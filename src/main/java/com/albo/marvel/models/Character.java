/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.albo.marvel.models;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Avelino
 */
@Entity
@Table(name = "character")
public class Character {
    
    @Id
    public Integer id;
    
    @Column
    public String name;
    
    public Set<Colaborator> colaborators;
    
    public Set<Comic> comics;
    
}
