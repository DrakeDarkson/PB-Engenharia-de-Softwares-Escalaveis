import React, { useState, useEffect } from 'react';
import EstoqueService from '../services/EstoqueService';
import './Estoque.css';

const Estoque = ({ produtoId }) => {
    const [estoque, setEstoque] = useState(0);
    const [quantidade, setQuantidade] = useState(0);

    useEffect(() => {
        EstoqueService.getEstoqueByProdutoId(produtoId)
            .then((response) => {
                setEstoque(response.data.quantidade);
            })
            .catch((error) => {
                console.error('Erro ao buscar o estoque', error);
            });
    }, [produtoId]);

    const handleAtualizarEstoque = () => {
        EstoqueService.atualizarEstoque(produtoId, quantidade)
            .then((response) => {
                setEstoque(response.data.quantidade);
                setQuantidade(0);
            })
            .catch((error) => {
                console.error('Erro ao atualizar o estoque', error);
            });
    };

    return (
        <div className="estoque">
            <p><strong>Estoque Atual:</strong> {estoque}</p>
            <input
                type="number"
                value={quantidade}
                onChange={(e) => setQuantidade(parseInt(e.target.value))}
                placeholder="Nova quantidade"
            />
            <button onClick={handleAtualizarEstoque}>Atualizar Estoque</button>
        </div>
    );
};

export default Estoque;
