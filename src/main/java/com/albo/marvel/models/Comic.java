/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.albo.marvel.models;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;

/**
 *
 * @author Avelino
 */
@Entity
@Table(name = "comic")
class Comic {
    
    @Id
    public Integer id;
    
    @Column
    public String description;
    
}
