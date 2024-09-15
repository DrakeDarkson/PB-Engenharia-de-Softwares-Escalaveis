package al.infnet.edu.br.eventlibrary.events;

import java.io.Serializable;
import lombok.Data;

@Data
public class ProdutoCriadoEvent implements Serializable {
    private Long produtoId;
    private String nome;
    private String categoria;
    private Double preco;
    private String descricao;

    public ProdutoCriadoEvent() {}

    public ProdutoCriadoEvent(Long produtoId, String nome, String categoria, Double preco, String descricao) {
        this.produtoId = produtoId;
        this.nome = nome;
        this.categoria = categoria;
        this.preco = preco;
        this.descricao = descricao;
    }
}
