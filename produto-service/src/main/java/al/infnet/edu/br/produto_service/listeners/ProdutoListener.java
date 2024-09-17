package al.infnet.edu.br.produto_service.listeners;

import al.infnet.edu.br.eventlibrary.events.ProdutoCriadoEvent;
import al.infnet.edu.br.eventlibrary.events.ProdutoAtualizadoEvent;
import al.infnet.edu.br.eventlibrary.events.ProdutoRemovidoEvent;
import al.infnet.edu.br.eventlibrary.events.HistoricoCriadoEvent;
import al.infnet.edu.br.eventlibrary.events.HistoricoAtualizadoEvent;
import al.infnet.edu.br.eventlibrary.events.EstoqueCriadoEvent;
import al.infnet.edu.br.eventlibrary.events.EstoqueAtualizadoEvent;
import al.infnet.edu.br.eventlibrary.events.EstoqueRemovidoEvent;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class ProdutoListener {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @RabbitListener(queues = "product.created.queue")
    public void onProdutoCriado(ProdutoCriadoEvent evento) {
        HistoricoCriadoEvent historicoCriadoEvent = new HistoricoCriadoEvent(
            Math.abs(UUID.randomUUID().getMostSignificantBits()),
            evento.getId(),
            evento.getNome(),
            evento.getCategoria(),
            evento.getPreco(),
            evento.getDescricao(),
            "Criação",
            LocalDateTime.now()
        );
        amqpTemplate.convertAndSend("historico.exchange", "historico.criado", historicoCriadoEvent);

        EstoqueCriadoEvent estoqueCriadoEvent = new EstoqueCriadoEvent(
            Math.abs(UUID.randomUUID().getMostSignificantBits()),
            evento.getId(),
            0
        );
        amqpTemplate.convertAndSend("stock.exchange", "stock.criado", estoqueCriadoEvent);
    }

    @RabbitListener(queues = "product.updated.queue")
    public void onProdutoAtualizado(ProdutoAtualizadoEvent evento) {
        HistoricoAtualizadoEvent historicoAtualizadoEvent = new HistoricoAtualizadoEvent(
            Math.abs(UUID.randomUUID().getMostSignificantBits()),
            evento.getId(),
            evento.getNome(),
            evento.getCategoria(),
            evento.getPreco(),
            evento.getDescricao(),
            "Atualização",
            LocalDateTime.now()
        );
        amqpTemplate.convertAndSend("historico.exchange", "historico.atualizado", historicoAtualizadoEvent);
    }

    @RabbitListener(queues = "product.deleted.queue")
    public void onProdutoRemovido(ProdutoRemovidoEvent evento) {
        EstoqueRemovidoEvent estoqueRemovidoEvent = new EstoqueRemovidoEvent(
            evento.getId()
        );
        amqpTemplate.convertAndSend("stock.exchange", "stock.removido", estoqueRemovidoEvent);
    }
}
