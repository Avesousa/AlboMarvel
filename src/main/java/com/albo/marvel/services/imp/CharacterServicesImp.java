/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.albo.marvel.services.imp;

import com.albo.marvel.ws.models.CharacterAPI;
import com.albo.marvel.services.CharacterServices;
import java.util.List;
import com.albo.marvel.models.Character;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.albo.marvel.daos.RelationRepository;
import com.albo.marvel.daos.CharacterRepository;
import com.albo.marvel.models.Relation;
import com.albo.marvel.models.response.CharactersResponse;
import com.albo.marvel.ws.services.MarvelServices;
import java.security.NoSuchAlgorithmException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.HttpClientErrorException;

@Service
public class CharacterServicesImp implements CharacterServices {

    private static final Logger LOG = LoggerFactory.getLogger(CharacterServicesImp.class);

    @Autowired
    private CharacterRepository characterRepository;

    @Autowired
    private RelationRepository characterRelatedRepository;

    @Autowired
    private MarvelServices marvelService;

    @Override
    public CharactersResponse find(String username) throws NoSuchAlgorithmException, HttpClientErrorException {
        Character heroe = characterRepository.findByHeroe(username)
        
    }
    
    @Override
    public void updateData() {
        List<Character> heroes = characterRepository.findOnlyUsername();
        heroes.forEach(heroe -> {
            try {
                List<CharacterAPI> charactersAPI = marvelService.getCharacters(heroe.getId());
                //Se eliminan todos los personajes para sincronizar.
                characterRelatedRepository.DeleteByHeroe(heroe.getId());
                //Se asigna al heroe los personajes realacionados.
                charactersAPI.forEach(characterAPI -> {
                    Character relatedCharacter = new Character(characterAPI.getId(), characterAPI.getName());
                    characterRepository.save(relatedCharacter);
                    characterRepository.save(heroe);
                    characterRelatedRepository.save(heroe, relatedCharacter);
                });
            } catch (NoSuchAlgorithmException ex) {
                LOG.error("An error has occurred converting the format");
            } catch (HttpClientErrorException ex) {
                LOG.error(String.format("[%d] %s", ex.getRawStatusCode(), ex.getResponseBodyAsString()));
            }
        });

    }

}
