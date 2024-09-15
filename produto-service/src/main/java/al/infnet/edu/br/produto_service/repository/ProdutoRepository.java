package al.infnet.edu.br.produto_service.repository;

import al.infnet.edu.br.produto_service.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
