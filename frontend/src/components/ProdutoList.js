import React, { useEffect, useState } from 'react';
import ProdutoService from '../services/ProdutoService';
import HistoricoService from '../services/HistoricoService';
import HistoricoList from './HistoricoList';
import './ProdutoList.css';

const ProdutoList = () => {
    const [produtos, setProdutos] = useState([]);
    const [historico, setHistorico] = useState({});
    const [expandido, setExpandido] = useState({});

    useEffect(() => {
        ProdutoService.getAllProdutos()
            .then((response) => {
                setProdutos(response.data);
            })
            .catch((error) => {
                console.error("There was an error fetching the produtos!", error);
            });
    }, []);

    useEffect(() => {
        produtos.forEach((produto) => {
            HistoricoService.getHistoricoByProdutoId(produto.id)
                .then((response) => {
                    setHistorico((prevHistorico) => ({
                        ...prevHistorico,
                        [produto.id]: response.data
                    }));
                })
                .catch((error) => {
                    console.error(`There was an error fetching the historico for produto ${produto.id}!`, error);
                });
        });
    }, [produtos]);

    const handleToggleHistorico = (id) => {
        setExpandido((prevExpandido) => ({
            ...prevExpandido,
            [id]: !prevExpandido[id]
        }));
    };

    return (
        <div className="produto-list">
            <h2>Lista de Produtos</h2>
            <ul>
                {produtos.map((produto) => (
                    <li key={produto.id}>
                        <div onClick={() => handleToggleHistorico(produto.id)} style={{ cursor: 'pointer' }}>
                            <h3>{produto.nome}</h3>
                            <p>{produto.descricao}</p>
                            <p><strong>Categoria:</strong> {produto.categoria}</p>
                            <p><strong>Preço:</strong> R$ {produto.preco.toFixed(2)}</p>
                        </div>
                        {expandido[produto.id] && (
                            <HistoricoList historico={historico[produto.id] || []} />
                        )}
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default ProdutoList;
