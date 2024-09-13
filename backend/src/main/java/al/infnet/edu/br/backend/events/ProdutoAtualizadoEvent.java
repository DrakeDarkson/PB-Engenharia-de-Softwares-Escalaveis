package al.infnet.edu.br.backend.events;

import java.io.Serializable;

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

    public Long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
