package com.xbrain.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
public class VendaModelDTO {

    private Long vendaId;
    private BigDecimal vendaValor;
    private OffsetDateTime vendaData;
    private VendaVendedorIdNomeModelDTO vendedor;

}
