package com.app.dao;

import com.app.model.entity.Usuario;
import java.util.List;

/**
 * Extiende GenericDAO añadiendo búsquedas específicas de Usuario.
 */
public interface UsuarioDAO extends GenericDAO<Usuario, Integer> {
    List<Usuario> findByNombre(String nombre);
    boolean       existsByEmail(String email);
}