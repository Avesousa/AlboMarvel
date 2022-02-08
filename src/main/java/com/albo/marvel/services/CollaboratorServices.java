package com.albo.marvel.services;

import com.albo.marvel.exception.NotFoundContentException;
import com.albo.marvel.models.Hero;
import com.albo.marvel.models.response.CollaboratorsResponse;
import com.albo.marvel.ws.models.CreatorAPI;
import com.albo.marvel.ws.models.ListAPI;

public interface CollaboratorServices {
    CollaboratorsResponse get(String username) throws NotFoundContentException;
    void update(ListAPI<CreatorAPI> creators, Hero hero);
}
