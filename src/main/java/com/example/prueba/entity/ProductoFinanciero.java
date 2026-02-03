package com.example.prueba.entity;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "productos_financieros")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductoFinanciero {

    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal costo;

    @Column(name = "porcentaje_retorno", nullable = false, precision = 5, scale = 2)
    private BigDecimal porcentajeRetorno;

    @Column(nullable = false)
    private Boolean activo;


}
