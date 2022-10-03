package com.xbrain.domain.repository;

import com.xbrain.domain.model.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface VendedorRepository extends JpaRepository<Vendedor, Long>{
}
