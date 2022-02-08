package com.albo.marvel.ws.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Setter
@Getter
public class CharacterAPI {
    
    private Integer id;
    private String name;
    
}
