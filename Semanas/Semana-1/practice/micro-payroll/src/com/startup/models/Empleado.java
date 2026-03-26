package com.startup.models;

// Aplicamos Records (Día 2) e Inmutabilidad
public record Empleado(String nombre, int id, double salarioBase, boolean esRemoto) {
    // El record ya incluye equals, hashCode y toString automáticamente
}
