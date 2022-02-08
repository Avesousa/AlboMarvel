package com.albo.marvel.ws.models;

import java.util.List;
import java.util.ArrayList;
import lombok.Getter;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Setter
@Getter
public class ListAPI<T> {
    private Integer characterId;
    private List<T> items;

    public ListAPI() {
        this.items = new ArrayList<T>();
    }
}
