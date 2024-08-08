package al.infnet.edu.br.backend.repository;

import al.infnet.edu.br.backend.model.ProdutoHistorico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdutoHistoricoRepository extends JpaRepository<ProdutoHistorico, Long> {
    List<ProdutoHistorico> findByProdutoId(Long produtoId);
}
