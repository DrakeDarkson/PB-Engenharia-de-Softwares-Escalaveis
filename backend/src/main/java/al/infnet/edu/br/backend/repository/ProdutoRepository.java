package al.infnet.edu.br.backend.repository;

import al.infnet.edu.br.backend.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
