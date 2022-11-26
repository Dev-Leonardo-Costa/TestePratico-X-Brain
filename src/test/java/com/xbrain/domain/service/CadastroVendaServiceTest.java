package com.xbrain.domain.service;

import com.xbrain.domain.model.Venda;
import com.xbrain.domain.model.Vendedor;
import com.xbrain.domain.repository.VendaRepository;
import com.xbrain.domain.repository.VendedorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class CadastroVendaServiceTest {

    public static final long   VENDEDOR_ID             = 100L;
    public static final String TESTE_NOME_VENDEDOR     = "Teste testando";
    public static final long VENDA_ID                  = 600L;
    public static final int INDEX = 0;

    private Vendedor vendedor;
    private Optional<Vendedor> optionalVendedor;
    private Venda venda;


    @InjectMocks
    private CadastroVendaService cadastroVenda;

    @InjectMocks
    private CadastroVendedorService cadastroVendedor;

    @Mock
    private VendedorRepository vendedorRepository;

    @Mock
    private VendaRepository vendaRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startVendedor();
        startVenda();
    }

    @Test
    @DisplayName("deve listar todas as vendas dos vendedores")
    void deveBuscar_TodasVendas() {
        when(vendaRepository.findAll()).thenReturn(List.of(venda));

        List<Venda> resposta = cadastroVenda.buscarTodas();

        assertNotNull(resposta);
        assertEquals(1, resposta.size());
        assertEquals(Venda.class, resposta.get(INDEX).getClass());
        assertEquals(VENDA_ID, resposta.get(INDEX).getVendaId());
        assertEquals(venda.getVendaValor(), resposta.get(INDEX).getVendaValor());
        assertEquals(vendedor, resposta.get(INDEX).getVendedor());
    }

    @Test
    void deveSalvar_UmaVendaComSucesso() {
        when(vendaRepository.save(any())).thenReturn(venda);

        Venda respostaVenda = cadastroVenda.salvar(venda);

        assertEquals(Venda.class, respostaVenda.getClass());
    }

    private void startVendedor(){
        vendedor = new Vendedor(VENDEDOR_ID, TESTE_NOME_VENDEDOR);
        optionalVendedor = Optional.of(new Vendedor(VENDEDOR_ID, TESTE_NOME_VENDEDOR));
    }

    private void startVenda(){
        venda = new Venda(VENDA_ID, new BigDecimal(800), vendedor);
        cadastroVendedor.buscarOuFalhar(VENDEDOR_ID);
    }
}