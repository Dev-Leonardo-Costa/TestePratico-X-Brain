package com.xbrain.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class VendaModelDTO {

    private Long vendaId;
    private BigDecimal vendaValor;
    private LocalDate vendaData;
    private VendedorIdNomeModelDTO vendedor;

}
