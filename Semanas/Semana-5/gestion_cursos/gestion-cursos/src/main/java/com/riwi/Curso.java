package com.riwi;

public class Curso {
    private Integer id;
    private String nombre;
    private String instructor;
    private int duracionHoras;


    public Curso(Integer id, String nombre, String instructor, int duracionHoras) {
        this.id = id;
        this.nombre = nombre;
        this.instructor = instructor;
        this.duracionHoras = duracionHoras;
    }

    public Curso() {
    }

    public Curso(String nombre, String instructor, int duracionHoras) {
        this.nombre = nombre;
        this.instructor = instructor;
        this.duracionHoras = duracionHoras;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public int getDuracionHoras() {
        return duracionHoras;
    }

    public void setDuracionHoras(int duracionHoras) {
        this.duracionHoras = duracionHoras;
    }

    @Override
    public String toString() {
        return "Curso{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", instructor='" + instructor + '\'' +
                ", duracionHoras=" + duracionHoras +
                '}';
    }
}
