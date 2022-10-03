package com.xbrain.domain.repository;

import com.xbrain.domain.model.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface VendedorRepository extends JpaRepository<Vendedor, Long> {
    @Query(value = "SELECT IFNULL(SUM(venda_valor),0) FROM xbrain.venda WHERE vendedor_id = '1'")
    float getTotalVendas();

}
