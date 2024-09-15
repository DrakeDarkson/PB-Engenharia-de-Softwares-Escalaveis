package al.infnet.edu.br.historico_service.listeners;

import al.infnet.edu.br.eventlibrary.events.HistoricoCriadoEvent;
import al.infnet.edu.br.eventlibrary.events.HistoricoAtualizadoEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class HistoricoListener {

    @RabbitListener(queues = "historico.criado.queue")
    public void onHistoricoCriado(HistoricoCriadoEvent evento) {
    }

    @RabbitListener(queues = "historico.atualizado.queue")
    public void onHistoricoAtualizado(HistoricoAtualizadoEvent evento) {
    }
}
