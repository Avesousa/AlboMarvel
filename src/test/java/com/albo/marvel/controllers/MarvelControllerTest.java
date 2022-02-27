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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.albo.marvel.helpers.TestError;
import com.albo.marvel.services.ScheduledServices;

@ExtendWith(SpringExtension.class)
@WebMvcTest(MarvelController.class)
public class MarvelControllerTest {
    
    private static final Logger LOG = LoggerFactory.getLogger(MarvelControllerTest.class);

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ScheduledServices scheduledServices;

    @MockBean
    private RestTemplate restTemplate;

    @Test
    void testSyncDataOk() {
        LOG.info("Started test of sync data");
        try{
            mvc.perform(put("/")).andExpect(content().string("Data sync successfully"));
            LOG.info("Ends test of sync data!");
        }catch(Exception e){
            TestError.log(MarvelControllerTest.class, e);
        }
    }

    @Test
    void testSyncDataEndpointAndMethodNotOk() {
        LOG.info("Started test of sync data with endpoint and method that not ok");
        try{
            LOG.info("Testing method GET!");
            mvc.perform(get("/")).andExpect(status().isMethodNotAllowed());
            LOG.info("Testing method POST!");
            mvc.perform(post("/")).andExpect(status().isMethodNotAllowed());
            LOG.info("Testing method DELETE!");
            mvc.perform(delete("/")).andExpect(status().isMethodNotAllowed());
            LOG.info("Testing other endpoint");
            mvc.perform(put("/test")).andExpect(status().isNotFound());
            LOG.info("Ends test of sync data with endpoint and method that not ok");
        }catch(Exception e){
            TestError.log(MarvelControllerTest.class, e);
        }
    }
}