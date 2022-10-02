package com.xbrain.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class VendaModelAddDTO {

    private BigDecimal vendaValor;
    private VendedorIdModelDTO vendedor;
}