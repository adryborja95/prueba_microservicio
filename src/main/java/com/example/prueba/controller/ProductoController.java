package com.example.prueba.controller;

import com.example.prueba.dto.ProductoDTO;
import com.example.prueba.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
@RequiredArgsConstructor
public class ProductoController {
    private final ProductoService productoService;

    // GET /productos
    @GetMapping
    public List<ProductoDTO> listarProductosActivos() {
        return productoService.listarProductosActivos();
    }
}
