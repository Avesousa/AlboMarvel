/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.albo.marvel.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "character")
@Setter
@Getter
public class Character implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Integer id;

    @Column
    private String username;
    
    @Column
    private String name;
    
    @OneToMany(mappedBy = "character")
    private List<Relation> relations;
    
    @Column(name = "last_sync",
            insertable = false,
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastSync;

    public Character(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

}
