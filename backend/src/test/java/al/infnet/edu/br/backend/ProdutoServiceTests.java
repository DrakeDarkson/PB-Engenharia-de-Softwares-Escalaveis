package al.infnet.edu.br.backend;

import al.infnet.edu.br.backend.client.EstoqueClient;
import al.infnet.edu.br.backend.dto.EstoqueDTO;
import al.infnet.edu.br.backend.dto.ProdutoDTO;
import al.infnet.edu.br.backend.model.Produto;
import al.infnet.edu.br.backend.model.ProdutoHistorico;
import al.infnet.edu.br.backend.repository.ProdutoHistoricoRepository;
import al.infnet.edu.br.backend.repository.ProdutoRepository;
import al.infnet.edu.br.backend.service.ProdutoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class ProdutoServiceTests {

    @Mock
    private ProdutoRepository produtoRepository;

    @Mock
    private ProdutoHistoricoRepository produtoHistoricoRepository;

    @Mock
    private EstoqueClient estoqueClient;

    @InjectMocks
    private ProdutoService produtoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveProduto() {
        Produto produto = new Produto();
        produto.setId(1L);
        produto.setNome("Produto1");
        produto.setDescricao("Descricao1");
        produto.setPreco(100.0);
        produto.setCategoria("Categoria1");

        when(produtoRepository.save(any(Produto.class))).thenReturn(produto);
        when(estoqueClient.adicionarEstoque(any(EstoqueDTO.class))).thenReturn(new EstoqueDTO());

        Produto savedProduto = produtoService.save(produto);

        verify(produtoRepository, times(1)).save(produto);
        verify(produtoHistoricoRepository, times(1)).save(any(ProdutoHistorico.class));
        verify(estoqueClient, times(1)).adicionarEstoque(any(EstoqueDTO.class));
    }

    @Test
    void testFindAll() {
        when(produtoRepository.findAll()).thenReturn(Collections.emptyList());

        List<Produto> produtos = produtoService.findAll();

        verify(produtoRepository, times(1)).findAll();
        assertTrue(produtos.isEmpty());
    }

    @Test
    void testFindById() {
        Produto produto = new Produto();
        produto.setId(1L);

        when(produtoRepository.findById(anyLong())).thenReturn(Optional.of(produto));

        Produto foundProduto = produtoService.findById(1L);

        verify(produtoRepository, times(1)).findById(1L);
        assertNotNull(foundProduto);
    }

    @Test
    void testDeleteById() {
        Produto produto = new Produto();
        produto.setId(1L);

        when(produtoRepository.findById(anyLong())).thenReturn(Optional.of(produto));

        produtoService.deleteById(1L);

        verify(produtoRepository, times(1)).deleteById(1L);
        verify(produtoHistoricoRepository, times(1)).save(any(ProdutoHistorico.class));
    }

    @Test
    void testUpdateProduto() {
        Produto produtoOriginal = new Produto();
        produtoOriginal.setId(1L);
        produtoOriginal.setNome("Produto1");
        produtoOriginal.setDescricao("Descricao1");
        produtoOriginal.setPreco(100.0);
        produtoOriginal.setCategoria("Categoria1");

        Produto produtoAtualizado = new Produto();
        produtoAtualizado.setId(1L);
        produtoAtualizado.setNome("Produto Atualizado");
        produtoAtualizado.setDescricao("Descricao Atualizada");
        produtoAtualizado.setPreco(150.0);
        produtoAtualizado.setCategoria("Categoria Atualizada");

        when(produtoRepository.findById(anyLong())).thenReturn(Optional.of(produtoOriginal));
        when(produtoRepository.save(any(Produto.class))).thenReturn(produtoAtualizado);

        Produto produto = produtoService.findById(1L);
        produto.setNome("Produto Atualizado");
        produto.setDescricao("Descricao Atualizada");
        produto.setPreco(150.0);
        produto.setCategoria("Categoria Atualizada");

        Produto updatedProduto = produtoService.save(produto);

        verify(produtoRepository, times(1)).save(produto);
        verify(produtoHistoricoRepository, times(1)).save(any(ProdutoHistorico.class));
        assertNotNull(updatedProduto);
        assertEquals("Produto Atualizado", updatedProduto.getNome());
        assertEquals("Descricao Atualizada", updatedProduto.getDescricao());
        assertEquals(150.0, updatedProduto.getPreco());
        assertEquals("Categoria Atualizada", updatedProduto.getCategoria());
    }

    @Test
    void testAdicionarProdutoComEstoque() {
        Produto produto = new Produto();
        produto.setId(1L);
        produto.setNome("Produto Novo");
        produto.setDescricao("Descricao Nova");
        produto.setPreco(200.0);
        produto.setCategoria("Categoria Nova");

        EstoqueDTO estoqueDTO = new EstoqueDTO();
        estoqueDTO.setProdutoId(1L);
        estoqueDTO.setQuantidade(50);

        when(produtoRepository.save(any(Produto.class))).thenReturn(produto);
        when(estoqueClient.adicionarEstoque(any(EstoqueDTO.class))).thenReturn(estoqueDTO);

        ProdutoDTO produtoDTO = new ProdutoDTO();
        produtoDTO.setNome("Produto Novo");
        produtoDTO.setDescricao("Descricao Nova");
        produtoDTO.setPreco(200.0);
        produtoDTO.setCategoria("Categoria Nova");
        produtoDTO.setQuantidadeInicial(50);

        ProdutoDTO resultado = produtoService.adicionarProduto(produtoDTO);

        verify(produtoRepository, times(1)).save(any(Produto.class));
        verify(estoqueClient, times(1)).adicionarEstoque(any(EstoqueDTO.class));
        assertEquals("Produto Novo", resultado.getNome());
    }

    @Test
    void testConsultarEstoque() {
        EstoqueDTO estoqueDTO = new EstoqueDTO();
        estoqueDTO.setProdutoId(1L);
        estoqueDTO.setQuantidade(100);

        when(estoqueClient.obterEstoque(anyLong())).thenReturn(estoqueDTO);

        EstoqueDTO resultado = produtoService.consultarEstoque(1L);

        verify(estoqueClient, times(1)).obterEstoque(1L);
        assertEquals(100, resultado.getQuantidade());
    }

    @Test
    void testAtualizarEstoque() {
        EstoqueDTO estoqueDTO = new EstoqueDTO();
        estoqueDTO.setProdutoId(1L);
        estoqueDTO.setQuantidade(200);

        when(estoqueClient.atualizarEstoque(anyLong(), anyInt())).thenReturn(estoqueDTO);

        EstoqueDTO resultado = produtoService.atualizarEstoque(1L, 200);

        verify(estoqueClient, times(1)).atualizarEstoque(1L, 200);
        assertEquals(200, resultado.getQuantidade());
    }
}
