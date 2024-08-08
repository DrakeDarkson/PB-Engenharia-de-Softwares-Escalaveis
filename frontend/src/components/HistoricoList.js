import React from 'react';
import './HistoricoList.css';

const HistoricoList = ({ historico }) => {
    return (
        <div className="historico-list">
            <h3>Histórico de Alterações</h3>
            <ul>
                {historico.length === 0 ? (
                    <li>Nenhum histórico disponível</li>
                ) : (
                    historico.map((item) => (
                        <li key={item.id}>
                            <span>{item.alteracaoTipo} - {new Date(item.alteracaoData).toLocaleString()}</span>
                        </li>
                    ))
                )}
            </ul>
        </div>
    );
};

export default HistoricoList;
