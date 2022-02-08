package com.albo.marvel.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import com.albo.marvel.services.CharacterServices;
import com.albo.marvel.exception.NotFoundContentException;
import com.albo.marvel.models.response.CharactersResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@ApiResponse(responseCode = "200", description = "Get all characters", content = {
    @Content(mediaType = "application/json", schema = @Schema(implementation = CharactersResponse.class))
})
@RestController
@RequestMapping("characters")
@EnableAutoConfiguration
public class CharacterController {

    @Autowired
    private CharacterServices characterServices;
    
    @Operation(summary = "Get all characters")
    @GetMapping("/{username}")
    @Transactional
    public ResponseEntity<CharactersResponse> getCharacters(@PathVariable String username) throws NotFoundContentException{
        return ResponseEntity.ok(characterServices.get(username));
    }
}
