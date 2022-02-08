package com.albo.marvel.repositories;

import org.springframework.stereotype.Repository;
import com.albo.marvel.models.Collaborator;
import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;

@Repository
public class CollaboratorRepository implements AlboRepository<Collaborator> {

    @Autowired
    private EntityManager em;
    
    @Override
    public Collaborator save(Collaborator entity) {
        em.persist(entity);
        return entity;
    }

    @Override
    public Collaborator findById(Object id) {
        return em.find(Collaborator.class, id);
    }

    @Override
    public List<Collaborator> findAll() {
        return em.createNativeQuery("SELECT * FROM COLLABORATOR", Collaborator.class).getResultList();
    }

    @Override
    public void delete(Object id) {
        Collaborator entity = findById(id);
        if(em.contains(entity)) {
            em.remove(entity);
        }
    }
    
}
