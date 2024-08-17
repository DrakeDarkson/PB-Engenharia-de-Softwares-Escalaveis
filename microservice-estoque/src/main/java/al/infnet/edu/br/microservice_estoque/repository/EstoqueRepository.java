package al.infnet.edu.br.microservice_estoque.repository;

import al.infnet.edu.br.microservice_estoque.model.Estoque;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstoqueRepository extends JpaRepository<Estoque, Long> {
}