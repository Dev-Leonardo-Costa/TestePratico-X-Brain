package com.xbrain.domain.service;

import com.xbrain.domain.exception.EntidadeNaoEncontradaException;
import com.xbrain.domain.model.Vendedor;
import com.xbrain.domain.repository.VendaRepository;
import com.xbrain.domain.repository.VendedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CadastroVendedorService {
    public static final String MSG_VENDEDOR_NAO_ENCONTRADA
            = "Não existe cadastro de vendedor com esse código %d ";
    @Autowired
    private VendedorRepository vendedorRepository;

    @Autowired
    private VendaRepository vendaRepository;

    public List<Vendedor> buscarTodos(){
        return vendedorRepository.findAll();
    }

    public Vendedor buscarOuFalhar(Long vendedorId){
        return vendedorRepository.findById(vendedorId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        String.format(MSG_VENDEDOR_NAO_ENCONTRADA, vendedorId)));
    }

}
