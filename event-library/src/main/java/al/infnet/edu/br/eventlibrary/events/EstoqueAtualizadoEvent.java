package al.infnet.edu.br.eventlibrary.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EstoqueAtualizadoEvent implements Serializable {
    private Long id;
    private Long produtoId;
    private int quantidade;
}
