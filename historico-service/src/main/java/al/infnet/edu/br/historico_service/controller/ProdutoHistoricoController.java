package al.infnet.edu.br.historico_service.controller;

import al.infnet.edu.br.historico_service.service.ProdutoHistoricoService;
import al.infnet.edu.br.historico_service.model.ProdutoHistorico;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;

@RestController
@RequestMapping("/api/historico")
public class ProdutoHistoricoController {

    private final ProdutoHistoricoService produtoHistoricoService;

    public ProdutoHistoricoController(ProdutoHistoricoService produtoHistoricoService) {
        this.produtoHistoricoService = produtoHistoricoService;
    }

    @PostMapping
    @Operation(summary = "Save product history", description = "Save a new product history record.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Product history saved successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    public ResponseEntity<ProdutoHistorico> salvarHistorico(@RequestBody ProdutoHistorico historico) {
        ProdutoHistorico novoHistorico = produtoHistoricoService.salvarHistorico(historico);
        return ResponseEntity.ok(novoHistorico);
    }

    @GetMapping("/produto/{produtoId}")
    @Operation(summary = "Get product history by product ID", description = "Retrieve the history of a specific product by its ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Product history retrieved successfully"),
        @ApiResponse(responseCode = "404", description = "Product not found or no history available")
    })
    public ResponseEntity<List<ProdutoHistorico>> obterHistorico(@PathVariable Long produtoId) {
        List<ProdutoHistorico> historico = produtoHistoricoService.obterHistoricoPorProduto(produtoId);
        return ResponseEntity.ok(historico);
    }
}
