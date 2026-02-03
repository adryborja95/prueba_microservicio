package com.example.prueba.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductoDTO {

    private UUID id;

    @NotBlank
    @Size(min = 1, max = 100)
    private String nombre;

    @Size(max = 500)
    private String descripcion;

    @NotNull
    @DecimalMin(value = "0.00", inclusive = true)
    @Digits(integer = 8, fraction = 2)
    private BigDecimal costo;

    @NotNull
    @DecimalMin(value = "0.00", inclusive = false)
    @Digits(integer = 3, fraction = 2)
    private BigDecimal porcentajeRetorno;

    @NotNull
    private Boolean activo;
}
