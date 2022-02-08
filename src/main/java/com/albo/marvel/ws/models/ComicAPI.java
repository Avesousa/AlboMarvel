package com.albo.marvel.ws.models;

import lombok.Getter;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Setter
@Getter
public class ComicAPI {
    private Integer id;
    private String title;
    private String description;
    private ListAPI<CreatorAPI> creators;
    private ListAPI<CharacterAPI> characters;
}
