package com.xbrain.assembler;

import com.xbrain.domain.model.Vendedor;
import com.xbrain.dto.VendedorModelDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class VendedorModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public VendedorModelDTO toModelVendedor(Vendedor vendedor){
       return modelMapper.map(vendedor, VendedorModelDTO.class);
    }

    public List<VendedorModelDTO> toCollectionModelVendedorDTO(List<Vendedor> vendedores){
        return  vendedores.stream()
                .map(vendedor -> toModelVendedor(vendedor))
                .collect(Collectors.toList());
    }
}
