package com.albo.marvel.models;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "heroCollaborator")
@Getter
@Setter
public class HeroCollaborator implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @ManyToOne
    @JoinColumn(name = "hero_id")
    private Hero hero;
    
    @Id
    @ManyToOne
    @JoinColumn(name = "collaborator_id")
    private Collaborator collaborator;

    public HeroCollaborator() {
    }

    public HeroCollaborator(Hero hero, Collaborator collaborator) {
        this.hero = hero;
        this.collaborator = collaborator;
    }
    
}
