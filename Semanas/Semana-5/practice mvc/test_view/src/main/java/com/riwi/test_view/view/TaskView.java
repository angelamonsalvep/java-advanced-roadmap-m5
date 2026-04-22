package com.riwi.test_view.view;

import com.riwi.test_view.model.TaskDTO;

public interface TaskView {
    // Métodos genéricos que cualquier interfaz de usuario debería poder hacer
    void showTask(TaskDTO task);
    void showSuccessMessage(String message);
    void showErrorMessage(String error);
}
