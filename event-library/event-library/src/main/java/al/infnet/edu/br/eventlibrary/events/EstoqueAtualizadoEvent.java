package al.infnet.edu.br.eventlibrary.events;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class EstoqueAtualizadoEvent implements Serializable {
    private Long produtoId;
    private Integer quantidade;
    private String alteracaoTipo;
    private LocalDateTime dataAlteracao;

    public EstoqueAtualizadoEvent() {}

    public EstoqueAtualizadoEvent(Long produtoId, Integer quantidade, String alteracaoTipo, LocalDateTime dataAlteracao) {
        this.produtoId = produtoId;
        this.quantidade = quantidade;
        this.alteracaoTipo = alteracaoTipo;
        this.dataAlteracao = dataAlteracao;
    }
}
