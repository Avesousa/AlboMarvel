package com.albo.marvel.ws.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Setter
@Getter
public class CreatorAPI {
    private String name;
    private String role;
    private String resourceURI;
}
