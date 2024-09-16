package al.infnet.edu.br.estoque_service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import al.infnet.edu.br.estoque_service.model.Estoque;
import al.infnet.edu.br.estoque_service.repository.EstoqueRepository;
import al.infnet.edu.br.estoque_service.service.EstoqueService;

import java.util.Optional;

public class EstoqueServiceApplicationTests {

    @Mock
    private EstoqueRepository estoqueRepository;

    @InjectMocks
    private EstoqueService estoqueService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void contextLoads() {
    }

    @Test
    public void testCriarEstoque() {
        Estoque estoque = new Estoque(null, 1L, 10);
        when(estoqueRepository.save(any(Estoque.class))).thenReturn(estoque);

        Estoque resultado = estoqueService.criarEstoque(estoque);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getProdutoId());
        assertEquals(10, resultado.getQuantidade());
        verify(estoqueRepository, times(1)).save(estoque);
    }

    @Test
    public void testBuscarEstoquePorId() {
        Estoque estoque = new Estoque(1L, 1L, 10);
        when(estoqueRepository.findById(1L)).thenReturn(Optional.of(estoque));

        Optional<Estoque> resultado = estoqueService.buscarEstoquePorId(1L);

        assertTrue(resultado.isPresent());
        assertEquals(1L, resultado.get().getId());
        assertEquals(1L, resultado.get().getProdutoId());
        assertEquals(10, resultado.get().getQuantidade());
        verify(estoqueRepository, times(1)).findById(1L);
    }

    @Test
    public void testBuscarEstoquePorProdutoId() {
        Estoque estoque = new Estoque(1L, 1L, 10);
        when(estoqueRepository.findByProdutoId(1L)).thenReturn(Optional.of(estoque));

        Optional<Estoque> resultado = estoqueService.buscarEstoquePorProdutoId(1L);

        assertTrue(resultado.isPresent());
        assertEquals(1L, resultado.get().getProdutoId());
        assertEquals(10, resultado.get().getQuantidade());
        verify(estoqueRepository, times(1)).findByProdutoId(1L);
    }

    @Test
    public void testAtualizarEstoque() {
        Estoque estoqueExistente = new Estoque(1L, 1L, 10);
        Estoque estoqueAtualizado = new Estoque(1L, 1L, 20);
        when(estoqueRepository.existsById(1L)).thenReturn(true);
        when(estoqueRepository.save(any(Estoque.class))).thenReturn(estoqueAtualizado);

        Estoque resultado = estoqueService.atualizarEstoque(1L, estoqueAtualizado);

        assertNotNull(resultado);
        assertEquals(20, resultado.getQuantidade());
        verify(estoqueRepository, times(1)).save(estoqueAtualizado);
    }

    @Test
    public void testDeletarEstoque() {
        doNothing().when(estoqueRepository).deleteById(1L);

        estoqueService.deletarEstoque(1L);

        verify(estoqueRepository, times(1)).deleteById(1L);
    }
}
