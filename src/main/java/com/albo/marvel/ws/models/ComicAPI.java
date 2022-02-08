package com.albo.marvel.ws.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

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
