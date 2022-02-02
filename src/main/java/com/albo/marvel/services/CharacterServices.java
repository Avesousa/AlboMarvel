package com.albo.marvel.services;

import java.util.List;
import com.albo.marvel.models.Character;
import com.albo.marvel.models.response.CharactersResponse;
import java.security.NoSuchAlgorithmException;
import org.springframework.web.client.HttpClientErrorException;

public interface CharacterServices {
    
    CharactersResponse find(String username) throws NoSuchAlgorithmException, HttpClientErrorException;
    void updateData();
}
