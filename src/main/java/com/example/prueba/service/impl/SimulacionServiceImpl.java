package com.example.prueba.service.impl;

import com.example.prueba.dto.SimulacionRequestDTO;
import com.example.prueba.dto.SimulacionResponseDTO;
import com.example.prueba.entity.ProductoFinanciero;
import com.example.prueba.entity.Simulacion;
import com.example.prueba.entity.Usuario;
import com.example.prueba.repository.ProductoFinancieroRepository;
import com.example.prueba.repository.SimulacionRepository;
import com.example.prueba.repository.UsuarioRepository;
import com.example.prueba.service.SimulacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class SimulacionServiceImpl implements SimulacionService {
    private final UsuarioRepository usuarioRepository;
    private final ProductoFinancieroRepository productoRepository;
    private final SimulacionRepository simulacionRepository;

    @Override
    public SimulacionResponseDTO simularInversion(SimulacionRequestDTO request) {

        //Obtener usuario
        Usuario usuario = usuarioRepository.findById(request.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        BigDecimal capital = request.getCapitalDisponible();

        //Parsear productos del request
        List<Map<String, Object>> productosRequest = request.getProductos();

        List<Map<String, Object>> productosViables = new ArrayList<>();

        BigDecimal productoMasBarato = null;

        for (Map<String, Object> p : productosRequest) {
            BigDecimal precio = new BigDecimal(p.get("precio").toString());

            if (productoMasBarato == null || precio.compareTo(productoMasBarato) < 0) {
                productoMasBarato = precio;
            }

            if (precio.compareTo(capital) <= 0) {
                productosViables.add(p);
            }
        }

        if (productosViables.isEmpty()) {

            BigDecimal diferenciaNecesaria = BigDecimal.ZERO;

            if (productoMasBarato != null) {
                diferenciaNecesaria = productoMasBarato.subtract(capital);
            }

            Map<String, Object> error = new HashMap<>();
            error.put("error", "Fondos insuficientes");
            error.put("detalle", "El capital disponible (" + capital + ") es insuficiente para adquirir cualquier producto de la lista.");
            error.put("capital_disponible", capital);
            error.put("producto_mas_barato", productoMasBarato);
            error.put("diferencia_necesaria", diferenciaNecesaria);

            throw new RuntimeException(error.toString());
        }

        //Generar combinaciones y buscar la óptima
        List<Map<String, Object>> mejorCombinacion = new ArrayList<>();
        BigDecimal mejorGanancia = BigDecimal.ZERO;
        BigDecimal mejorCosto = BigDecimal.ZERO;

        int n = productosViables.size();

        // Combinaciones por máscara binaria (2^n)
        for (int i = 1; i < (1 << n); i++) {

            BigDecimal costoTotal = BigDecimal.ZERO;
            BigDecimal gananciaTotal = BigDecimal.ZERO;
            List<Map<String, Object>> combinacionActual = new ArrayList<>();

            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) != 0) {
                    Map<String, Object> prod = productosViables.get(j);
                    BigDecimal precio = new BigDecimal(prod.get("precio").toString());
                    BigDecimal porcentaje = new BigDecimal(prod.get("porcentaje_ganancia").toString());

                    costoTotal = costoTotal.add(precio);

                    BigDecimal ganancia = precio
                            .multiply(porcentaje)
                            .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);

                    gananciaTotal = gananciaTotal.add(ganancia);
                    combinacionActual.add(prod);
                }
            }

            if (costoTotal.compareTo(capital) <= 0 &&
                    gananciaTotal.compareTo(mejorGanancia) > 0) {

                mejorGanancia = gananciaTotal;
                mejorCosto = costoTotal;
                mejorCombinacion = combinacionActual;
            }
        }

        //Construir productos seleccionados con ganancia
        List<Map<String, Object>> productosSeleccionados = new ArrayList<>();

        for (Map<String, Object> p : mejorCombinacion) {
            BigDecimal precio = new BigDecimal(p.get("precio").toString());
            BigDecimal porcentaje = new BigDecimal(p.get("porcentaje_ganancia").toString());

            BigDecimal ganancia = precio
                    .multiply(porcentaje)
                    .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);

            Map<String, Object> prod = new HashMap<>();
            prod.put("nombre", p.get("nombre"));
            prod.put("precio", precio);
            prod.put("porcentaje_ganancia", porcentaje);
            prod.put("ganancia_esperada", ganancia);

            productosSeleccionados.add(prod);
        }


        Simulacion simulacion = new Simulacion();
        simulacion.setUsuario(usuario);
        simulacion.setFechaSimulacion(LocalDateTime.now());
        simulacion.setCapitalDisponible(capital);
        simulacion.setGananciaTotal(mejorGanancia);

        List<ProductoFinanciero> productosEntidad = new ArrayList<>();
        for (Map<String, Object> p : mejorCombinacion) {
            productoRepository.findAll().stream()
                    .filter(prod -> prod.getNombre().equals(p.get("nombre")))
                    .findFirst()
                    .ifPresent(productosEntidad::add);
        }

        simulacion.setProductosSeleccionados(productosEntidad);
        Simulacion guardada = simulacionRepository.save(simulacion);

        //Construir respuesta
        BigDecimal capitalRestante = capital.subtract(mejorCosto);
        BigDecimal retornoPorcentaje = mejorGanancia
                .multiply(BigDecimal.valueOf(100))
                .divide(mejorCosto, 2, RoundingMode.HALF_UP);

        BigDecimal eficienciaCapital = mejorCosto
                .multiply(BigDecimal.valueOf(100))
                .divide(capital, 2, RoundingMode.HALF_UP);

        SimulacionResponseDTO response = new SimulacionResponseDTO();
        response.setId(guardada.getId());
        response.setUsuarioId(usuario.getId());
        response.setFechaSimulacion(guardada.getFechaSimulacion());
        response.setCapitalDisponible(capital);
        response.setProductosSeleccionados(productosSeleccionados);
        response.setCostoTotal(mejorCosto);
        response.setCapitalRestante(capitalRestante);
        response.setGananciaTotal(mejorGanancia);
        response.setRetornoTotalPorcentaje(retornoPorcentaje);
        response.setEficienciaCapital(eficienciaCapital);

        if (mejorGanancia.compareTo(BigDecimal.ZERO) > 0) {
            response.setMensaje("Simulación exitosa con ganancias óptimas");
        } else {
            response.setMensaje("Simulación con ganancias mínimas. Considere aumentar capital.");
        }

        return response;
    }

    @Override
    public List<SimulacionResponseDTO> listarSimulacionesPorUsuario(UUID usuarioId) {

        List<Simulacion> simulaciones = simulacionRepository.findByUsuarioId(usuarioId);

        List<SimulacionResponseDTO> response = new ArrayList<>();

        for (Simulacion s : simulaciones) {
            SimulacionResponseDTO dto = new SimulacionResponseDTO();
            dto.setId(s.getId());
            dto.setUsuarioId(usuarioId);
            dto.setFechaSimulacion(s.getFechaSimulacion());
            dto.setCapitalDisponible(s.getCapitalDisponible());
            dto.setGananciaTotal(s.getGananciaTotal());
            dto.setMensaje("Simulación registrada");

            response.add(dto);
        }

        return response;
    }
}
