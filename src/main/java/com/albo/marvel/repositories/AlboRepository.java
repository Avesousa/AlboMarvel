/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.albo.marvel.repositories;

import java.util.List;

public interface AlboRepository<T> {
    T save(T entity);
    T findById(Object id);
    List<T> findAll();
    void delete(Object id);
}
