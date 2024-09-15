package al.infnet.edu.br.estoque_service.listeners;

import al.infnet.edu.br.eventlibrary.events.EstoqueAtualizadoEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EstoqueListener {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @RabbitListener(queues = "stock.updated.queue")
    public void onEstoqueAtualizado(EstoqueAtualizadoEvent evento) {
    }
}
