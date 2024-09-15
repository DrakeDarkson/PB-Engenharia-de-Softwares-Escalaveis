package al.infnet.edu.br.eventlibrary.events;

import java.io.Serializable;
import lombok.Data;

@Data
public class ProdutoAtualizadoEvent implements Serializable {
    private Long produtoId;
    private String nome;
    private String categoria;
    private Double preco;
    private String descricao;

    public ProdutoAtualizadoEvent() {}

    public ProdutoAtualizadoEvent(Long produtoId, String nome, String categoria, Double preco, String descricao) {
        this.produtoId = produtoId;
        this.nome = nome;
        this.categoria = categoria;
        this.preco = preco;
        this.descricao = descricao;
    }
}
