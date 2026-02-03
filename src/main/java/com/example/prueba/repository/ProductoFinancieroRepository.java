package com.example.prueba.repository;

import com.example.prueba.entity.ProductoFinanciero;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProductoFinancieroRepository extends JpaRepository<ProductoFinanciero, UUID> {
    List<ProductoFinanciero> findByActivoTrue();
}
