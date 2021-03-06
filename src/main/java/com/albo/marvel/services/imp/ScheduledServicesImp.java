package com.albo.marvel.services.imp;

import java.util.List;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.albo.marvel.models.Hero;
import com.albo.marvel.repositories.HeroRepository;
import com.albo.marvel.services.ScheduledServices;
import com.albo.marvel.services.CharacterServices;
import com.albo.marvel.services.CollaboratorServices;
import com.albo.marvel.ws.services.MarvelServices;
import com.albo.marvel.ws.models.ComicAPI;
import com.albo.marvel.ws.models.CreatorAPI;
import com.albo.marvel.ws.models.ListAPI;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

@Service
public class ScheduledServicesImp implements ScheduledServices {

    private static final Logger LOG = LoggerFactory.getLogger(ScheduledServicesImp.class);

    @Autowired
    private CollaboratorServices collaboratorService;

    @Autowired
    private CharacterServices characterService;

    @Autowired
    private MarvelServices marvelService;

    @Autowired
    private HeroRepository heroRepository;

    @Override
    @Scheduled(cron = "0 0 1 * * *")
    @Transactional
    public void syncData() {
        try {
            LOG.info("Running sync data");
            updateData();
        } catch (NoSuchAlgorithmException e) {
            LOG.error(String.format("[Error MD5] :: %s", e.getMessage()));
        } catch (HttpClientErrorException e) {
            LOG.error(String.format("[Error HTTP client] :: %s", e.getMessage()));
        } catch (IOException e) {
            LOG.error(String.format("[Error mapped] :: %s", e.getMessage()));
        }
    }

    public void updateData() throws NoSuchAlgorithmException, HttpClientErrorException, IOException {
        List<Hero> heroes = heroRepository.findAll();
        for (Hero hero : heroes) {
            List<ComicAPI> comics = marvelService.getComics(hero.getId());
            ListAPI<CreatorAPI> collaborators = getCollaborators(comics);
            characterService.update(comics, hero);
            collaboratorService.update(collaborators, hero);
        }
    }

    private ListAPI<CreatorAPI> getCollaborators(List<ComicAPI> comics) {
        ListAPI<CreatorAPI> collaborators = new ListAPI<CreatorAPI>();
        for (ComicAPI comic : comics) {
            collaborators.getItems().addAll(comic.getCreators().getItems());
        }
        return collaborators;
    }

}
