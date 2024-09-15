package al.infnet.edu.br.eventlibrary.events;

import lombok.Data;

@Data
public class HistoricoCriadoEvent {
    private Long produtoId;
    private String nome;
    private String categoria;
    private double preco;
    private String descricao;
    private String alteracaoTipo;

    public HistoricoCriadoEvent(Long produtoId, String nome, String categoria, double preco, String descricao, String alteracaoTipo) {
        this.produtoId = produtoId;
        this.nome = nome;
        this.categoria = categoria;
        this.preco = preco;
        this.descricao = descricao;
        this.alteracaoTipo = alteracaoTipo;
    }
}
