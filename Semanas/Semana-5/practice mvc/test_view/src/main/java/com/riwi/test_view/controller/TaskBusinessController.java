package com.riwi.test_view.controller;

import com.riwi.test_view.model.Task;
import com.riwi.test_view.model.TaskDTO;
import com.riwi.test_view.view.TaskView;

import java.util.HashMap;
import java.util.Map;

public class TaskBusinessController {
    // El controlador depende de la abstracción (Interfaz), no de una vista concreta
    private final TaskView view;

    // Simulamos una base de datos en memoria
    private final Map<String, Task> taskDatabase = new HashMap<>();

    // Inyección de dependencias: se le pasa la vista al construirlo
    public TaskBusinessController(TaskView view) {
        this.view = view;
    }

    // Método que las vistas llamarán pasándole datos puros (Strings)
    public void handleCreateTask(String id, String description) {
        if (description == null || description.isBlank()) {
            view.showErrorMessage("La descripción de la tarea no puede estar vacía.");
            return;
        }

        Task newTask = new Task(id, description);
        taskDatabase.put(id, newTask);

        view.showSuccessMessage("¡Tarea creada con éxito!");
        view.showTask(new TaskDTO(newTask.getId(), newTask.getDescription(), newTask.isCompleted()));
    }

    // Otro método de negocio accionado por la vista
    public void handleCompleteTask(String id) {
        Task task = taskDatabase.get(id);

        if (task != null) {
            task.setCompleted(true);
            view.showSuccessMessage("¡Tarea marcada como completada!");
            view.showTask(new TaskDTO(task.getId(), task.getDescription(), task.isCompleted()));
        } else {
            view.showErrorMessage("No se encontró la tarea con ID: " + id);
        }
    }
}
