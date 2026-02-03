package com.example.prueba.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "simulaciones")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Simulacion {

    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @Column(name = "fecha_simulacion", nullable = false)
    private LocalDateTime fechaSimulacion;

    @Column(name = "capital_disponible", nullable = false, precision = 10, scale = 2)
    private BigDecimal capitalDisponible;

    @Column(name = "ganancia_total", nullable = false, precision = 10, scale = 2)
    private BigDecimal gananciaTotal;

    @ManyToMany
    @JoinTable(
            name = "simulacion_productos",
            joinColumns = @JoinColumn(name = "simulacion_id"),
            inverseJoinColumns = @JoinColumn(name = "producto_id")
    )
    private List<ProductoFinanciero> productosSeleccionados;
}
