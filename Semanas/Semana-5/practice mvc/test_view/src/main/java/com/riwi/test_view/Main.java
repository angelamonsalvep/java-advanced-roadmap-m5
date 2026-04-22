package com.riwi.test_view;

import com.riwi.test_view.controller.TaskBusinessController;
import com.riwi.test_view.view.ConsoleTaskView;
import com.riwi.test_view.view.GuiTaskView;
import com.riwi.test_view.view.TaskView;

public class Main {
    public static void main(String[] args) {
        // --- ESCENARIO 1: Ejecutando con Vista de Consola ---
        System.out.println("Iniciando aplicación en modo CONSOLA...");
        TaskView consoleView = new ConsoleTaskView();
        TaskBusinessController consoleController = new TaskBusinessController(consoleView);

        // La vista (o el main en este simulacro) captura el input del usuario y llama al controlador
        consoleController.handleCreateTask("001", "Configurar Docker Compose");
        consoleController.handleCompleteTask("001");


        // --- ESCENARIO 2: Ejecutando con Vista Gráfica ---
        System.out.println("Cambiando a modo GRÁFICO (Revisa las ventanas emergentes)...");
        TaskView guiView = new GuiTaskView();
        // Usamos exactamente el mismo tipo de controlador, pero le inyectamos otra vista
        TaskBusinessController guiController = new TaskBusinessController(guiView);

        // Simulamos clics de botones en la GUI que llaman al controlador
        guiController.handleCreateTask("002", "Desplegar API en AWS");
        //guiController.handleErrorMessageTest("003"); // Probando el manejo de errores
    }
}
