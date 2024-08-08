package al.infnet.edu.br.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class ProdutoHistorico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long produtoId;
    private String nome;
    private String descricao;
    private double preco;
    private String categoria;
    private String alteracaoTipo;
    private String alteracaoData;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getAlteracaoTipo() {
        return alteracaoTipo;
    }

    public void setAlteracaoTipo(String alteracaoTipo) {
        this.alteracaoTipo = alteracaoTipo;
    }

    public String getAlteracaoData() {
        return alteracaoData;
    }

    public void setAlteracaoData(String alteracaoData) {
        this.alteracaoData = alteracaoData;
    }
}
