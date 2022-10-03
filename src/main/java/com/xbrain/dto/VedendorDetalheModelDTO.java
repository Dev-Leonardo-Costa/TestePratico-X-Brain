package com.xbrain.dto;

import com.xbrain.domain.model.Venda;
import com.xbrain.domain.repository.VendedorRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class VedendorDetalheModelDTO {

    @Autowired
    private VendedorRepository vendedorRepository;

    private Long vendedorId;
    private String vendedorNome;
    private float vendasTotal = vendedorRepository.getTotalVendas();
    private BigDecimal vendasMediaDiaria;
    private List<Venda> vendas = new ArrayList<>();
}
