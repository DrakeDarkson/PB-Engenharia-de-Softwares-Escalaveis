package al.infnet.edu.br.backend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import al.infnet.edu.br.backend.model.Produto;
import al.infnet.edu.br.backend.service.ProdutoService;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {
    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public List<Produto> getAllProdutos() {
        return produtoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> getProdutoById(@PathVariable Long id) {
        Optional<Produto> produto = produtoService.findById(id);
        if (produto.isPresent()) {
            return ResponseEntity.ok(produto.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Produto createProduto(@RequestBody Produto produto) {
        return produtoService.save(produto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> updateProduto(@PathVariable Long id, @RequestBody Produto produtoDetails) {
        Optional<Produto> produto = produtoService.findById(id);
        if (produto.isPresent()) {
            Produto updatedProduto = produto.get();
            updatedProduto.setNome(produtoDetails.getNome());
            updatedProduto.setDescricao(produtoDetails.getDescricao());
            updatedProduto.setPreco(produtoDetails.getPreco());
            updatedProduto.setCategoria(produtoDetails.getCategoria());
            produtoService.save(updatedProduto);
            return ResponseEntity.ok(updatedProduto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduto(@PathVariable Long id) {
        Optional<Produto> produto = produtoService.findById(id);
        if (produto.isPresent()) {
            produtoService.delete(produto.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
