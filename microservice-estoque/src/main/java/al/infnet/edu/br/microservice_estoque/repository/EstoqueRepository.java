package al.infnet.edu.br.microservice_estoque.repository;

import al.infnet.edu.br.microservice_estoque.model.Estoque;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface EstoqueRepository extends JpaRepository<Estoque, Long> {
    Optional<Estoque> findByProdutoId(Long produtoId);
}
