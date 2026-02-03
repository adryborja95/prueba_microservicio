package com.example.prueba.repository;

import com.example.prueba.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UsuarioRepository  extends JpaRepository<Usuario, UUID> {
}
