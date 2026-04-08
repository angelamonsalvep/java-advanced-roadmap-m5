package com.nomina.main;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Implementación de Try-with-resources para el Scanner
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("--- Diagnóstico de Elegibilidad de Bonos ---");
            
            try {
                System.out.print("Ingrese el ID del empleado: ");
                int idEmpleado = sc.nextInt();
                
                System.out.print("Ingrese el promedio de desempeño: ");
                double promedioDesempeño = sc.nextDouble();

                String resultado = (promedioDesempeño >= 8.5) ? "ELEGIBLE PARA BONO" : "CONTINUAR EVALUACIÓN";

                System.out.println("\n--- Resumen de Resultados ---");
                System.out.println("ID Empleado: " + idEmpleado);
                System.out.println("Estado: " + resultado);

                // --- SECCIÓN PARA JEP 358 (Helpful NullPointerExceptions) ---
                System.out.println("\n--- Simulación de Auditoría (Demostración JEP 358) ---");
                Empleado emp = new Empleado(); // El contrato es null por defecto
                
                // Intentamos acceder a una propiedad en una cadena de llamadas
                // Esto lanzará un NPE detallado en Java 17
                System.out.println("Tipo de Contrato: " + emp.getContrato().getTipo());

            } catch (InputMismatchException e) {
                System.err.println("\n[ERROR]: Entrada no válida.");
            } catch (NullPointerException e) {
                System.err.println("\n[DIAGNÓSTICO JEP 358]:");
                System.err.println("Mensaje detallado: " + e.getMessage());
            }

        }
    }
}

// Clases de apoyo para demostrar JEP 358
class Empleado {
    private Contrato contrato; // Es null inicialmente
    public Contrato getContrato() { return contrato; }
}

class Contrato {
    private String tipo = "Indefinido";
    public String getTipo() { return tipo; }
}
