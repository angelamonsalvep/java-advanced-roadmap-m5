package com.app.view;

import com.app.model.entity.Usuario;
import java.util.List;
import java.util.Scanner;

/**
 * Vista de consola (System.in / System.out).
 */
public class ConsoleView extends BaseView {

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void showMessage(String msg) {
        System.out.println("[INFO] " + msg);
    }

    @Override
    public void showError(String msg) {
        System.err.println("[ERROR] " + msg);
    }

    @Override
    public void showUsuarios(List<Usuario> usuarios) {
        System.out.println("\n" + formatUsuarios(usuarios));
    }

    @Override
    public void showUsuario(Usuario usuario) {
        System.out.println("\n" + formatUsuario(usuario));
    }

    @Override
    public String askInput(String prompt) {
        System.out.print(prompt + ": ");
        return scanner.nextLine().trim();
    }

    @Override
    public boolean confirm(String question) {
        System.out.print(question + " (s/n): ");
        String r = scanner.nextLine().trim().toLowerCase();
        return r.equals("s") || r.equals("si") || r.equals("sí");
    }

    @Override
    public void showMenu(String[] options, String title) {
        System.out.println("\n" + buildMenu(options, title));
    }

    @Override
    public int getMenuChoice() {
        System.out.print("Opción: ");
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}