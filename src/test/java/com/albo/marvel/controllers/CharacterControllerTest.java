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
import com.albo.marvel.services.CharacterServices;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CharacterController.class)
public class CharacterControllerTest {
    
    private static final Logger LOG = LoggerFactory.getLogger(CharacterControllerTest.class);

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CharacterServices characterServices;

    @MockBean
    private RestTemplate restTemplate;

    @Test
    void testCharactersOk() {
        LOG.info("Started test of get characters");
        try{
            mvc.perform(get("/characters/test")).andExpect(status().isOk());
            LOG.info("Ends test of get characters");
        }catch(Exception e){
            TestError.log(CharacterControllerTest.class, e);
        }
    }
}