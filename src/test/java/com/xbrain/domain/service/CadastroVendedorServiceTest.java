package com.xbrain.domain.service;

import com.xbrain.domain.exception.VendedorNaoEncontradaException;
import com.xbrain.domain.model.Vendedor;
import com.xbrain.domain.repository.VendedorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class CadastroVendedorServiceTest {

    public static final long   VENDEDOR_ID             = 6L;
    public static final String TESTE_NOME_VENDEDOR     = "Teste testando";
    public static final String VENDEDOR_NAO_ENCONTRADO = "Não existe cadastro de vendedor com esse código %d";
    public static final int INDEX = 0;

    @InjectMocks
    private CadastroVendedorService cadastroVendedor;

    @Mock
    private VendedorRepository repository;

    private Vendedor vendedor;
    private Optional<Vendedor> optionalVendedor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startVendedor();
    }

    @Test
    @DisplayName("deve listar todos os vendedores cadastrado")
    void deveBuscar_TodosVendedores() {
        when(repository.findAll()).thenReturn(List.of(vendedor));

        List<Vendedor> resposta = cadastroVendedor.buscarTodos();

        assertNotNull(resposta);
        assertEquals(1, resposta.size());
        assertEquals(Vendedor.class, resposta.get(INDEX).getClass());
        assertEquals(VENDEDOR_ID, resposta.get(INDEX).getVendedorId());
        assertEquals(TESTE_NOME_VENDEDOR, resposta.get(INDEX).getVendedorNome());
    }

    @Test
    @DisplayName("deve adicionar sempre um vendedor")
    void deveSalvar_VendedorComSucesso() {
        when(repository.save(any())).thenReturn(vendedor);

        Vendedor resposta = cadastroVendedor.salvar(vendedor);

        assertNotNull(resposta);
        assertEquals(Vendedor.class, resposta.getClass());
        assertEquals(VENDEDOR_ID, resposta.getVendedorId());
        assertEquals(TESTE_NOME_VENDEDOR, resposta.getVendedorNome());
    }

    @Test
    @DisplayName("deve buscar pelo o id do vendedor")
    void deveBuscar_VendedorPorId() {
        when(repository.findById(anyLong()))
                .thenReturn(optionalVendedor);

        Vendedor resposta = cadastroVendedor.buscarOuFalhar(VENDEDOR_ID);

        assertNotNull(resposta);
        assertEquals(Vendedor.class, resposta.getClass());
        assertEquals(VENDEDOR_ID, resposta.getVendedorId());
        assertEquals(TESTE_NOME_VENDEDOR, resposta.getVendedorNome());
    }

    @Test
    @DisplayName("deve lançar uma exceção que não existe um vendedor com o id informado")
    void deveRetornar_VendedorNaoEncontrado(){
        when(repository.findById(anyLong())).thenThrow(new VendedorNaoEncontradaException(VENDEDOR_NAO_ENCONTRADO));

        try {
            cadastroVendedor.buscarOuFalhar(VENDEDOR_ID);
        }catch (Exception exception){
            assertEquals(VendedorNaoEncontradaException.class, exception.getClass());
            assertEquals(VENDEDOR_NAO_ENCONTRADO, exception.getMessage());
        }
    }

    @Test
    @DisplayName("deve buscar um vendedor pelo nome")
    void deveBuscar_PorNomeVendedor() {
        when(repository.findById(anyLong()))
                .thenReturn(optionalVendedor);

        Vendedor resposta = cadastroVendedor.buscarOuFalhar(VENDEDOR_ID);

        assertNotNull(resposta);
        assertEquals(TESTE_NOME_VENDEDOR, resposta.getVendedorNome());
    }

    private void startVendedor(){
      vendedor = new Vendedor(VENDEDOR_ID, TESTE_NOME_VENDEDOR);
      optionalVendedor = Optional.of(new Vendedor(VENDEDOR_ID, TESTE_NOME_VENDEDOR));
    }
}