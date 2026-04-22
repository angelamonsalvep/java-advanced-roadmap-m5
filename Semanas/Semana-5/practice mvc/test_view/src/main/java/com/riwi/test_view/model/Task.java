package com.riwi.test_view.model;

// 1. La Entidad de Negocio (Modelo real)
public class Task {
    private String id;
    private String description;
    private boolean completed;

    public Task(String id, String description) {
        this.id = id;
        this.description = description;
        this.completed = false;
    }

    // Getters y Setters
    public String getId() { return id; }
    public String getDescription() { return description; }
    public boolean isCompleted() { return completed; }
    public void setCompleted(boolean completed) { this.completed = completed; }
}

