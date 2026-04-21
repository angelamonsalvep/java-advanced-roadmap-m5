package com.riwi;

import java.util.List;

public interface CursoDAO {
    public List<Curso> findAll();
    public Curso save(Curso curso);
    public Curso update(Curso curso);
    public Boolean delete(Integer id);
}
