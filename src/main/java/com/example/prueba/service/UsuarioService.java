package com.example.prueba.service;

import com.example.prueba.dto.UsuarioDTO;

import java.util.List;
import java.util.UUID;

public interface UsuarioService {
    UsuarioDTO crearUsuario(UsuarioDTO usuarioDTO);

    List<UsuarioDTO> listarUsuarios();

    UsuarioDTO obtenerUsuarioPorId(UUID id);

    UsuarioDTO actualizarUsuario(UUID id, UsuarioDTO usuarioDTO);

    void eliminarUsuario(UUID id);
}
