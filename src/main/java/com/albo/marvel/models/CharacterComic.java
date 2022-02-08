/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
@Table(name = "characterComic")
@Getter
@Setter
public class CharacterComic implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @ManyToOne
    @JoinColumn(name = "hero_id", nullable = false)
    private Hero hero;

    @Id
    @ManyToOne
    @JoinColumn(name = "character_id", nullable = false)
    private Character character;
    
    @Id
    @ManyToOne
    @JoinColumn(name = "comic_id")
    private Comic comic;

    public CharacterComic() {
    }
    
    public CharacterComic(Hero hero, Character character, Comic comic) {
        this.hero = hero;
        this.character = character;
        this.comic = comic;
    }

}
