package com.albo.marvel.services;

import com.albo.marvel.exception.NotFoundContentException;
import com.albo.marvel.models.Hero;
import com.albo.marvel.models.response.CharactersResponse;
import com.albo.marvel.ws.models.ComicAPI;
import java.util.List;

public interface CharacterServices {
    
    CharactersResponse get(String username) throws NotFoundContentException;
    void update(List<ComicAPI> comics, Hero hero);
}
