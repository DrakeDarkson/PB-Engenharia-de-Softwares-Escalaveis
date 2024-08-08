package al.infnet.edu.br.backend.service;

import al.infnet.edu.br.backend.model.ProdutoHistorico;
import al.infnet.edu.br.backend.repository.ProdutoHistoricoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoHistoricoService {

    @Autowired
    private ProdutoHistoricoRepository produtoHistoricoRepository;

    public List<ProdutoHistorico> findHistoricoByProdutoId(Long produtoId) {
        return produtoHistoricoRepository.findByProdutoId(produtoId);
    }
}
