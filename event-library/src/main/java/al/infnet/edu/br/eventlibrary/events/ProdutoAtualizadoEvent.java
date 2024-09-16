package al.infnet.edu.br.eventlibrary.events;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoAtualizadoEvent implements Serializable {
    private Long id;
    private String nome;
    private String categoria;
    private BigDecimal preco;
    private String descricao;
}
