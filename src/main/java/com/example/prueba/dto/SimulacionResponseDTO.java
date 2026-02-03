package com.example.prueba.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimulacionResponseDTO {
    private UUID id;
    private UUID usuarioId;
    private LocalDateTime fechaSimulacion;

    private BigDecimal capitalDisponible;

    private List<Map<String, Object>> productosSeleccionados;

    private BigDecimal costoTotal;
    private BigDecimal capitalRestante;
    private BigDecimal gananciaTotal;
    private BigDecimal retornoTotalPorcentaje;


    private BigDecimal eficienciaCapital;

    private String mensaje;
}
