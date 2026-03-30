package com.startup.models;

public record Empleado(String nombre, int id, double salarioBase, boolean esRemoto) {

    private Empleado modificarSalario(double salarioBase){
        return new Empleado(nombre, id, salarioBase, esRemoto);
    }


}
