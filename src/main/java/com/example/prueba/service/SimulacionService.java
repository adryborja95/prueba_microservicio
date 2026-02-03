package com.example.prueba.service;

import com.example.prueba.dto.SimulacionRequestDTO;
import com.example.prueba.dto.SimulacionResponseDTO;

import java.util.List;
import java.util.UUID;

public interface SimulacionService {
    SimulacionResponseDTO simularInversion(SimulacionRequestDTO request);

    List<SimulacionResponseDTO> listarSimulacionesPorUsuario(UUID usuarioId);
}
