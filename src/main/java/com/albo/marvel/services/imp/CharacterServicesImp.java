package com.albo.marvel.services.imp;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.albo.marvel.models.CharacterComic;
import com.albo.marvel.models.Hero;
import com.albo.marvel.models.Character;
import com.albo.marvel.models.Comic;
import com.albo.marvel.models.response.CharacterResponse;
import com.albo.marvel.models.response.CharactersResponse;
import com.albo.marvel.services.CharacterServices;
import com.albo.marvel.repositories.CharacterRepository;
import com.albo.marvel.repositories.CharacterComicRepository;
import com.albo.marvel.repositories.ComicRepository;
import com.albo.marvel.repositories.HeroRepository;
import com.albo.marvel.ws.models.ComicAPI;
import com.albo.marvel.exception.NotFoundContentException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CharacterServicesImp implements CharacterServices {

    private static final Logger LOG = LoggerFactory.getLogger(CharacterServicesImp.class);

    @Autowired
    private CharacterRepository characterRepository;

    @Autowired
    private HeroRepository heroRepository;

    @Autowired
    private ComicRepository comicRepository;

    @Autowired
    private CharacterComicRepository characterComicRepository;

    @Override
    public CharactersResponse get(String username) throws NotFoundContentException {
        try {
            Hero hero = heroRepository.findByUsername(username);
        CharactersResponse charactersResponse = new CharactersResponse();
        charactersResponse.setLast_sync(hero.getLastSync());
        for (CharacterComic characterComic : hero.getCharactersAndComics()) {
            List<CharacterResponse> characters = charactersResponse.getCharacters();
            CharacterResponse characterResponse = characters
                    .stream()
                    .filter((character) -> character
                    .getCharacter()
                    .equals(characterComic
                            .getCharacter()
                            .getName()))
                    .findFirst().orElse(null);
            if(characterResponse != null) {
                characterResponse.getComics().add(characterComic.getComic().getTitle());
            }else {
                characterResponse = new CharacterResponse();
                characterResponse.setCharacter(characterComic.getCharacter().getName());
                characterResponse.getComics().add(characterComic.getComic().getTitle());
                charactersResponse.getCharacters().add(characterResponse);
            }
        }
        LOG.info(String.format("Characters obtained [%d/%s]", hero.getId(), hero.getName()));
        return charactersResponse;
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundContentException(String.format("Characters not found for %s", username));
        }
        
    }

    @Override
    public void update(List<ComicAPI> comics, Hero hero){
        characterComicRepository.delete(hero.getId());
        for (ComicAPI comicAPI : comics) {
            Comic comic = new Comic(comicAPI.getTitle());
            comicRepository.save(comic);
            comicAPI.getCharacters().getItems().stream().forEach((characterAPI) -> {
                Character character = new Character(characterAPI.getId(), characterAPI.getName());
                characterRepository.save(character);
                CharacterComic characterComic = new CharacterComic(hero, character, comic);
                characterComicRepository.save(characterComic);
            });
        }
        heroRepository.update(hero);
        LOG.info(String.format("The characters have been successfully updated [%d/%s]", hero.getId(), hero.getName()));
    }
}
