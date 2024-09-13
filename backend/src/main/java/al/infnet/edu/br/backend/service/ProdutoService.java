package al.infnet.edu.br.backend.service;

import al.infnet.edu.br.backend.client.EstoqueClient;
import al.infnet.edu.br.backend.dto.EstoqueDTO;
import al.infnet.edu.br.backend.dto.ProdutoDTO;
import al.infnet.edu.br.backend.events.ProdutoCriadoEvent;
import al.infnet.edu.br.backend.events.ProdutoAtualizadoEvent;
import al.infnet.edu.br.backend.events.ProdutoRemovidoEvent;
import al.infnet.edu.br.backend.model.Produto;
import al.infnet.edu.br.backend.model.ProdutoHistorico;
import al.infnet.edu.br.backend.repository.ProdutoHistoricoRepository;
import al.infnet.edu.br.backend.repository.ProdutoRepository;
import al.infnet.edu.br.backend.service.ProdutoService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ProdutoHistoricoRepository produtoHistoricoRepository;

    @Autowired
    private EstoqueClient estoqueClient;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Autowired
    public ProdutoService(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    public List<Produto> findAll() {
        return produtoRepository.findAll();
    }

    public Produto save(Produto produto) {
        Produto savedProduto = produtoRepository.save(produto);

        ProdutoHistorico historico = new ProdutoHistorico();
        historico.setProdutoId(savedProduto.getId());
        historico.setNome(savedProduto.getNome());
        historico.setDescricao(savedProduto.getDescricao());
        historico.setPreco(savedProduto.getPreco());
        historico.setCategoria(savedProduto.getCategoria());
        historico.setAlteracaoTipo(produto.getId() == null ? "Criação" : "Atualização");
        historico.setAlteracaoData(LocalDateTime.now().toString());
        produtoHistoricoRepository.save(historico);

        ProdutoCriadoEvent produtoCriadoEvent = new ProdutoCriadoEvent(
            savedProduto.getId(),
            savedProduto.getNome(),
            savedProduto.getCategoria(),
            savedProduto.getPreco(),
            savedProduto.getDescricao()
        );
        amqpTemplate.convertAndSend("product.exchange", "product.created", produtoCriadoEvent);

        return savedProduto;
    }

    public Produto findById(Long id) {
        return produtoRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        Produto produto = produtoRepository.findById(id).orElse(null);
        if (produto != null) {
            produtoRepository.deleteById(id);
            ProdutoHistorico historico = new ProdutoHistorico();
            historico.setProdutoId(produto.getId());
            historico.setNome(produto.getNome());
            historico.setDescricao(produto.getDescricao());
            historico.setPreco(produto.getPreco());
            historico.setCategoria(produto.getCategoria());
            historico.setAlteracaoTipo("Exclusão");
            historico.setAlteracaoData(LocalDateTime.now().toString());
            produtoHistoricoRepository.save(historico);

            ProdutoRemovidoEvent produtoRemovidoEvent = new ProdutoRemovidoEvent(produto.getId());
            amqpTemplate.convertAndSend("product.exchange", "product.deleted", produtoRemovidoEvent);
        }
    }

    public ProdutoDTO adicionarProduto(ProdutoDTO produtoDTO) {
        Produto produto = new Produto();
        produto.setNome(produtoDTO.getNome());
        produto.setDescricao(produtoDTO.getDescricao());
        produto.setPreco(produtoDTO.getPreco());
        produtoRepository.save(produto);

        EstoqueDTO estoqueDTO = new EstoqueDTO();
        estoqueDTO.setProdutoId(produto.getId());
        estoqueDTO.setQuantidade(produtoDTO.getQuantidadeInicial());

        ProdutoCriadoEvent produtoCriadoEvent = new ProdutoCriadoEvent(
            produto.getId(),
            produto.getNome(),
            produto.getCategoria(),
            produto.getPreco(),
            produto.getDescricao()
        );
        amqpTemplate.convertAndSend("product.exchange", "product.created", produtoCriadoEvent);

        return produtoDTO;
    }

    public EstoqueDTO consultarEstoque(Long produtoId) {
        return estoqueClient.obterEstoque(produtoId);
    }

    public EstoqueDTO atualizarEstoque(Long produtoId, int quantidade) {
        return estoqueClient.atualizarEstoque(produtoId, quantidade);
    }
}
