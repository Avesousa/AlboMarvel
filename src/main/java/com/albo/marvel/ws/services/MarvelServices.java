package com.albo.marvel.ws.services;

import com.albo.marvel.ws.models.ComicAPI;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import org.springframework.web.client.HttpClientErrorException;

public interface MarvelServices {

    // Return comics by characterId
    List<ComicAPI> getComics(Integer characterId) throws NoSuchAlgorithmException, HttpClientErrorException, IOException;
    
}
