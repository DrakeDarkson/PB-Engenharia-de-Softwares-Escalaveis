import axios from 'axios';

const API_URL = 'http://localhost:8080';

const getHistoricoByProdutoId = (produtoId) => {
    return axios.get(`${API_URL}/produtos/${produtoId}/historico`);
};

export default {
    getHistoricoByProdutoId,
};
