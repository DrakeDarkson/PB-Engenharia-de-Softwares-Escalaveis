import React from 'react';
import './App.css';
import ProdutoList from './components/ProdutoList';

function App() {
    return (
        <div className="App">
            <main>
                <h1>Gerenciamento de Produtos</h1>
                <ProdutoList />
            </main>
        </div>
    );
}

export default App;
