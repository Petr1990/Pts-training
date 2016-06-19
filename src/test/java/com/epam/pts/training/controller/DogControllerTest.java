package com.epam.pts.training.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.Test;

import javax.annotation.PostConstruct;
import java.nio.charset.Charset;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:test-training-servlet.xml" })
public class DogControllerTest extends AbstractTestNGSpringContextTests {
    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @PostConstruct
    void postConstruct() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test()
    public void testGetDogs() {
        given().when().
            get("/dog").
            then().
            statusCode(200);
    }

    @Test()
    void testGetDogsController() throws Exception {
        mockMvc.perform(get("/dog"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(new MediaType(MediaType.APPLICATION_JSON.getType(),
                        MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"))))
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].id", is(0)))
                .andExpect(jsonPath("$[0].name", is("FirstDog")))
                .andExpect(jsonPath("$[0].birthDate", nullValue()))
                .andExpect(jsonPath("$[0].height", nullValue()))
                .andExpect(jsonPath("$[0].weight", nullValue()))
                .andExpect(jsonPath("$[1].id", is(1)))
                .andExpect(jsonPath("$[1].name", is("SecondDog")))
                .andExpect(jsonPath("$[1].birthDate", nullValue()))
                .andExpect(jsonPath("$[1].height", nullValue()))
                .andExpect(jsonPath("$[1].weight", nullValue()))
                .andExpect(jsonPath("$[2].id", is(2)))
                .andExpect(jsonPath("$[2].name", is("ThirdDog")))
                .andExpect(jsonPath("$[2].birthDate", nullValue()))
                .andExpect(jsonPath("$[2].height", nullValue()))
                .andExpect(jsonPath("$[2].weight", nullValue()));
    }
}
