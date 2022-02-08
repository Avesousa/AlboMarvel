package com.albo.marvel.ws.models;

import lombok.Getter;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Setter
@Getter
public class CreatorAPI {
    private String name;
    private String role;
    private String resourceURI;
}
