package com.riwi;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // 1. Definición de Datos con constantes para facilitar el mantenimiento
        final String[] sedes = {"Norte", "Sur", "Centro"};
        final double[][] matrizPesos = {
                {15.55, 20.30, 10.85}, // Norte
                {40.90, 35.15, 50.25}, // Sur
                {12.40, 18.60, 14.75}  // Centro
        };

        procesarYReportar(sedes, matrizPesos);
    }

    /**
     * Procesa la matriz de pesos y genera el reporte formateado.
     * Utiliza casting explícito para la conversión final a enteros.
     */
    private static void procesarYReportar(String[] sedes, double[][] pesos) {
        for (int i = 0; i < sedes.length; i++) {
            double pesoTotalSede = 0;
            int totalContenedores = pesos[i].length;

            // Recorrido de la matriz (Bucle anidado)
            for (double pesoContenedor : pesos[i]) {
                pesoTotalSede += pesoContenedor;
            }

            double promedioPrecision = pesoTotalSede / totalContenedores;

            // Transformación de Datos: Casting Explícito (double -> int)
            // Se trunca la parte decimal para cumplir con el requerimiento de despacho.
            int pesoDespacho = (int) promedioPrecision;

            imprimirReporte(sedes[i], pesoTotalSede, promedioPrecision, pesoDespacho);
        }
    }

    private static void imprimirReporte(String sede, double total, double promedio, int despacho) {
        System.out.printf("""
            Sede Actual: %s
            -------------------------------------------
            > Peso Total Acumulado: %.2f t
            > Promedio de Precisión: %.5f t
            > PESO PARA DESPACHO (Cast): %d t
            ===========================================
            """, sede, total, promedio, despacho);
    }
}