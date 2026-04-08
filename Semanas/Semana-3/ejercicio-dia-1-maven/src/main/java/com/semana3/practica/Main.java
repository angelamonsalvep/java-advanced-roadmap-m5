package com.semana3.practica;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Ejercicio Práctico - Semana 3, Día 1: El Fin de los Arreglos Fijos
 * Tópicos: ArrayList, Genéricos y Try-with-resources.
 */
public class Main {

    static class Empleado {
        private int id;
        private String nombre;
        private double salario;

        public Empleado(int id, String nombre, double salario) {
            this.id = id;
            this.nombre = nombre;
            this.salario = salario;
        }

        @Override
        public String toString() {
            return String.format("ID: %d | Nombre: %s | Salario: %.2f", id, nombre, salario);
        }

        public String toCSV() {
            return id + "," + nombre + "," + salario;
        }
    }

    public static void main(String[] args) {
        List<Empleado> nomina = new ArrayList<>();

        System.out.println("--- Gestión de Nómina Dinámica ---");
        
        nomina.add(new Empleado(101, "Ana García", 2500.50));
        nomina.add(new Empleado(102, "Carlos López", 3100.00));
        nomina.add(new Empleado(103, "Elena Rivas", 2850.75));

        System.out.println("Tamaño actual: " + nomina.size());

        try (PrintWriter writer = new PrintWriter(new FileWriter("nomina_empleados.txt"))) {
            for (Empleado e : nomina) {
                writer.println(e.toCSV());
                System.out.println(e);
            }
            System.out.println("¡Datos guardados exitosamente con try-with-resources!");
        } catch (IOException e) {
            System.err.println("Error al guardar: " + e.getMessage());
        }

        nomina.remove(1); 
        System.out.println("Tamaño final tras eliminación: " + nomina.size());
    }
}
