package al.infnet.edu.br.historico_service.repository;

import al.infnet.edu.br.historico_service.model.ProdutoHistorico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdutoHistoricoRepository extends JpaRepository<ProdutoHistorico, Long> {
    List<ProdutoHistorico> findByProdutoId(Long produtoId);
}
