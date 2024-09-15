package al.infnet.edu.br.eventlibrary.events;

import java.io.Serializable;
import lombok.Data;

@Data
public class ProdutoRemovidoEvent implements Serializable {
    private Long produtoId;

    public ProdutoRemovidoEvent() {}

    public ProdutoRemovidoEvent(Long produtoId) {
        this.produtoId = produtoId;
    }
}
