package com.startup.utils;

public class CalculadoraNomina {
    
    public static double calcularNeto(double salario, double bono) {
        // Reto Día 3: Jerarquía de operadores
        // (Salario + bono con 10% extra) - (5% de retención sobre el salario)
        return (salario + bono * 1.10) - (salario * 0.05);
    }

    public static void demostrarOverflow() {
        // Reto Día 2: Análisis de desbordamiento (Stack Memory)
        byte nivelSeguridad = 127;
        nivelSeguridad = (byte) (nivelSeguridad + 1);
        System.out.println("⚠️ Alerta de Sistema - Nivel de Seguridad (Overflow): " + nivelSeguridad);
        // Explicación: 127 + 1 en un byte de 8 bits resulta en -128 por el complemento a dos.
    }
}
