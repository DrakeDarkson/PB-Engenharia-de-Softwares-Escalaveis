package al.ifnet.edu.br.historico_service.service;

import al.ifnet.edu.br.historico_service.model.ProdutoHistorico;
import al.ifnet.edu.br.historico_service.repository.ProdutoHistoricoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoHistoricoService {
    private final ProdutoHistoricoRepository produtoHistoricoRepository;

    public ProdutoHistoricoService(ProdutoHistoricoRepository produtoHistoricoRepository) {
        this.produtoHistoricoRepository = produtoHistoricoRepository;
    }

    public ProdutoHistorico salvarHistorico(ProdutoHistorico historico) {
        return produtoHistoricoRepository.save(historico);
    }

    public List<ProdutoHistorico> obterHistoricoPorProduto(Long produtoId) {
        return produtoHistoricoRepository.findByProdutoId(produtoId);
    }
}
