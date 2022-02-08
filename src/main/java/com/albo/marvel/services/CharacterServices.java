package com.albo.marvel.services;

import java.util.List;
import com.albo.marvel.models.Hero;
import com.albo.marvel.models.response.CharactersResponse;
import com.albo.marvel.ws.models.ComicAPI;
import com.albo.marvel.exception.NotFoundContentException;

public interface CharacterServices {
    
    CharactersResponse get(String username) throws NotFoundContentException;
    void update(List<ComicAPI> comics, Hero hero);
}
