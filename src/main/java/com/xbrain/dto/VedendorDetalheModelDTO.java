package com.xbrain.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Setter
@Getter
public class VedendorDetalheModelDTO {
    private Long vendedorId;
    private String vendedorNome;
    private BigDecimal vendasTotal ;
    private BigDecimal vendasMediaDiaria;
    private List<VendaDetalheModelDTO> vendas;
}
