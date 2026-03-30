package com.startup.models;

public record Empresa(String nombre, int sedes) {

    private Empresa modificarNombre(String nombre)
    {
        Empresa empresaModificada = new Empresa(nombre, this.sedes);
        return empresaModificada;
    }
    
}
