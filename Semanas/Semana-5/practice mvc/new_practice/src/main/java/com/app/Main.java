package com.app;

import com.app.config.AppConfig;
import com.app.controller.UsuarioController;
import com.app.dao.UsuarioDAO;
import com.app.dao.impl.UsuarioDAOImpl;
import com.app.view.ConsoleView;
import com.app.view.SwingView;
import com.app.view.View;

public class Main {

    public static void main(String[] args) {

        AppConfig config = AppConfig.getInstance();

        // ── Factory: elige la vista según app.properties ──
        View view = createView(config.getViewType());

        // ── Inyección de dependencias ──
        UsuarioDAO          usuarioDAO  = new UsuarioDAOImpl();
        UsuarioController   controller  = new UsuarioController(view, usuarioDAO);

        view.showMessage("Bienvenido a " + config.getAppName());

        // ── Arrancar la aplicación ──
        controller.run();
    }

    private static View createView(String type) {
        return switch (type.toLowerCase()) {
            case "swing" -> new SwingView();
            default       -> new ConsoleView();
        };
    }
}