import React, { useEffect, useState } from 'react';
import ProdutoService from '../services/ProdutoService';
import HistoricoService from '../services/HistoricoService';
import HistoricoList from './HistoricoList';
import Estoque from './Estoque';
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
                console.error("Erro ao buscar os produtos!", error);
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
                    console.error(`Erro ao buscar o histórico do produto ${produto.id}!`, error);
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
                        <div>
                            <h3>{produto.nome}</h3>
                            <p>{produto.descricao}</p>
                            <p><strong>Categoria:</strong> {produto.categoria}</p>
                            <p><strong>Preço:</strong> R$ {produto.preco.toFixed(2)}</p>
                            <Estoque produtoId={produto.id} />
                            <button
                                className="historico-button"
                                onClick={() => handleToggleHistorico(produto.id)}
                            >
                                {expandido[produto.id] ? 'Ocultar Histórico' : 'Mostrar Histórico'}
                            </button>
                            {expandido[produto.id] && (
                                <div className="historico-list">
                                    <HistoricoList historico={historico[produto.id] || []} />
                                </div>
                            )}
                        </div>
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default ProdutoList;
