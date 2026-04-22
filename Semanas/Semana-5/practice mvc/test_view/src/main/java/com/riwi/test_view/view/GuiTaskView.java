package com.riwi.test_view.view;

import com.riwi.test_view.model.TaskDTO;

import javax.swing.*;

public class GuiTaskView implements TaskView{
    @Override
    public void showTask(TaskDTO task) {
        String estado = task.completed() ? "Lista" : "Pendiente";
        String mensaje = String.format("ID: %s\nDescripción: %s\nEstado: %s",
                task.id(), task.description(), estado);
        JOptionPane.showMessageDialog(null, mensaje, "Detalles de Tarea", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void showSuccessMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void showErrorMessage(String error) {
        JOptionPane.showMessageDialog(null, error, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
