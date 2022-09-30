package com.xbrain.domain.service;

import com.xbrain.domain.model.Venda;
import com.xbrain.domain.model.Vendedor;
import com.xbrain.domain.repository.VendaRepository;
import com.xbrain.domain.repository.VendedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

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
        Long vendedorId = venda.getVendedor().getId();
        Vendedor vendedor = cadastroVendedor.buscarOuFalhar(vendedorId);
        venda.setVendedor(vendedor);
        return vendaRepository.save(venda);
    }

    public List<Venda> listAllVendaVendedorById(Long id){
        return vendaRepository.findAllvendaByVendedor_Id(id);
    }
}
