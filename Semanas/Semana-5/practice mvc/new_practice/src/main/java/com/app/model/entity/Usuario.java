package com.app.model.entity;

public class Usuario {

    private int    id;
    private String nombre;
    private String email;

    public Usuario() {}
    public Usuario(int id, String nombre, String email) {
        this.id     = id;
        this.nombre = nombre;
        this.email  = email;
    }

    // Getters / Setters
    public int    getId()    { return id; }
    public String getNombre(){ return nombre; }
    public String getEmail() { return email; }
    public void   setId(int id)           { this.id     = id; }
    public void   setNombre(String nombre){ this.nombre = nombre; }
    public void   setEmail(String email)  { this.email  = email; }

    @Override
    public String toString() {
        return String.format("Usuario{id=%d, nombre='%s', email='%s'}",
                id, nombre, email);
    }
}