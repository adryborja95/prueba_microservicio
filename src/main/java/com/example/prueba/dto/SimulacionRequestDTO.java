package com.example.prueba.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimulacionRequestDTO {
    @NotNull
    private UUID usuarioId;

    @NotNull
    @DecimalMin(value = "0.00", inclusive = false)
    @Digits(integer = 8, fraction = 2)
    private BigDecimal capitalDisponible;

    @NotEmpty
    private List<Map<String, Object>> productos;
}
