package com.xbrain.controller;

import com.xbrain.assembler.VendedorModelAssembler;
import com.xbrain.domain.model.Vendedor;
import com.xbrain.domain.service.CadastroVendedorService;
import com.xbrain.dto.VendedorModelDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/vendedores")
public class VendedorController {

    @Autowired
    private CadastroVendedorService cadastroVendedor;

    @Autowired
    private VendedorModelAssembler vendedorModelAssembler;

    @GetMapping
    public List<VendedorModelDTO> buscar(){
        return vendedorModelAssembler.toCollectionModelVendedorDTO(cadastroVendedor.buscarTodos());
    }

    @GetMapping("/{vendedorId}")
    public VendedorModelDTO buscarPorId(@PathVariable Long vendedorId) {
        Vendedor vendedor = cadastroVendedor.buscarOuFalhar(vendedorId);
        return vendedorModelAssembler.toModelVendedor(vendedor);
    }

}
