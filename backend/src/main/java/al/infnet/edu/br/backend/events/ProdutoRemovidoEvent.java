package al.infnet.edu.br.backend.events;

public class ProdutoRemovidoEvent {
    private Long produtoId;

    public ProdutoRemovidoEvent() {}

    public ProdutoRemovidoEvent(Long produtoId) {
        this.produtoId = produtoId;
    }

    public Long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }
}
