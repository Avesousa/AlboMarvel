package com.albo.marvel.daos;

import java.util.List;
import javax.persistence.EntityManager;
import com.albo.marvel.models.Character;
import java.util.ArrayList;
import javax.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CharacterRepository {

    @Autowired
    private EntityManager em;

    public Character save(Character entity) {
        em.persist(entity);
        return entity;
    }
    
    public List<Character> findOnlyUsername(){
        TypedQuery<Character> query = em.createQuery("SELECT * FROM character c WHERE c.username IS NOT NULL", Character.class);
        return query.getResultList(); 
    }

    public Character findById(Integer id) {
        return em.find(Character.class, id);
    }

    public Character findByHeroe(String username) {
        TypedQuery<Character> query = em.createQuery("SELECT * FROM character c WHERE c.username = :username", Character.class);
        query.setParameter("username", username);
        return query.getSingleResult();
    }

    public void delete(Integer id) {
        Character entity = this.findById(id);
        if (em.contains(entity)) {
            em.remove(entity);
        }
    }

}
