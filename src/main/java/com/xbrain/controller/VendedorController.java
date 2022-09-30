package com.xbrain.controller;

import com.xbrain.domain.model.Venda;
import com.xbrain.domain.model.Vendedor;
import com.xbrain.domain.service.CadastroVendedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vendedores")
public class VendedorController {

    @Autowired
    private CadastroVendedorService cadastroVendedor;

    @GetMapping
    public List<Vendedor> buscar(){
        return cadastroVendedor.buscarTodos();
    }

    @GetMapping("/{vendedorId}")
    public Vendedor buscarPorId(@PathVariable Long vendedorId) {
        return cadastroVendedor.buscarOuFalhar(vendedorId);
    }

}
