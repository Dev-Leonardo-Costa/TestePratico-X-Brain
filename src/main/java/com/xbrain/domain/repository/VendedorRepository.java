package com.xbrain.domain.repository;

import com.xbrain.domain.model.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VendedorRepository extends JpaRepository<Vendedor, Long>{

    List<Vendedor> vendedorNome(String nome);

}
