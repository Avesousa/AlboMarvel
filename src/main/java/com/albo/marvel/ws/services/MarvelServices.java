/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.albo.marvel.ws.services;

import com.albo.marvel.ws.models.CharacterAPI;
import com.albo.marvel.ws.models.ComicAPI;
import com.albo.marvel.ws.models.CreatorAPI;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import org.springframework.web.client.HttpClientErrorException;

/**
 *
 * @author Avelino
 */
public interface MarvelServices {
    
// Return all characters
    List<CharacterAPI> getCharacters(Integer characterId) throws NoSuchAlgorithmException, HttpClientErrorException;
    
// Return all collaborators
    List<CreatorAPI> getCollaborator(Integer characterId) throws NoSuchAlgorithmException, HttpClientErrorException;
    
}
