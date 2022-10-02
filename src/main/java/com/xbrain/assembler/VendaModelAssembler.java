package com.xbrain.assembler;

import com.xbrain.domain.model.Venda;
import com.xbrain.dto.VendaModelAddDTO;
import com.xbrain.dto.VendaModelDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class VendaModelAssembler {

    @Autowired
    private ModelMapper modelMapper;
    public VendaModelDTO toModelVenda(Venda venda){
        return  modelMapper.map(venda, VendaModelDTO.class);
    }

    public Venda toDomainObject(VendaModelAddDTO vendaModelAddDTO) {
        return  modelMapper.map(vendaModelAddDTO, Venda.class);
    }

    public List<VendaModelDTO> toCollectionModelVendaDTO(List<Venda> vendas){
        return  vendas.stream()
                .map(venda -> toModelVenda(venda))
                .collect(Collectors.toList());
    }
}