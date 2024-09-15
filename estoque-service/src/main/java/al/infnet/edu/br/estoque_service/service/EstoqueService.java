package al.infnet.edu.br.estoque_service.service;

import al.infnet.edu.br.estoque_service.model.Estoque;
import al.infnet.edu.br.estoque_service.repository.EstoqueRepository;
import al.infnet.edu.br.eventlibrary.events.EstoqueAtualizadoEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstoqueService {

    @Autowired
    private EstoqueRepository estoqueRepository;

    public List<Estoque> listarEstoques() {
        return estoqueRepository.findAll();
    }

    public Estoque obterEstoque(Long id) {
        return estoqueRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Estoque não encontrado"));
    }

    public Estoque obterEstoquePorProdutoId(Long produtoId) {
        return estoqueRepository.findByProdutoId(produtoId)
            .orElseThrow(() -> new RuntimeException("Estoque não encontrado para o produto com ID: " + produtoId));
    }

    public Estoque adicionarEstoque(Estoque estoque) {
        return estoqueRepository.save(estoque);
    }

    public Estoque atualizarEstoque(Long id, Estoque estoque) {
        if (estoqueRepository.existsById(id)) {
            estoque.setId(id);
            return estoqueRepository.save(estoque);
        } else {
            throw new RuntimeException("Estoque não encontrado para atualização");
        }
    }

    public boolean excluirEstoque(Long id) {
        if (estoqueRepository.existsById(id)) {
            estoqueRepository.deleteById(id);
            return true;
        } else {
            throw new RuntimeException("Estoque não encontrado para exclusão");
        }
    }

    @RabbitListener(queues = "stock.updated.queue")
    public void receberEstoqueAtualizado(EstoqueAtualizadoEvent evento) {
        Estoque estoque = estoqueRepository.findByProdutoId(evento.getProdutoId())
            .orElse(new Estoque(evento.getProdutoId(), 0));
        estoque.setQuantidade(evento.getQuantidade());
        estoqueRepository.save(estoque);
    }
}
