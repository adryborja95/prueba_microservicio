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
public class UsuarioDTO {

    private UUID id;

    @NotBlank
    @Size(min = 1, max = 50)
    private String nombre;

    @NotBlank
    @Email
    @Size(max = 100)
    private String email;

    @NotNull
    @DecimalMin(value = "0.00", inclusive = true)
    @Digits(integer = 8, fraction = 2)
    private BigDecimal capitalDisponible;
}
