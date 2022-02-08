package com.albo.marvel.repositories;

import com.albo.marvel.models.HeroCollaborator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class HeroCollaboratorRepository implements AlboRepository<HeroCollaborator> {

    @Autowired
    private EntityManager em;
    
    @Override
    public HeroCollaborator save(HeroCollaborator entity) {
        em.persist(entity);
        return entity;
    }

    @Override
    public HeroCollaborator findById(Object id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public List<HeroCollaborator> findByHero(Integer heroId) {
        Query query = em.createNativeQuery("SELECT * FROM HERO_COLLABORATOR hc WHERE hc.hero_id = :hero", HeroCollaborator.class);
        query.setParameter("hero", heroId);
        return query.getResultList();
    }

    @Override
    public List<HeroCollaborator> findAll() {
        return em.createNativeQuery("SELECT * FROM HERO_COLLABORATOR", HeroCollaborator.class).getResultList();
    }

    @Override
    public void delete(Object id) {
        List<HeroCollaborator> heroesCollaborators = findByHero((Integer)id);
        for(HeroCollaborator heroCollaborator : heroesCollaborators) {
            if(em.contains(heroCollaborator)) em.remove(heroCollaborator);
        }
    }
}
