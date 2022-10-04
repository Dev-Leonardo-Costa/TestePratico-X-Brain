package com.xbrain.domain.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xbrain.Grups;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;
import java.math.BigDecimal;
import java.time.LocalDate;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@Entity
public class Venda {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vendaId;

    @PositiveOrZero
    private BigDecimal vendaValor;

    @CreationTimestamp
    @Column(nullable = false)
    @JsonFormat(pattern = "dd/MM/yyyy[ HH:mm:ss]")
    private LocalDate vendaData;

    @Valid
    @ConvertGroup(from = Default.class, to = Grups.VendedorID.class)
    @NotNull
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "vendedor_id", nullable = false)
    private Vendedor vendedor;
}
