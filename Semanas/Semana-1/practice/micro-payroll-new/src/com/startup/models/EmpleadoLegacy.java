package com.startup.models;

import java.util.Objects;
//vjava8
public class EmpleadoLegacy {
    private final String nombre;
    private final int id;
    private final double salarioBase;
    private final boolean esRemoto;

    // Constructor Manual
    public EmpleadoLegacy(String nombre, int id, double salarioBase, boolean esRemoto) {
        this.nombre = nombre;
        this.id = id;
        this.salarioBase = salarioBase;
        this.esRemoto = esRemoto;
    }

    // Getters Manuales (No hay .nombre(), es .getNombre())
    public String getNombre() { return nombre; }
    public int getId() { return id; }
    public double getSalarioBase() { return salarioBase; }
    public boolean isEsRemoto() { return esRemoto; }

    // toString Manual (¡Obligatorio o verás algo como Empleado@1a2b3c!)
    @Override
    public String toString() {
        return "EmpleadoLegacy{nombre='" + nombre + "', id=" + id + "}";
    }

    // equals y hashCode Manuales (Si olvidas esto, las colecciones fallan)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmpleadoLegacy that = (EmpleadoLegacy) o;
        return id == that.id && Objects.equals(nombre, that.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, id);
    }
}
