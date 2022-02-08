package com.albo.marvel.ws.services;

import java.util.List;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.albo.marvel.ws.models.ComicAPI;
import com.albo.marvel.ws.models.ResponseAPI;
import com.albo.marvel.helpers.Converter;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.HttpClientErrorException;

@Service
public class MarvelServicesImp implements MarvelServices {

    @Autowired
    RestTemplate restTemplate;

    private static final Logger LOG = LoggerFactory.getLogger(MarvelServicesImp.class);

    private static String HOST = "http://gateway.marvel.com/v1/public";
    private static String KEY_PUBLIC = "20bd3e95d5e84bc6d6962d3335004231";
    private static String KEY_PRIVATE = "9f3b40279eb51e53ef6e2886b5ce242821b33379";
    private static Integer TS = 1;

    @Override
    public List<ComicAPI> getComics(Integer characterId) throws NoSuchAlgorithmException, HttpClientErrorException, IOException {
        String endpoint = getEndpointComicByHeroe(getHash(), characterId);
        ResponseAPI response = new ObjectMapper().convertValue(restTemplate.getForObject(endpoint, ResponseAPI.class), new TypeReference<ResponseAPI>() {
        });
        List<ComicAPI> comics = new ObjectMapper().convertValue(response.getData().getResults(), new TypeReference<List<ComicAPI>>() {
        });
        LOG.info("Got comics successfull");
        return comics;
    }

    private String getHash() throws NoSuchAlgorithmException {
        return Converter.MD5(TS + KEY_PRIVATE + KEY_PUBLIC).toLowerCase();
    }

    private String getEndpointComicByHeroe(String hash, Integer characterId) {
        //{host}/characters/{characterId}/comics?ts={timestamp}&apikey={keypublic}&hash={hash}
        return String.format("%s/characters/%d/comics%s", HOST, characterId, getQuery(hash));
    }
    
    private String getQuery(String hash) {
        return String.format("?orderBy=-modified&ts=%d&apikey=%s&hash=%s&limit=5", TS, KEY_PUBLIC, hash);
    }

}
