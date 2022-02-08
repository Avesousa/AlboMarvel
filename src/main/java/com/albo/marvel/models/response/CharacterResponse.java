package com.albo.marvel.models.response;

import lombok.Getter;
import lombok.Setter;
import java.util.Set;
import java.util.HashSet;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Setter
@Getter
public class CharacterResponse {
    
    private String character;
    private Set<String> comics;

    public CharacterResponse() {
        this.comics = new HashSet<String>();
    }
    
}
