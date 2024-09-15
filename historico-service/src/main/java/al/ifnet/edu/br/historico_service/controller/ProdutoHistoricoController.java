package al.infnet.edu.br.historico_service.controller;

import al.infnet.edu.br.historico_service.service.ProdutoHistoricoService;
import al.infnet.edu.br.historico_service.model.ProdutoHistorico;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/historico")
public class ProdutoHistoricoController {

    private final ProdutoHistoricoService produtoHistoricoService;

    public ProdutoHistoricoController(ProdutoHistoricoService produtoHistoricoService) {
        this.produtoHistoricoService = produtoHistoricoService;
    }

    @PostMapping
    public ResponseEntity<ProdutoHistorico> salvarHistorico(@RequestBody ProdutoHistorico historico) {
        ProdutoHistorico novoHistorico = produtoHistoricoService.salvarHistorico(historico);
        return ResponseEntity.ok(novoHistorico);
    }

    @GetMapping("/produto/{produtoId}")
    public ResponseEntity<List<ProdutoHistorico>> obterHistorico(@PathVariable Long produtoId) {
        List<ProdutoHistorico> historico = produtoHistoricoService.obterHistoricoPorProduto(produtoId);
        return ResponseEntity.ok(historico);
    }
}
