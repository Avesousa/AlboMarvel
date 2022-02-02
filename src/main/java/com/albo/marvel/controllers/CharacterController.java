/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.albo.marvel.controllers;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import com.albo.marvel.exception.ApiExceptionHandler;
import com.albo.marvel.services.CharacterServices;
import com.albo.marvel.ws.services.MarvelServices;
import java.security.NoSuchAlgorithmException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

/**
 *
 * @author Avelino
 */
@RestController
@RequestMapping("characters")
public class CharacterController {

    private static final Logger LOG = LoggerFactory.getLogger(CharacterController.class);

    @Autowired
    private MarvelServices marveServices;
    
    @Autowired
    private CharacterServices characterServices;

    @GetMapping("/{username}")
    public ResponseEntity getCharacters(@PathVariable String username) throws HttpClientErrorException, NoSuchAlgorithmException {
        return ResponseEntity.ok(characterServices.find(username));
    }

}
