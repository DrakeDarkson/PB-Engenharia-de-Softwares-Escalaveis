package al.infnet.edu.br.produto_service;

import al.infnet.edu.br.produto_service.model.Produto;
import al.infnet.edu.br.produto_service.repository.ProdutoRepository;
import al.infnet.edu.br.produto_service.service.ProdutoService;
import al.infnet.edu.br.eventlibrary.events.ProdutoCriadoEvent;
import al.infnet.edu.br.eventlibrary.events.ProdutoRemovidoEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ProdutoServiceApplicationTests {

    @Mock
    private ProdutoRepository produtoRepository;

    @Mock
    private AmqpTemplate amqpTemplate;

    @InjectMocks
    private ProdutoService produtoService;

    private Produto produto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        produto = new Produto();
        produto.setId(1L);
        produto.setNome("Produto Teste");
        produto.setDescricao("Descrição do produto teste");
        produto.setPreco(100.0);
        produto.setCategoria("Categoria Teste");
    }

    @Test
    void contextLoads() {
    }

    @Test
    void testFindAll() {
        when(produtoRepository.findAll()).thenReturn(List.of(produto));

        List<Produto> produtos = produtoService.findAll();

        assertNotNull(produtos);
        assertEquals(1, produtos.size());
        assertEquals("Produto Teste", produtos.get(0).getNome());
    }

    @Test
    void testSaveProduto() {
        when(produtoRepository.save(any(Produto.class))).thenReturn(produto);

        Produto savedProduto = produtoService.save(produto);

        assertNotNull(savedProduto);
        assertEquals("Produto Teste", savedProduto.getNome());
        verify(amqpTemplate, times(1)).convertAndSend(eq("product.exchange"), eq("product.created"), any(ProdutoCriadoEvent.class));
    }

    @Test
    void testFindById() {
        when(produtoRepository.findById(1L)).thenReturn(Optional.of(produto));

        Produto foundProduto = produtoService.findById(1L);

        assertNotNull(foundProduto);
        assertEquals("Produto Teste", foundProduto.getNome());
    }

    @Test
    void testDeleteById() {
        when(produtoRepository.findById(1L)).thenReturn(Optional.of(produto));
        doNothing().when(produtoRepository).deleteById(1L);

        produtoService.deleteById(1L);

        verify(produtoRepository, times(1)).deleteById(1L);
        verify(amqpTemplate, times(1)).convertAndSend(eq("product.exchange"), eq("product.deleted"), any(ProdutoRemovidoEvent.class));
    }
}
