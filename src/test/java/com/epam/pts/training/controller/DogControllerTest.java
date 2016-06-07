package com.epam.pts.training.controller;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.expect;
import static org.junit.matchers.JUnitMatchers.hasItems;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@WebAppConfiguration
public class DogControllerTest {

    @Autowired
    private DogController dogController;

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = webAppContextSetup(wac).build();
    }

    @Test()
    public void testGetDogs() {
        expect().
                statusCode(200).
                body("name", hasItems("First", "Second")).
                when().
                get("/pts-training/dog");
    }

    @Test()
    public void shouldReturnGetDogsCollection() throws Exception {
        mockMvc = webAppContextSetup(wac).build();
        mockMvc.perform(get("/pts-training/dog"))
                .andExpect(status().isOk());
    }

    public void setDogController(DogController dogController) {
        this.dogController = dogController;
    }
}
