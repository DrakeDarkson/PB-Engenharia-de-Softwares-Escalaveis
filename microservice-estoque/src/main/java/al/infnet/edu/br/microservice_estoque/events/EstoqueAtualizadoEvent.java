package al.infnet.edu.br.microservice_estoque.events;

import java.io.Serializable;
import java.time.LocalDateTime;

public class EstoqueAtualizadoEvent implements Serializable {
    private Long produtoId;
    private Integer quantidade;
    private String alteracaoTipo;
    private LocalDateTime dataAlteracao;

    public EstoqueAtualizadoEvent() {}

    public EstoqueAtualizadoEvent(Long produtoId, Integer quantidade, String alteracaoTipo, LocalDateTime dataAlteracao) {
        this.produtoId = produtoId;
        this.quantidade = quantidade;
        this.alteracaoTipo = alteracaoTipo;
        this.dataAlteracao = dataAlteracao;
    }

    public Long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public String getAlteracaoTipo() {
        return alteracaoTipo;
    }

    public void setAlteracaoTipo(String alteracaoTipo) {
        this.alteracaoTipo = alteracaoTipo;
    }

    public LocalDateTime getDataAlteracao() {
        return dataAlteracao;
    }

    public void setDataAlteracao(LocalDateTime dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }
}
