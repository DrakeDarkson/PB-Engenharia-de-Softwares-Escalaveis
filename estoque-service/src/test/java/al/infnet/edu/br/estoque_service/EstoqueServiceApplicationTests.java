package al.infnet.edu.br.estoque_service;

import al.infnet.edu.br.estoque_service.model.Estoque;
import al.infnet.edu.br.estoque_service.service.EstoqueService;
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

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class EstoqueServiceApplicationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Mock
    private EstoqueService estoqueService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void contextLoads() {
    }

    @Test
    void testListarEstoques() {
        Estoque estoque = new Estoque(1L, 1L, 100);
        when(estoqueService.listarEstoques()).thenReturn(Collections.singletonList(estoque));

        ResponseEntity<Estoque[]> response = restTemplate.getForEntity("/api/estoques", Estoque[].class);
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().length);
        assertEquals(estoque.getId(), response.getBody()[0].getId());
    }

    @Test
    void testObterEstoquePorId() {
        Estoque estoque = new Estoque(1L, 1L, 100);
        when(estoqueService.obterEstoque(anyLong())).thenReturn(estoque);

        ResponseEntity<Estoque> response = restTemplate.getForEntity("/api/estoques/1", Estoque.class);
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(estoque.getId(), response.getBody().getId());
    }

    @Test
    void testObterEstoquePorProdutoId() {
        Estoque estoque = new Estoque(1L, 1L, 100);
        when(estoqueService.obterEstoquePorProdutoId(anyLong())).thenReturn(estoque);

        ResponseEntity<Estoque> response = restTemplate.getForEntity("/api/estoques/produto/1", Estoque.class);
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(estoque.getProdutoId(), response.getBody().getProdutoId());
    }
    
    @Test
    void testAdicionarEstoque() {
        Estoque estoque = new Estoque(1L, 1L, 100);
        when(estoqueService.adicionarEstoque(any(Estoque.class))).thenReturn(estoque);

        ResponseEntity<Estoque> response = restTemplate.postForEntity("/api/estoques", estoque, Estoque.class);
        
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(estoque.getId(), response.getBody().getId());
    }

    @Test
    void testAtualizarEstoque() {
        Estoque estoque = new Estoque(1L, 1L, 100);
        when(estoqueService.atualizarEstoque(anyLong(), any(Estoque.class))).thenReturn(estoque);

        ResponseEntity<Estoque> response = restTemplate.exchange(
            "/api/estoques/1", 
            HttpMethod.PUT, 
            new HttpEntity<>(estoque), 
            Estoque.class
        );
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(estoque.getId(), response.getBody().getId());
    }

    @Test
    void testExcluirEstoque() {
        when(estoqueService.excluirEstoque(anyLong())).thenReturn(true);

        ResponseEntity<Void> response = restTemplate.exchange(
            "/api/estoques/1", 
            HttpMethod.DELETE, 
            null, 
            Void.class
        );
        
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}
