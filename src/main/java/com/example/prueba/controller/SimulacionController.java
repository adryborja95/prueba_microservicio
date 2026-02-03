package com.example.prueba.controller;

import com.example.prueba.dto.SimulacionRequestDTO;
import com.example.prueba.dto.SimulacionResponseDTO;
import com.example.prueba.service.SimulacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/simulaciones")
@RequiredArgsConstructor
public class SimulacionController {

    private final SimulacionService simulacionService;

    // POST /simulaciones
    @PostMapping
    public ResponseEntity<?> simularInversion(
            @RequestBody SimulacionRequestDTO request
    ) {
        try {
            SimulacionResponseDTO response =
                    simulacionService.simularInversion(request);
            return ResponseEntity.ok(response);

        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // GET /simulaciones/{usuarioId}
    @GetMapping("/{usuarioId}")
    public List<SimulacionResponseDTO> listarSimulacionesPorUsuario(
            @PathVariable UUID usuarioId
    ) {
        return simulacionService.listarSimulacionesPorUsuario(usuarioId);
    }

}
