package com.xbrain.domain.service;

import com.xbrain.domain.model.Venda;
import com.xbrain.domain.repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Service
public class CadastroVendaService {

    @Autowired
    private VendaRepository vendaRepository;

    public List<Venda> buscarTodas(){
        return  vendaRepository.findAll();
    }
}
