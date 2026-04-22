package com.app.controller;

import com.app.dao.UsuarioDAO;
import com.app.model.entity.Usuario;
import com.app.view.View;

import java.util.List;
import java.util.Optional;

/**
 * Controlador de usuarios.
 * Recibe eventos de la Vista, orquesta el DAO y devuelve resultados a la Vista.
 * No sabe si la Vista es consola o Swing → depende de la interfaz View.
 */
public class UsuarioController {

    private final View       view;
    private final UsuarioDAO usuarioDAO;

    // Inyección de dependencias por constructor
    public UsuarioController(View view, UsuarioDAO usuarioDAO) {
        this.view       = view;
        this.usuarioDAO = usuarioDAO;
    }

    // ── Menú principal ──
    public void run() {
        String[] menuOptions = {
            "Listar todos", "Buscar por ID", "Crear usuario",
            "Actualizar usuario", "Eliminar usuario", "Salir"
        };

        boolean running = true;
        while (running) {
            view.showMenu(menuOptions, "Gestión de Usuarios");
            int choice = view.getMenuChoice();

            switch (choice) {
                case 1 -> listarTodos();
                case 2 -> buscarPorId();
                case 3 -> crearUsuario();
                case 4 -> actualizarUsuario();
                case 5 -> eliminarUsuario();
                case 6 -> running = false;
                default -> view.showError("Opción no válida");
            }
        }
        view.showMessage("¡Hasta luego!");
    }

    // ── Operaciones CRUD ──

    public void listarTodos() {
        List<Usuario> usuarios = usuarioDAO.findAll();
        if (usuarios.isEmpty()) {
            view.showMessage("No hay usuarios registrados.");
        } else {
            view.showUsuarios(usuarios);
        }
    }

    public void buscarPorId() {
        String input = view.askInput("ID del usuario");
        try {
            int id = Integer.parseInt(input);
            Optional<Usuario> usuario = usuarioDAO.findById(id);
            if (usuario.isPresent()) {
                view.showUsuario(usuario.get());
            } else {
                view.showError("No se encontró usuario con ID " + id);
            }
        } catch (NumberFormatException e) {
            view.showError("ID inválido: " + input);
        }
    }

    public void crearUsuario() {
        String nombre = view.askInput("Nombre del usuario");
        String email  = view.askInput("Email del usuario");

        if (nombre.isBlank() || email.isBlank()) {
            view.showError("Nombre y email son requeridos.");
            return;
        }
        if (usuarioDAO.existsByEmail(email)) {
            view.showError("Ya existe un usuario con ese email.");
            return;
        }

        Usuario nuevo = new Usuario(0, nombre, email);
        usuarioDAO.save(nuevo);
        view.showMessage("Usuario creado con ID: " + nuevo.getId());
    }

    public void actualizarUsuario() {
        String input = view.askInput("ID del usuario a actualizar");
        try {
            int id = Integer.parseInt(input);
            Optional<Usuario> opt = usuarioDAO.findById(id);
            if (opt.isEmpty()) {
                view.showError("No se encontró usuario con ID " + id);
                return;
            }
            Usuario u = opt.get();
            String nombre = view.askInput("Nuevo nombre [" + u.getNombre() + "]");
            String email  = view.askInput("Nuevo email ["  + u.getEmail()  + "]");

            if (!nombre.isBlank()) u.setNombre(nombre);
            if (!email.isBlank())  u.setEmail(email);

            boolean ok = usuarioDAO.update(u);
            view.showMessage(ok ? "Usuario actualizado." : "No se pudo actualizar.");
        } catch (NumberFormatException e) {
            view.showError("ID inválido.");
        }
    }

    public void eliminarUsuario() {
        String input = view.askInput("ID del usuario a eliminar");
        try {
            int id = Integer.parseInt(input);
            if (view.confirm("¿Confirmar eliminación del usuario " + id + "?")) {
                boolean ok = usuarioDAO.deleteById(id);
                view.showMessage(ok ? "Usuario eliminado." : "No se encontró el usuario.");
            }
        } catch (NumberFormatException e) {
            view.showError("ID inválido.");
        }
    }
}