package al.infnet.edu.br.backend.listeners;

import al.infnet.edu.br.backend.events.ProdutoCriadoEvent;
import al.infnet.edu.br.backend.events.ProdutoAtualizadoEvent;
import al.infnet.edu.br.backend.events.EstoqueAtualizadoEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ProdutoListener {

    @RabbitListener(queues = "product.created.queue")
    public void onProdutoCriado(ProdutoCriadoEvent evento) {
        // Lógica para tratar o evento ProdutoCriadoEvent
    }

    @RabbitListener(queues = "product.updated.queue")
    public void onProdutoAtualizado(ProdutoAtualizadoEvent evento) {
        // Lógica para tratar o evento ProdutoAtualizadoEvent
    }

    @RabbitListener(queues = "stock.updated.queue")
    public void onEstoqueAtualizado(EstoqueAtualizadoEvent evento) {
        // Lógica para tratar o evento EstoqueAtualizadoEvent
    }
}
