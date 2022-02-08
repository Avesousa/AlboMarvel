package com.albo.marvel.services.imp;

import com.albo.marvel.exception.NotFoundContentException;
import com.albo.marvel.models.Collaborator;
import org.springframework.beans.factory.annotation.Autowired;

import com.albo.marvel.repositories.HeroRepository;

import com.albo.marvel.models.Hero;
import com.albo.marvel.models.HeroCollaborator;
import com.albo.marvel.models.response.CollaboratorsResponse;
import com.albo.marvel.repositories.CollaboratorRepository;
import com.albo.marvel.repositories.HeroCollaboratorRepository;
import com.albo.marvel.ws.models.CreatorAPI;
import com.albo.marvel.ws.models.ListAPI;
import com.albo.marvel.services.CollaboratorServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CollaboratorServicesImp implements CollaboratorServices {

    private static final Logger LOG = LoggerFactory.getLogger(CollaboratorServicesImp.class);

    @Autowired
    private CollaboratorRepository collaboratorRepository;

    @Autowired
    private HeroRepository heroRepository;

    @Autowired
    private HeroCollaboratorRepository heroCollaboratorRepository;

    @Override
    public CollaboratorsResponse get(String username) throws NotFoundContentException {
        try {
            Hero hero = heroRepository.findByUsername(username);
            CollaboratorsResponse response = new CollaboratorsResponse(hero.getLastSync());
            for (HeroCollaborator heroCollaborator : hero.getCollaborators()) {
                Collaborator collaborator = heroCollaborator.getCollaborator();
                switch (collaborator.getType()) {
                    case "writer":
                        response.getWriters().add(collaborator.getName());
                        break;
                    case "colorist":
                        response.getColorists().add(collaborator.getName());
                        break;
                    case "editor":
                        response.getEditors().add(collaborator.getName());
                        break;
                    default:
                        break;
                }
            }
            return response;
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundContentException(String.format("Collaborators not found for %s", username));
        }

    }

    @Override
    public void update(ListAPI<CreatorAPI> creators, Hero hero) {
        heroCollaboratorRepository.delete(hero.getId());
        for (CreatorAPI creator : creators.getItems()) {
            Collaborator collaborator = new Collaborator(creator.getName(), creator.getRole());
            collaboratorRepository.save(collaborator);
            heroCollaboratorRepository.save(new HeroCollaborator(hero, collaborator));
        }
        heroRepository.update(hero);
        LOG.info(String.format("The collaborators have been successfully updated [%d/%s]", hero.getId(), hero.getName()));
    }
}
