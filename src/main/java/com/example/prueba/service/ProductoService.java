package com.example.prueba.service;

import com.example.prueba.dto.ProductoDTO;

import java.util.List;
import java.util.UUID;

public interface ProductoService {
    ProductoDTO crearProducto(ProductoDTO productoDTO);

    List<ProductoDTO> listarProductosActivos();

    ProductoDTO obtenerProductoPorId(UUID id);

    ProductoDTO actualizarProducto(UUID id, ProductoDTO productoDTO);

    void eliminarProducto(UUID id);
}
