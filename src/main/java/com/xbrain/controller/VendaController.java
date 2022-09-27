package com.xbrain.controller;

import com.xbrain.domain.model.Venda;
import com.xbrain.domain.service.CadastroVendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vendas")
public class VendaController {
    @Autowired
    private CadastroVendaService cadastroVenda;

    @GetMapping
    public List<Venda> buscarTodas(){
        return  cadastroVenda.buscarTodas();
    }
}
