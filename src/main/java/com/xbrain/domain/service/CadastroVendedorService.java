package com.xbrain.domain.service;

import com.xbrain.domain.exception.VendedorNaoEncontradaException;
import com.xbrain.domain.model.Vendedor;
import com.xbrain.domain.repository.VendedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CadastroVendedorService {
    @Autowired
    private VendedorRepository vendedorRepository;


    public List<Vendedor> buscarTodos(){
        return vendedorRepository.findAll();
    }

    public Vendedor salvar(Vendedor vendedor){
        return  vendedorRepository.save(vendedor);
    }

    public Vendedor buscarOuFalhar(Long vendedorId){
        return vendedorRepository.findById(vendedorId)
                .orElseThrow(() -> new VendedorNaoEncontradaException(vendedorId));
    }

    public List<Vendedor> buscarPorNomeVendedor(String nome){
        return vendedorRepository.vendedorNome(nome);
    }

}
