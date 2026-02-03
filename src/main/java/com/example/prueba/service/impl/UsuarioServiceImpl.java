package com.example.prueba.service.impl;

import com.example.prueba.dto.UsuarioDTO;
import com.example.prueba.entity.Usuario;
import com.example.prueba.repository.UsuarioRepository;
import com.example.prueba.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {
    private final UsuarioRepository usuarioRepository;

    @Override
    public UsuarioDTO crearUsuario(UsuarioDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setNombre(dto.getNombre());
        usuario.setEmail(dto.getEmail());
        usuario.setCapitalDisponible(dto.getCapitalDisponible());

        Usuario guardado = usuarioRepository.save(usuario);
        dto.setId(guardado.getId());
        return dto;
    }

    @Override
    public List<UsuarioDTO> listarUsuarios() {
        return usuarioRepository.findAll()
                .stream()
                .map(u -> new UsuarioDTO(
                        u.getId(),
                        u.getNombre(),
                        u.getEmail(),
                        u.getCapitalDisponible()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public UsuarioDTO obtenerUsuarioPorId(UUID id) {
        Usuario u = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        return new UsuarioDTO(
                u.getId(),
                u.getNombre(),
                u.getEmail(),
                u.getCapitalDisponible()
        );
    }

    @Override
    public UsuarioDTO actualizarUsuario(UUID id, UsuarioDTO dto) {
        Usuario u = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        u.setNombre(dto.getNombre());
        u.setEmail(dto.getEmail());
        u.setCapitalDisponible(dto.getCapitalDisponible());

        usuarioRepository.save(u);
        dto.setId(u.getId());
        return dto;
    }

    @Override
    public void eliminarUsuario(UUID id) {
        usuarioRepository.deleteById(id);
    }

}
