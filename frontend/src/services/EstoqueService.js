import axios from 'axios';

const ESTOQUE_API_URL = 'http://localhost:8081/api/estoques';

class EstoqueService {
    getEstoqueByProdutoId(produtoId) {
        return axios.get(`${ESTOQUE_API_URL}/produto/${produtoId}`);
    }

    atualizarEstoque(produtoId, quantidade) {
        return axios.put(`${ESTOQUE_API_URL}/produto/${produtoId}`, {produtoId, quantidade });
    }
}

export default new EstoqueService();
