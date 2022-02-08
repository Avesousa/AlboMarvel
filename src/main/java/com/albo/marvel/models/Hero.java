package com.albo.marvel.models;

import lombok.Getter;
import lombok.Setter;
import java.util.Date;
import java.util.List;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "hero")
@Setter
@Getter
public class Hero {
    
    // 1010913 Captian America
    // 1010935 Iron Man
    
    @Id
    private Integer id;

    @Column
    private String username;
    
    @Column
    private String name;
    
    @Column(name = "last_sync", insertable = false,
        columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastSync;
    
    @OneToMany(mappedBy = "hero")
    private List<CharacterComic> charactersAndComics;
    
    @OneToMany(mappedBy = "hero")
    private List<HeroCollaborator> collaborators;

    public Hero() {
    }

    public Hero(Integer id) {
        this.id = id;
    }
    
}
