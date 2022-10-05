package com.xbrain.domain.repository;

import com.xbrain.domain.model.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Long> {

    List<Venda> vendaDataBetween(LocalDate dataInicial, LocalDate dataFinal);
}