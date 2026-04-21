package com.riwi;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        CursoDAO cursoDAO = new CursoDaoImpl();
        Curso cursoGuardado = cursoDAO.save(new Curso
                ("Java", "Angela", 8));
        System.out.println("Consulta ejecutada: " + cursoGuardado.toString());
    }
}