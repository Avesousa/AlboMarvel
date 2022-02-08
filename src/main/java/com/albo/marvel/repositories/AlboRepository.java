/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.albo.marvel.daos;

import java.util.List;

public interface AlboRepository<T> {
    T save(T entity);
    List<T> save(List<T> entities);
    T findById(Integer id);
    T findByHeroe(String username);
    List<T> findAll();
    void delete(Integer id);
}
