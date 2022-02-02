package com.albo.marvel.daos;

import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.albo.marvel.models.Relation;
import java.util.List;
import javax.persistence.TypedQuery;
import com.albo.marvel.models.Character;
import com.albo.marvel.models.Comic;

@Repository
public class RelationRepository {
    
    @Autowired
    private EntityManager em;
    
    public List<Relation> findByHeroe(Integer id){
        TypedQuery<Relation> query = em.createQuery("SELECT * FROM relation WHERE character_id = :id ", Relation.class);
        query.setParameter("id", id);
        return query.getResultList();
    }
    
    public void DeleteByHeroe(Integer id){
        TypedQuery<Relation> query = em.createQuery("DELETE FROM relation WHERE character_id = :id ", Relation.class);
        query.setParameter("id", id);
        query.executeUpdate();
    }
    
    public Relation save(Character heroe, Character character, Comic comic){
        Relation relation = new Relation(character, character, comic);
        if(!em.contains(relation)){
            em.persist(relation);
        }
        return relation;
    }
    
}
