package al.infnet.edu.br.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import al.infnet.edu.br.backend.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
