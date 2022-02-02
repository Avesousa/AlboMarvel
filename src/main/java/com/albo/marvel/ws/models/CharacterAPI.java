/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.albo.marvel.ws.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Avelino
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Setter
@Getter
public class CharacterAPI {
    
    private Integer id;
    private String name;
    private String description;
    private Date modified;
    private ListAPI<ComicSummaryAPI> comics;
    
}
