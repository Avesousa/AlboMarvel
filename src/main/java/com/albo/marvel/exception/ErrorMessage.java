package com.albo.marvel.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorMessage {

    public ErrorMessage(String message) {
        this.message = message;
    }
    
    private String message;
    
}
