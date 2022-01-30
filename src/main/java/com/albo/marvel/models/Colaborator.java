/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.albo.marvel.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Avelino
 */
@Entity
@Table(name = "Colaborator")
@Setter
@Getter
public class Colaborator {
    
    @Id
    public Integer id;
    
    @Column
    public String name;
    
    @Column
    public String type;
    
    
}
