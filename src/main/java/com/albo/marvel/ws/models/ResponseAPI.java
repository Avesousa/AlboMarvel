package com.albo.marvel.ws.models;

import lombok.Getter;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Setter
@Getter
public class ResponseAPI<T> {
    private Integer code;
    private String status;
    private DataAPI<T> data;
}
