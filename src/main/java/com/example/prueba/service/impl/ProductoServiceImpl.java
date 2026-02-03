package com.example.prueba.service.impl;

import com.example.prueba.dto.ProductoDTO;
import com.example.prueba.entity.ProductoFinanciero;
import com.example.prueba.repository.ProductoFinancieroRepository;
import com.example.prueba.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductoServiceImpl implements ProductoService {
    private final ProductoFinancieroRepository repository;

    @Override
    public ProductoDTO crearProducto(ProductoDTO dto) {
        ProductoFinanciero p = new ProductoFinanciero();
        p.setNombre(dto.getNombre());
        p.setDescripcion(dto.getDescripcion());
        p.setCosto(dto.getCosto());
        p.setPorcentajeRetorno(dto.getPorcentajeRetorno());
        p.setActivo(dto.getActivo());

        ProductoFinanciero guardado = repository.save(p);
        dto.setId(guardado.getId());
        return dto;
    }

    @Override
    public List<ProductoDTO> listarProductosActivos() {
        return repository.findByActivoTrue()
                .stream()
                .map(p -> new ProductoDTO(
                        p.getId(),
                        p.getNombre(),
                        p.getDescripcion(),
                        p.getCosto(),
                        p.getPorcentajeRetorno(),
                        p.getActivo()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public ProductoDTO obtenerProductoPorId(UUID id) {
        ProductoFinanciero p = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        return new ProductoDTO(
                p.getId(),
                p.getNombre(),
                p.getDescripcion(),
                p.getCosto(),
                p.getPorcentajeRetorno(),
                p.getActivo()
        );
    }

    @Override
    public ProductoDTO actualizarProducto(UUID id, ProductoDTO dto) {
        ProductoFinanciero p = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        p.setNombre(dto.getNombre());
        p.setDescripcion(dto.getDescripcion());
        p.setCosto(dto.getCosto());
        p.setPorcentajeRetorno(dto.getPorcentajeRetorno());
        p.setActivo(dto.getActivo());

        repository.save(p);
        dto.setId(p.getId());
        return dto;
    }

    @Override
    public void eliminarProducto(UUID id) {
        repository.deleteById(id);
    }
}
