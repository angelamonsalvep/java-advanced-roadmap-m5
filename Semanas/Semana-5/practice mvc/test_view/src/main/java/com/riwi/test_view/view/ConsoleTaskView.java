package com.riwi.test_view.view;

import com.riwi.test_view.model.TaskDTO;

public class ConsoleTaskView implements TaskView{
    @Override
    public void showTask(TaskDTO task) {
        System.out.println("--- CONSOLA: Detalles de Tarea ---");
        System.out.println("ID: " + task.id());
        System.out.println("Desc: " + task.description());
        System.out.println("Estado: " + (task.completed() ? "[X] Lista" : "[ ] Pendiente"));
        System.out.println("----------------------------------\n");
    }

    @Override
    public void showSuccessMessage(String message) {
        System.out.println("✅ ÉXITO: " + message);

    }

    @Override
    public void showErrorMessage(String error) {
        System.err.println("❌ ERROR: " + error);
    }
}
