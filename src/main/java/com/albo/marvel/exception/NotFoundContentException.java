package com.albo.marvel.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotFoundContentException extends Exception{
    
    private String message;
    
    public NotFoundContentException(String message) {
        this.message = message;
    }
    
}
