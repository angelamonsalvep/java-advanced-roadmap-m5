package com.startup.utils;

public class CalculadoraNomina {

    public static double calcularNeto(double salario, double bono){
        // (Salario + bono con 10% extra) - (5% de retención sobre el salario)
        return (salario + bono * 1.10) - (salario * 0.05);
    }

}
