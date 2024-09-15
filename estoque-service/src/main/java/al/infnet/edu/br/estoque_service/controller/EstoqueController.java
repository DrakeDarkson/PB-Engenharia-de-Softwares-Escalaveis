package al.infnet.edu.br.estoque_service.controller;

import al.infnet.edu.br.estoque_service.model.Estoque;
import al.infnet.edu.br.estoque_service.service.EstoqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estoques")
public class EstoqueController {
    @Autowired
    private EstoqueService estoqueService;

    @GetMapping
    public List<Estoque> listarEstoques() {
        return estoqueService.listarEstoques();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estoque> obterEstoque(@PathVariable Long id) {
        Estoque estoque = estoqueService.obterEstoque(id);
        return ResponseEntity.ok(estoque);
    }

    @GetMapping("/produto/{produtoId}")
    public ResponseEntity<Estoque> obterEstoquePorProdutoId(@PathVariable Long produtoId) {
        Estoque estoque = estoqueService.obterEstoquePorProdutoId(produtoId);
        return ResponseEntity.ok(estoque);
    }

    @PostMapping
    public ResponseEntity<Estoque> adicionarEstoque(@RequestBody Estoque estoque) {
        Estoque novoEstoque = estoqueService.adicionarEstoque(estoque);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoEstoque);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Estoque> atualizarEstoque(@PathVariable Long id, @RequestBody Estoque estoque) {
        Estoque estoqueAtualizado = estoqueService.atualizarEstoque(id, estoque);
        if (estoqueAtualizado != null) {
            return ResponseEntity.ok(estoqueAtualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/produto/{produtoId}")
    public ResponseEntity<Estoque> atualizarEstoquePorProdutoId(
            @PathVariable Long produtoId, @RequestBody Estoque estoque) {
        Estoque estoqueExistente = estoqueService.obterEstoquePorProdutoId(produtoId);
        if (estoqueExistente != null) {
            estoque.setId(estoqueExistente.getId());
            Estoque estoqueAtualizado = estoqueService.atualizarEstoque(estoqueExistente.getId(), estoque);
            return ResponseEntity.ok(estoqueAtualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirEstoque(@PathVariable Long id) {
        boolean excluido = estoqueService.excluirEstoque(id);
        if (excluido) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
