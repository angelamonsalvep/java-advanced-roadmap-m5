package com.riwi;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        // 1. Definición de Datos con constantes para facilitar el mantenimiento
        String[] sedes = { "Norte", "Sur", "Centro" };
        double[][] matrizPesos = {
                {15.55, 20.30, 10.85},
                {40.90, 35.15, 50.25},
                {12.40, 18.60, 14.75}
        };
        procesarYReportar(sedes, matrizPesos);

    }

    /**
     * Procesa la matriz de pesos y genera el reporte formateado.
     * Utiliza casting explícito para la conversión final a enteros.
     */
    private static void procesarYReportar(String[] sedes, double[][] pesos) {
        for(int i =0; i<sedes.length; i++){
            double pesoAcumulado = 0;
            int cantidadCont = pesos[i].length;
            for(double peso: pesos[i]){
                pesoAcumulado+=peso;
            }
            double promedio = pesoAcumulado/cantidadCont;
            imprimirReporte(sedes[i], pesoAcumulado, promedio, (int) promedio);
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