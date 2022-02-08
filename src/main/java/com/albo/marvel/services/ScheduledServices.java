package com.albo.marvel.services;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import org.springframework.web.client.HttpClientErrorException;

public interface ScheduledServices {
    
    void syncData();
    void updateData() throws NoSuchAlgorithmException, HttpClientErrorException, IOException;
    
}
