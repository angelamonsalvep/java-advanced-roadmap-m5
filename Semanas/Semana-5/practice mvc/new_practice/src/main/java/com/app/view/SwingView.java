package com.app.view;

import com.app.model.entity.Usuario;
import javax.swing.JOptionPane;
import java.util.List;

/**
 * Vista basada en JOptionPane (Swing).
 * Reutiliza la lógica de formato de BaseView.
 */
public class SwingView extends BaseView {

    @Override
    public void showMessage(String msg) {
        JOptionPane.showMessageDialog(null, msg, "Información",
                JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void showError(String msg) {
        JOptionPane.showMessageDialog(null, msg, "Error",
                JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void showUsuarios(List<Usuario> usuarios) {
        JOptionPane.showMessageDialog(null,
                formatUsuarios(usuarios), "Lista de Usuarios",
                JOptionPane.PLAIN_MESSAGE);
    }

    @Override
    public void showUsuario(Usuario usuario) {
        JOptionPane.showMessageDialog(null,
                formatUsuario(usuario), "Detalle de Usuario",
                JOptionPane.PLAIN_MESSAGE);
    }

    @Override
    public String askInput(String prompt) {
        return JOptionPane.showInputDialog(null, prompt);
    }

    @Override
    public boolean confirm(String question) {
        int r = JOptionPane.showConfirmDialog(null, question,
                "Confirmar", JOptionPane.YES_NO_OPTION);
        return r == JOptionPane.YES_OPTION;
    }

    @Override
    public void showMenu(String[] options, String title) {
        // El menú en Swing se muestra en getMenuChoice()
        // Se almacena internamente para que getMenuChoice lo use
    }

    @Override
    public int getMenuChoice() {
        // Construye el menú como lista de selección
        String[] opts = {"Listar usuarios", "Buscar por ID",
                "Crear usuario", "Actualizar usuario",
                "Eliminar usuario", "Salir"};
        Object sel = JOptionPane.showInputDialog(
                null, "Selecciona una opción:", "Menú",
                JOptionPane.PLAIN_MESSAGE, null, opts, opts[0]);
        if (sel == null) return 6; // Salir
        for (int i = 0; i < opts.length; i++) {
            if (opts[i].equals(sel)) return i + 1;
        }
        return -1;
    }
}