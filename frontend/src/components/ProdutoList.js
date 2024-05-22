import React, { useEffect, useState } from 'react';
import ProdutoService from '../services/ProdutoService';
import './ProdutoList.css';

const ProdutoList = () => {
    const [produtos, setProdutos] = useState([]);

    useEffect(() => {
        ProdutoService.getAllProdutos()
            .then((response) => {
                setProdutos(response.data);
            })
            .catch((error) => {
                console.error("There was an error fetching the produtos!", error);
            });
    }, []);

    return (
        <div className="produto-list">
            <h2>Lista de Produtos</h2>
            <ul>
                {produtos.map((produto) => (
                    <li key={produto.id}>
                        <span>{produto.nome}</span>
                        <span>R$ {produto.preco.toFixed(2)}</span>
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default ProdutoList;