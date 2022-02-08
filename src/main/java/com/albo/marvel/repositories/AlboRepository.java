package com.albo.marvel.repositories;

import java.util.List;

public interface AlboRepository<T> {
    T save(T entity);
    T findById(Object id);
    List<T> findAll();
    void delete(Object id);
}
