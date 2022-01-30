/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.albo.marvel.services;

import com.albo.marvel.modelsAPI.CharacterAPI;
import java.util.List;

/**
 *
 * @author Avelino
 */
public interface MarvelServices {
    
    // Return all characters
    List<CharacterAPI> getCharacters();
    
    //Return a character with id
    CharacterAPI getCharacter(Integer id);
    
}
