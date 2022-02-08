package com.albo.marvel.repositories;

import java.util.List;
import javax.persistence.Query;
import javax.persistence.EntityManager;
import com.albo.marvel.models.CharacterComic;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;

@Repository
public class CharacterComicRepository implements AlboRepository<CharacterComic>{
    
    @Autowired
    private EntityManager em;

    @Override
    public CharacterComic save(CharacterComic entity) {
        em.persist(entity);
        return entity;
    }

    @Override
    public CharacterComic findById(Object id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public List<CharacterComic> findByHero(Integer heroId) {
        Query query = em.createNativeQuery("SELECT * FROM CHARACTER_COMIC cc WHERE cc.hero_id = :hero", CharacterComic.class);
        query.setParameter("hero", heroId);
        return query.getResultList();
    }

    @Override
    public List<CharacterComic> findAll() {
       return em.createNativeQuery("SELECT * FROM CHARACTER_COMIC", CharacterComic.class).getResultList();
    }

    @Override
    public void delete(Object id) {
        List<CharacterComic> charactersComics = findByHero((Integer)id);
        for(CharacterComic characterComic : charactersComics) {
            if(em.contains(characterComic)) em.remove(characterComic);
        }
    }

}
