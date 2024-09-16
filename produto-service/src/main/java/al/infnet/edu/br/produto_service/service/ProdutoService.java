package al.infnet.edu.br.produto_service.service;

import al.infnet.edu.br.produto_service.model.Produto;
import al.infnet.edu.br.produto_service.repository.ProdutoRepository;

import al.infnet.edu.br.eventlibrary.events.ProdutoCriadoEvent;
import al.infnet.edu.br.eventlibrary.events.ProdutoRemovidoEvent;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private AmqpTemplate amqpTemplate;

    public List<Produto> findAll() {
        return produtoRepository.findAll();
    }

    public Produto save(Produto produto) {
        Produto savedProduto = produtoRepository.save(produto);

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

            ProdutoRemovidoEvent produtoRemovidoEvent = new ProdutoRemovidoEvent(produto.getId());
            amqpTemplate.convertAndSend("product.exchange", "product.deleted", produtoRemovidoEvent);
        }
    }
}
