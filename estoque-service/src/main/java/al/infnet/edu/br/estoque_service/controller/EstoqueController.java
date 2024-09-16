package al.infnet.edu.br.estoque_service.controller;

import al.infnet.edu.br.estoque_service.model.Estoque;
import al.infnet.edu.br.estoque_service.service.EstoqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

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
    @Operation(summary = "Create a new stock record", description = "Create a new stock record with the given details.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Stock record created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    public ResponseEntity<Estoque> criarEstoque(@RequestBody Estoque estoque) {
        Estoque novoEstoque = estoqueService.criarEstoque(estoque);
        return ResponseEntity.ok(novoEstoque);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get stock by ID", description = "Retrieve a stock record by its ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Stock record retrieved successfully"),
        @ApiResponse(responseCode = "404", description = "Stock record not found")
    })
    public ResponseEntity<Estoque> buscarEstoquePorId(@PathVariable Long id) {
        Optional<Estoque> estoque = estoqueService.buscarEstoquePorId(id);
        return estoque.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/produto/{produtoId}")
    @Operation(summary = "Get stock by product ID", description = "Retrieve the stock record for a specific product by its ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Stock record retrieved successfully"),
        @ApiResponse(responseCode = "404", description = "Stock record not found for the given product ID")
    })
    public ResponseEntity<Estoque> buscarEstoquePorProdutoId(@PathVariable Long produtoId) {
        Optional<Estoque> estoque = estoqueService.buscarEstoquePorProdutoId(produtoId);
        return estoque.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update stock by ID", description = "Update the stock record with the given ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Stock record updated successfully"),
        @ApiResponse(responseCode = "404", description = "Stock record not found")
    })
    public ResponseEntity<Estoque> atualizarEstoque(@PathVariable Long id, @RequestBody Estoque estoque) {
        Estoque estoqueAtualizado = estoqueService.atualizarEstoque(id, estoque);
        return estoqueAtualizado != null ? ResponseEntity.ok(estoqueAtualizado) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete stock by ID", description = "Delete the stock record with the given ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Stock record deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Stock record not found")
    })
    public ResponseEntity<Void> deletarEstoque(@PathVariable Long id) {
        estoqueService.deletarEstoque(id);
        return ResponseEntity.noContent().build();
    }
}
