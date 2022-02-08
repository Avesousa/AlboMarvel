package com.albo.marvel.models.response;

import lombok.Getter;
import lombok.Setter;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Setter
@Getter
public class CharactersResponse {
    
    private Date last_sync;
    private List<CharacterResponse> characters;

    public CharactersResponse() {
        this.characters = new ArrayList<CharacterResponse>();
    }
    
}
