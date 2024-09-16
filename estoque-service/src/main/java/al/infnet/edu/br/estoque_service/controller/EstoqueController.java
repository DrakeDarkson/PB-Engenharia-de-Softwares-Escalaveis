package al.infnet.edu.br.estoque_service.controller;

import al.infnet.edu.br.estoque_service.model.Estoque;
import al.infnet.edu.br.estoque_service.service.EstoqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/estoques")
public class EstoqueController {

    private final EstoqueService estoqueService;

    @Autowired
    public EstoqueController(EstoqueService estoqueService) {
        this.estoqueService = estoqueService;
    }

    @PostMapping
    public ResponseEntity<Estoque> criarEstoque(@RequestBody Estoque estoque) {
        Estoque novoEstoque = estoqueService.criarEstoque(estoque);
        return ResponseEntity.ok(novoEstoque);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estoque> buscarEstoquePorId(@PathVariable Long id) {
        Optional<Estoque> estoque = estoqueService.buscarEstoquePorId(id);
        return estoque.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/produto/{produtoId}")
    public ResponseEntity<Estoque> buscarEstoquePorProdutoId(@PathVariable Long produtoId) {
        Optional<Estoque> estoque = estoqueService.buscarEstoquePorProdutoId(produtoId);
        return estoque.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Estoque> atualizarEstoque(@PathVariable Long id, @RequestBody Estoque estoque) {
        Estoque estoqueAtualizado = estoqueService.atualizarEstoque(id, estoque);
        return estoqueAtualizado != null ? ResponseEntity.ok(estoqueAtualizado) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEstoque(@PathVariable Long id) {
        estoqueService.deletarEstoque(id);
        return ResponseEntity.noContent().build();
    }
}
