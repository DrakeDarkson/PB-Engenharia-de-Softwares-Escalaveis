package al.infnet.edu.br.microservice_estoque.listeners;

import al.infnet.edu.br.microservice_estoque.events.EstoqueAtualizadoEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class EstoqueListener {

    @RabbitListener(queues = "stock.updated.queue")
    public void onEstoqueAtualizado(EstoqueAtualizadoEvent evento) {
        // Lógica para tratar o evento EstoqueAtualizadoEvent
    }
}
