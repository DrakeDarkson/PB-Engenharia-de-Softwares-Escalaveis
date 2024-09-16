package al.infnet.edu.br.estoque_service.service;

import al.infnet.edu.br.estoque_service.model.Estoque;
import al.infnet.edu.br.estoque_service.repository.EstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EstoqueService {

    private final EstoqueRepository estoqueRepository;

    @Autowired
    public EstoqueService(EstoqueRepository estoqueRepository) {
        this.estoqueRepository = estoqueRepository;
    }

    public Estoque criarEstoque(Estoque estoque) {
        return estoqueRepository.save(estoque);
    }

    public Optional<Estoque> buscarEstoquePorId(Long id) {
        return estoqueRepository.findById(id);
    }

    public Optional<Estoque> buscarEstoquePorProdutoId(Long produtoId) {
        return estoqueRepository.findByProdutoId(produtoId);
    }

    public Estoque atualizarEstoque(Long id, Estoque estoque) {
        if (estoqueRepository.existsById(id)) {
            estoque.setId(id);
            return estoqueRepository.save(estoque);
        }
        return null;
    }

    public void deletarEstoque(Long id) {
        estoqueRepository.deleteById(id);
    }
}
