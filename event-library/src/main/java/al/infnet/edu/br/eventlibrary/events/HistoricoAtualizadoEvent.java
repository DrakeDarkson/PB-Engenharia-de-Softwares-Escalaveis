package al.infnet.edu.br.eventlibrary.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HistoricoAtualizadoEvent {
    private Long produtoId;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private String categoria;
    private String alteracaoTipo;
    private LocalDateTime alteracaoData;
}
