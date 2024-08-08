package al.infnet.edu.br.backend.controller;

import al.infnet.edu.br.backend.model.Produto;
import al.infnet.edu.br.backend.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public List<Produto> getAllProdutos() {
        return produtoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> getProdutoById(@PathVariable Long id) {
        Produto produto = produtoService.findById(id);
        return produto != null ? ResponseEntity.ok(produto) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Produto> createProduto(@RequestBody Produto produto) {
        Produto savedProduto = produtoService.save(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> updateProduto(@PathVariable Long id, @RequestBody Produto produto) {
        produto.setId(id);
        Produto updatedProduto = produtoService.save(produto);
        return ResponseEntity.ok(updatedProduto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduto(@PathVariable Long id) {
        Produto produto = produtoService.findById(id);
        if (produto != null) {
            produtoService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
