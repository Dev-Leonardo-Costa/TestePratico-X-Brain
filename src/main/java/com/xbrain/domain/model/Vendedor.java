package com.xbrain.domain.model;


import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@Entity
public class Vendedor {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vendedorId;

    @NotNull
    private String vendedorNome;

    private BigDecimal  vendasTotal;

    private BigDecimal vendasMediaDiaria;

    @OneToMany(mappedBy = "vendedor")
    private List<Venda> vendas = new ArrayList<>();

}
