import axios from 'axios';

const API_URL = 'http://localhost:8080/produtos';

class ProdutoService {
    getAllProdutos() {
        return axios.get(API_URL);
    }
}

export default new ProdutoService();