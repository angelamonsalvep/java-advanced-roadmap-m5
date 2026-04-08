import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Ejercicio Práctico - Semana 3, Día 1: El Fin de los Arreglos Fijos
 * Tópicos: ArrayList, Genéricos y Try-with-resources.
 * 
 * Objetivo: Gestionar una lista dinámica de empleados y persistirla en un archivo.
 */
public class Ejercicio_Dia1_Semana3 {

    // Clase interna para representar al Empleado
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
        // 1. Uso de ArrayList con Genéricos (Día 1)
        List<Empleado> nomina = new ArrayList<>();

        System.out.println("--- Iniciando Gestión de Nómina Dinámica ---");
        System.out.println("Tamaño inicial: " + nomina.size());

        // 2. Operaciones Dinámicas: Agregar
        nomina.add(new Empleado(101, "Ana García", 2500.50));
        nomina.add(new Empleado(102, "Carlos López", 3100.00));
        nomina.add(new Empleado(103, "Elena Rivas", 2850.75));

        System.out.println("Tamaño después de agregar: " + nomina.size());

        // 3. Listar empleados
        System.out.println("\nLista de Empleados:");
        for (Empleado e : nomina) {
            System.out.println("- " + e);
        }

        // 4. Uso de Try-with-resources para persistencia (Requisito adicional)
        String nombreArchivo = "nomina_empleados.txt";
        
        System.out.println("\nGuardando datos en " + nombreArchivo + "...");
        
        try (PrintWriter writer = new PrintWriter(new FileWriter(nombreArchivo))) {
            for (Empleado e : nomina) {
                writer.println(e.toCSV());
            }
            System.out.println("¡Datos guardados exitosamente!");
        } catch (IOException e) {
            System.err.println("Error al guardar el archivo: " + e.getMessage());
        }

        // 5. Eliminar por índice y ver cambio de tamaño
        System.out.println("\nEliminando al segundo empleado (índice 1)...");
        nomina.remove(1); 
        System.out.println("Tamaño final: " + nomina.size());
        
        System.out.println("\nLista final actualizada:");
        nomina.forEach(System.out::println);
    }
}
