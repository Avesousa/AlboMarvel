package com.albo.marvel.controllers;

import com.albo.marvel.exception.NotFoundContentException;
import com.albo.marvel.models.response.CollaboratorsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.albo.marvel.services.CollaboratorServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

@ApiResponse(responseCode = "200", description = "Get all collaborators", content = {
    @Content(mediaType = "application/json", schema = @Schema(implementation = CollaboratorsResponse.class))
})
@RestController
@RequestMapping("colaborators")
@EnableAutoConfiguration
public class CollaboratorController {

    @Autowired
    private CollaboratorServices collaboratorServices;

    @Operation(summary = "Get all collaborators")
    @GetMapping("/{username}")
    @Transactional
    public ResponseEntity<CollaboratorsResponse> getColaborators(@PathVariable String username) throws NotFoundContentException {
        return ResponseEntity.ok(collaboratorServices.get(username));
    }
}
