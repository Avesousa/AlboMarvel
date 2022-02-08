package com.albo.marvel.repositories;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import com.albo.marvel.models.Hero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class HeroRepository implements AlboRepository<Hero> {

    @Autowired
    private EntityManager em;

    @Override
    public Hero save(Hero entity) {
        em.persist(entity);
        return entity;
    }

    @Override
    public Hero findById(Object id) {
        return em.find(Hero.class, id);
    }

    public Hero findByUsername(String username) {
        Query query = em.createNativeQuery("SELECT * FROM HERO h WHERE h.username = :username", Hero.class);
        query.setParameter("username", username);
        return (Hero) query.getSingleResult();
    }

    @Override
    public List<Hero> findAll() {
        return em.createNativeQuery("SELECT * FROM HERO", Hero.class).getResultList();
    }

    public Hero update(Hero entity) {
        entity.setLastSync(new Date());
        em.persist(entity);
        return entity;
    }

    @Override
    public void delete(Object id) {
        Hero entity = findById(id);
        if (em.contains(entity)) {
            em.remove(entity);
        }
    }

}
