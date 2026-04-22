package com.app.dao;

import java.util.List;
import java.util.Optional;

/**
 * Interfaz genérica DAO.
 * T  = tipo de entidad  (ej. Usuario)
 * ID = tipo de la clave (ej. Integer)
 */
public interface GenericDAO<T, ID> {
    T              save(T entity);
    Optional<T>    findById(ID id);
    List<T>        findAll();
    boolean        update(T entity);
    boolean        deleteById(ID id);
}