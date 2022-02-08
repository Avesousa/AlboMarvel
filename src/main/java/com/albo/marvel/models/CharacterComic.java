/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.albo.marvel.models;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "relation")
@Getter
@Setter
public class Relation {

    @Id
    @ManyToOne
    @JoinColumn(name = "character_id", nullable = false)
    private Character heroe;

    @Id
    @ManyToOne
    private Character character;
    
    @Id
    @ManyToOne
    @JoinColumn(name = "comic_id")
    private Comic comic;

    public Relation(Character heroe, Character character, Comic comic) {
        this.heroe = heroe;
        this.character = character;
        this.comic = comic;
    }

}
