package al.infnet.edu.br.eventlibrary.events;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoRemovidoEvent implements Serializable {
    private Long id;
}
