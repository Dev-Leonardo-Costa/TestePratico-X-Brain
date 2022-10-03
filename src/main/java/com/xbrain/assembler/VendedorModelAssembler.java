package com.xbrain.assembler;

import com.xbrain.domain.model.Vendedor;
import com.xbrain.dto.VedendorDetalheModelDTO;
import com.xbrain.dto.VendedorAddModelDTO;
import com.xbrain.dto.VendedorIdNomeModelDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class VendedorModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public VedendorDetalheModelDTO toModelVendedor(Vendedor vendedor){
       return modelMapper.map(vendedor, VedendorDetalheModelDTO.class);
    }
    public VendedorIdNomeModelDTO toDetalheModelVendedor(Vendedor vendedor){
        return modelMapper.map(vendedor, VendedorIdNomeModelDTO.class);
    }
    public Vendedor toDomainObject(VendedorAddModelDTO vendedorAddModelDTO) {
        return  modelMapper.map(vendedorAddModelDTO, Vendedor.class);
    }

    public List<VedendorDetalheModelDTO> toCollectionModelVendedorDTO(List<Vendedor> vendedores){
        return  vendedores.stream()
                .map(vendedor -> toModelVendedor(vendedor))
                .collect(Collectors.toList());
    }
}
