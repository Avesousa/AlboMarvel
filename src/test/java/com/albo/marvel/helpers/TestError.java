package com.albo.marvel.helpers;

import org.slf4j.LoggerFactory;

public class TestError {
    
    public static void log(Class<?> className, Exception e){
        LoggerFactory.getLogger(className).error("Ends test with error", e.getMessage());

    }

}