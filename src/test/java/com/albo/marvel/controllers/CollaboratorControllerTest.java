package com.albo.marvel.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.albo.marvel.helpers.TestError;
import com.albo.marvel.services.CollaboratorServices;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CollaboratorController.class)
public class CollaboratorControllerTest {
    
    private static final Logger LOG = LoggerFactory.getLogger(CollaboratorControllerTest.class);

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CollaboratorServices CollaboratorServices;

    @MockBean
    private RestTemplate restTemplate;

    @Test
    void testCollaboratorsOk() {
        LOG.info("Started test of get collaborators");
        try{
            mvc.perform(get("/colaborators/test")).andExpect(status().isOk());
            LOG.info("Ends test of get collaborators");
        }catch(Exception e){
            TestError.log(CollaboratorControllerTest.class, e);
        }
    }
}