package al.infnet.edu.br.produto_service.listeners;

import al.infnet.edu.br.eventlibrary.events.ProdutoCriadoEvent;
import al.infnet.edu.br.eventlibrary.events.ProdutoAtualizadoEvent;
import al.infnet.edu.br.eventlibrary.events.ProdutoRemovidoEvent;
import al.infnet.edu.br.eventlibrary.events.HistoricoCriadoEvent;
import al.infnet.edu.br.eventlibrary.events.HistoricoAtualizadoEvent;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProdutoListener {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @RabbitListener(queues = "product.created.queue")
    public void onProdutoCriado(ProdutoCriadoEvent evento) {
        HistoricoCriadoEvent historicoCriadoEvent = new HistoricoCriadoEvent(
            evento.getId(),
            evento.getNome(),
            evento.getCategoria(),
            evento.getPreco(),
            evento.getDescricao(),
            "Criação"
        );
        amqpTemplate.convertAndSend("historico.exchange", "historico.criado", historicoCriadoEvent);

        EstoqueAtualizadoEvent estoqueAtualizadoEvent = new EstoqueAtualizadoEvent(
            evento.getId(),
            0 // Quantidade
        );
        amqpTemplate.convertAndSend("stock.exchange", "stock.atualizado", estoqueAtualizadoEvent);
    }

    @RabbitListener(queues = "product.updated.queue")
    public void onProdutoAtualizado(ProdutoAtualizadoEvent evento) {
        HistoricoAtualizadoEvent historicoAtualizadoEvent = new HistoricoAtualizadoEvent(
            evento.getId(),
            evento.getNome(),
            evento.getCategoria(),
            evento.getPreco(),
            evento.getDescricao(),
            "Atualização"
        );
        amqpTemplate.convertAndSend("historico.exchange", "historico.atualizado", historicoAtualizadoEvent);
    }

    @RabbitListener(queues = "product.deleted.queue")
    public void onProdutoRemovido(ProdutoRemovidoEvent evento) {
        EstoqueAtualizadoEvent estoqueAtualizadoEvent = new EstoqueAtualizadoEvent(
            evento.getId(),
            null // Quantidade
        );
        amqpTemplate.convertAndSend("stock.exchange", "stock.deletado", estoqueAtualizadoEvent);
    }
}
