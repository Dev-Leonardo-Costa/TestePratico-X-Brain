package com.xbrain.dto;

import com.xbrain.domain.model.Vendedor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


@Setter
@Getter
public class VendedorModelDTO {

    private Long vendedorId;
    private String vendedorNome;
    private BigDecimal  vendasTotal;
    private BigDecimal vendasMediaDiaria;

}
