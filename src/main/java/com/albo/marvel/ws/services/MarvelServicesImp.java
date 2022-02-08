/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.albo.marvel.ws.services;

import com.albo.marvel.ws.models.CharacterAPI;
import com.albo.marvel.ws.models.ComicAPI;
import com.albo.marvel.ws.models.CreatorAPI;
import com.albo.marvel.ws.models.ResponseAPI;
import com.albo.marvel.exception.ApiExceptionHandler;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import com.albo.marvel.helpers.Converter;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Avelino
 */
@Service
public class MarvelServiceImp implements MarvelServices {

    @Autowired
    RestTemplate restTemplate;

    private static final Logger LOG = LoggerFactory.getLogger(MarvelServiceImp.class);

    private static String HOST = "http://gateway.marvel.com/v1/public";
    private static String KEY_PUBLIC = "20bd3e95d5e84bc6d6962d3335004231";
    private static String KEY_PRIVATE = "9f3b40279eb51e53ef6e2886b5ce242821b33379";
    private static Integer TS = 1;

    @Override
    public List<CharacterAPI> getCharacters(Integer characterId) throws NoSuchAlgorithmException, HttpClientErrorException {
        List<ComicAPI> comics = getComic(characterId);
        List<CharacterAPI> characters = new ArrayList<CharacterAPI>();
        
        for(ComicAPI comic : comics){
            String endpoint = getEndpointCharacterByComic(getHash(), comic.getId());
            List<CharacterAPI> charactersComic = restTemplate.getForObject(endpoint, ResponseAPI.class).getData().getResults();
            characters.addAll(charactersComic);
        }
        LOG.info(String.format("[Characters] :: Got characters successfull of %d", characterId));
        return characters;
    }

    @Override
    public List<CreatorAPI> getCollaborator(Integer characterId) throws NoSuchAlgorithmException, HttpClientErrorException {
        List<ComicAPI> comics = getComic(characterId);
        List<CreatorAPI> creators = new ArrayList<CreatorAPI>();
        
        for(ComicAPI comic : comics){
            creators.addAll(comic.getCreators().getItems());
        }
        LOG.info(String.format("[Collaborators] :: Got Collaborators successfull of %d", characterId));
        return creators;
    }
    
    private List<ComicAPI> getComic(Integer characterId) throws NoSuchAlgorithmException, HttpClientErrorException{
         String endpoint = getEndpointComicByHeroe(getHash(), characterId);
         List<ComicAPI> comics = (List<ComicAPI>) restTemplate.getForObject(endpoint, ResponseAPI.class).getData().getResults();
         LOG.info("[Comic] :: Got comics successfull");
         return comics;
    }
    
        private <T extends Object> List<T> getResults(ResponseAPI response){
        return (List<T>)response.getData().getResults();
    }
    
    private String getHash() throws NoSuchAlgorithmException {
        return Converter.MD5(TS + KEY_PRIVATE + KEY_PUBLIC);
    }

    private String getEndpointComicByHeroe(String hash, Integer characterId) {
        //{host}/characters/{characterId}/comics?ts={timestamp}&apikey={keypublic}&hash={hash}
        return String.format("%s/characters/%d/comics?ts=%d&apikey=%s&hash=%s", HOST, characterId, TS, KEY_PUBLIC, hash);
    }
    
    private String getEndpointCharacterByComic(String hash, Integer comicId) {
        //{host}/comics/{comicId}/characters?ts={timestamp}&apikey={keypublic}&hash={hash}
        return String.format("%s/characters/%d/comics?ts=%d&apikey=%s&hash=%s", HOST, comicId, TS, KEY_PUBLIC, hash);
    }

}
 