package com.xbrain.controller;

import com.xbrain.domain.model.Venda;
import com.xbrain.domain.service.CadastroVendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vendas")
public class VendaController {
    @Autowired
    private CadastroVendaService cadastroVenda;

    @GetMapping
    public List<Venda> buscarTodas(){
        return  cadastroVenda.buscarTodas();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Venda adicionar(@RequestBody Venda venda){
        return  cadastroVenda.salvar(venda);
    }
//    @GetMapping("/{id}")
//    public Optional<Venda> listAllVendasVendedorId(@PathVariable Long id){
//        return  cadastroVenda.listAllVendaVendedorById(id);
//    }
}
