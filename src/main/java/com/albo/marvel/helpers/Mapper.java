package com.albo.marvel.helpers;

import com.albo.marvel.models.Relation;
import com.albo.marvel.models.Character;
import com.albo.marvel.models.response.CharacterResponse;
import com.albo.marvel.models.response.CharactersResponse;

public class Mapper {
    
    public static CharactersResponse mappedToCharacterResponse(Character heroe){
        CharactersResponse response = new CharactersResponse();
        response.setLast_sync(heroe.getLastSync());
        for(Relation relation : heroe.getRelations()){
            CharacterResponse character = new CharacterResponse();
            character.setCharacter(relation.getCharacter().getName());
            character.getComics().add(relation.getComic().getDescription());
            response.getCharacters().add(character);
        }
        return response;
    }

    
}
