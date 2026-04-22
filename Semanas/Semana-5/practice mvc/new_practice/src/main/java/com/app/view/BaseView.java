package com.app.view;

import com.app.model.entity.Usuario;
import java.util.List;

/**
 * Comportamiento común a todas las vistas.
 * Evita duplicar lógica de formato entre ConsoleView y SwingView.
 */
public abstract class BaseView implements View {

    // ── Formato compartido para la lista de usuarios ──
    protected String formatUsuarios(List<Usuario> usuarios) {
        if (usuarios.isEmpty()) return "(Sin usuarios)";

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-5s %-20s %-30s%n", "ID", "NOMBRE", "EMAIL"));
        sb.append("─".repeat(55)).append("\n");
        for (Usuario u : usuarios) {
            sb.append(String.format("%-5d %-20s %-30s%n",
                    u.getId(), u.getNombre(), u.getEmail()));
        }
        return sb.toString();
    }

    protected String formatUsuario(Usuario u) {
        return String.format("ID: %d%nNombre: %s%nEmail: %s",
                u.getId(), u.getNombre(), u.getEmail());
    }

    protected String buildMenu(String[] options, String title) {
        StringBuilder sb = new StringBuilder();
        sb.append("╔═══ ").append(title).append(" ═══╗\n");
        for (int i = 0; i < options.length; i++) {
            sb.append(String.format("  %d. %s%n", i + 1, options[i]));
        }
        sb.append("╚" + "═".repeat(title.length() + 8) + "╝");
        return sb.toString();
    }
}