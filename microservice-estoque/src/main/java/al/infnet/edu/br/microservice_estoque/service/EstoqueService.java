package al.infnet.edu.br.microservice_estoque.service;

import al.infnet.edu.br.microservice_estoque.model.Estoque;
import al.infnet.edu.br.microservice_estoque.repository.EstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstoqueService {
    @Autowired
    private EstoqueRepository estoqueRepository;

    public List<Estoque> listarEstoques() {
        return estoqueRepository.findAll();
    }

    public Estoque obterEstoque(Long id) {
        return estoqueRepository.findById(id).orElseThrow(() -> new RuntimeException("Estoque não encontrado"));
    }

    public Estoque adicionarEstoque(Estoque estoque) {
        return estoqueRepository.save(estoque);
    }

    public Estoque atualizarEstoque(Long id, Estoque estoque) {
        if (estoqueRepository.existsById(id)) {
            estoque.setId(id);
            return estoqueRepository.save(estoque);
        } else {
            return null;
        }
    }

    public boolean excluirEstoque(Long id) {
        if (estoqueRepository.existsById(id)) {
            estoqueRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
