package com.albo.marvel.repositories;

import java.util.List;
import javax.persistence.EntityManager;
import com.albo.marvel.models.Comic;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;

@Repository
public class ComicRepository implements AlboRepository<Comic> {

    @Autowired
    private EntityManager em;
    
    @Override
    public Comic save(Comic entity) {
        em.persist(entity);
        return entity;
    }

    @Override
    public Comic findById(Object id) {
        return em.find(Comic.class, id);
    }

    @Override
    public List<Comic> findAll() {
        return em.createNativeQuery("SELECT * FROM COMIC", Comic.class).getResultList();
    }

    @Override
    public void delete(Object id) {
        Comic entity = findById(id);
        if(em.contains(entity)) {
            em.remove(entity);
        }
    }
    
}
