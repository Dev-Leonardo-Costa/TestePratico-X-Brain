package com.xbrain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Setter
@Getter
public class VendaDetalheModelDTO {
    private Long vendaId;
    private BigDecimal vendaValor;
    @JsonFormat(pattern = "dd/MM/yyyy[ HH:mm:ss]")
    private LocalDate vendaData;
}
