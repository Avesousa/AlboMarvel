package com.albo.marvel.repositories;

import java.util.List;
import javax.persistence.EntityManager;
import com.albo.marvel.models.Character;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CharacterRepository implements AlboRepository<Character> {

    @Autowired
    private EntityManager em;

    @Override
    public Character save(Character entity) {
        em.persist(entity);
        return entity;
    }

    @Override
    public Character findById(Object id) {
        return em.find(Character.class, id);
    }

    @Override
    public List<Character> findAll() {
        return em.createNativeQuery("SELECT * FROM CHARACTER", Character.class).getResultList();
    }

    @Override
    public void delete(Object id) {
        Character entity = findById(id);
        if (em.contains(entity)) {
            em.remove(entity);
        }
    }
}
