/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.albo.marvel.services;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import org.springframework.web.client.HttpClientErrorException;

public interface ScheduledServices {
    
    void syncData();
    void updateData() throws NoSuchAlgorithmException, HttpClientErrorException, IOException;
    
}
