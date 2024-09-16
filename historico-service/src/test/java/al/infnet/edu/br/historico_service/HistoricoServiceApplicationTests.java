package al.infnet.edu.br.historico_service;

import al.infnet.edu.br.historico_service.model.ProdutoHistorico;
import al.infnet.edu.br.historico_service.repository.ProdutoHistoricoRepository;
import al.infnet.edu.br.historico_service.service.ProdutoHistoricoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class HistoricoServiceApplicationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Mock
    private ProdutoHistoricoRepository produtoHistoricoRepository;

    @InjectMocks
    private ProdutoHistoricoService produtoHistoricoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void contextLoads() {
    }

    @Test
    void testSalvarHistorico() {
        ProdutoHistorico historico = new ProdutoHistorico(
                1L, 
                1L, 
                "Produto 1", 
                "Descrição do produto", 
                BigDecimal.valueOf(100.0), 
                "Categoria 1", 
                "Criação", 
                LocalDateTime.now()
        );

        when(produtoHistoricoRepository.save(any(ProdutoHistorico.class))).thenReturn(historico);

        ResponseEntity<ProdutoHistorico> response = restTemplate.postForEntity("/api/historico", historico, ProdutoHistorico.class);
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(historico.getId(), response.getBody().getId());
    }

    @Test
    void testObterHistorico() {
        ProdutoHistorico historico = new ProdutoHistorico(
                1L, 
                1L, 
                "Produto 1", 
                "Descrição do produto", 
                BigDecimal.valueOf(100.0), 
                "Categoria 1", 
                "Criação", 
                LocalDateTime.now()
        );

        // Mockando o retorno do repositório
        when(produtoHistoricoRepository.findByProdutoId(1L)).thenReturn(Collections.singletonList(historico));

        // Chamando o endpoint
        ResponseEntity<List<ProdutoHistorico>> response = restTemplate.exchange(
                "/api/historico/produto/1",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<ProdutoHistorico>>() {}
        );

        // Adicione logs para depuração
        System.out.println("Response Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody());

        // Verificações
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        assertEquals(historico.getNome(), response.getBody().get(0).getNome());
    }
}
