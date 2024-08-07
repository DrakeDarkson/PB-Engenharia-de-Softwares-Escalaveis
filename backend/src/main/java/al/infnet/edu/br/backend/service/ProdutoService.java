package al.infnet.edu.br.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import al.infnet.edu.br.backend.model.Produto;
import al.infnet.edu.br.backend.repository.ProdutoRepository;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Produto> findAll() {
        return produtoRepository.findAll();
    }

    public Optional<Produto> findById(Long id) {
        return produtoRepository.findById(id);
    }

    public Produto save(Produto produto) {
        return produtoRepository.save(produto);
    }

    public void delete(Produto produto) {
        produtoRepository.delete(produto);
    }
}
