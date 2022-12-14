package com.xbrain.domain.service;

import com.xbrain.domain.model.Venda;
import com.xbrain.domain.model.Vendedor;
import com.xbrain.domain.repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CadastroVendaService {

    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private CadastroVendedorService cadastroVendedor;

    public List<Venda> buscarTodas(){
        return  vendaRepository.findAll();
    }

    public Venda salvar(Venda venda){
        Long vendedorId = venda.getVendedor().getVendedorId();
        Vendedor vendedor = cadastroVendedor.buscarOuFalhar(vendedorId);
        venda.setVendedor(vendedor);
        return vendaRepository.save(venda);
    }
}
