package com.albo.marvel.models;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "collaborator")
@Setter
@Getter
public class Collaborator implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    
    @Column
    private String name;
    
    @Column
    private String type;

    public Collaborator() {
    }
    
    public Collaborator(String name, String type) {
        this.name = name;
        this.type = type;
    }
    
}
