package al.infnet.edu.br.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import al.infnet.edu.br.backend.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
