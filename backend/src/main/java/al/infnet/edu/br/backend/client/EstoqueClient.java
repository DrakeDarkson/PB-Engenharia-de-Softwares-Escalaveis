package al.infnet.edu.br.backend.client;

import al.infnet.edu.br.backend.dto.EstoqueDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "microservice-estoque")
public interface EstoqueClient {

    @GetMapping("/api/estoques/{produtoId}")
    EstoqueDTO obterEstoque(@PathVariable("produtoId") Long produtoId);

    @PutMapping("/api/estoques/{produtoId}")
    EstoqueDTO atualizarEstoque(@PathVariable("produtoId") Long produtoId, @RequestBody int quantidade);

    @PostMapping("/api/estoques")
    EstoqueDTO adicionarEstoque(@RequestBody EstoqueDTO estoque);
}
