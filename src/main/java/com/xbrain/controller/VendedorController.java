package com.xbrain.controller;

import com.xbrain.assembler.VendedorModelAssembler;
import com.xbrain.domain.exception.NegocioException;
import com.xbrain.domain.exception.VendedorNaoEncontradaException;
import com.xbrain.domain.model.Vendedor;
import com.xbrain.domain.service.CadastroVendedorService;
import com.xbrain.dto.VedendorDetalheModelDTO;
import com.xbrain.dto.VendedorAddModelDTO;
import com.xbrain.dto.VendedorIdNomeModelDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/vendedores")
public class VendedorController {

    @Autowired
    private CadastroVendedorService cadastroVendedor;

    @Autowired
    private VendedorModelAssembler vendedorModelAssembler;

    @GetMapping
    public List<VedendorDetalheModelDTO> listar() {
        return vendedorModelAssembler.toCollectionModelVendedorDTO(cadastroVendedor.buscarTodos());
    }

    @GetMapping("/{vendedorId}")
    public VedendorDetalheModelDTO buscar(@PathVariable Long vendedorId) {
        Vendedor vendedor = cadastroVendedor.buscarOuFalhar(vendedorId);
        return vendedorModelAssembler.toModelVendedor(vendedor);
    }

    @GetMapping("/buscar-por-nome-vendedor")
    public List<Vendedor> buscarPorNome(String nome) {
        return  cadastroVendedor.buscarPorNomeVendedor(nome);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VendedorIdNomeModelDTO adicionar(@RequestBody @Valid VendedorAddModelDTO vendedorAddModelDTO) {
        try {
            Vendedor vendedor = vendedorModelAssembler.toDomainObject(vendedorAddModelDTO);
            return vendedorModelAssembler.toDetalheModelVendedor(cadastroVendedor.salvar(vendedor));
        } catch (VendedorNaoEncontradaException exception) {
            throw new NegocioException(exception.getMessage(), exception);
        }
    }
}