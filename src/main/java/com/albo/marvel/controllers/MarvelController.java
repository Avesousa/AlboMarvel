package com.albo.marvel.controllers;

import com.albo.marvel.models.response.CollaboratorsResponse;
import com.albo.marvel.services.ScheduledServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

@ApiResponse(responseCode = "200", description = "Data sync successfully")
@RestController
@RequestMapping("/")
@EnableAutoConfiguration
public class MarvelController {

    @Autowired
    private ScheduledServices scheduledServices;

    @Operation(summary = "Synchronize information")
    @PutMapping
    @Transactional
    public ResponseEntity syncData() throws NoSuchAlgorithmException, HttpClientErrorException, IOException {
        scheduledServices.updateData();
        return ResponseEntity.ok("Data sync successfully");
    }

}
