package com.epam.pts.training.controller;

import com.epam.pts.training.entity.Dog;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.Test;

import javax.annotation.PostConstruct;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:test-training-servlet.xml"})
public class DogControllerTest extends AbstractTestNGSpringContextTests {
    private final static List<Dog> DOGS = new ArrayList<Dog>(Arrays.asList(new Dog("FirstDog"), new Dog("SecondDog"), new Dog("ThirdDog")));
    private static final String DOGS_JSON = "[{\"id\":0,\"name\":\"FirstDog\",\"birthDate\":null,\"height\":null,\"weight\":null},{\"id\":1," +
            "\"name\":\"SecondDog\",\"birthDate\":null,\"height\":null,\"weight\":null},{\"id\":2,\"name\":\"ThirdDog\",\"birthDate\":null," +
            "\"height\":null,\"weight\":null}]";

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @PostConstruct
    void postConstruct() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test()
    public void whenGettingDogsResponseIsValidJson() {
        given().log().all().
                    contentType("application/json").
                when().
                    get("/dog").
                then().
                    body(equalTo(DOGS_JSON)).
                    statusCode(200);
    }

    @Test()
    void dogsShouldReturnThreeCorrectDogs() throws Exception {
        MvcResult result = mockMvc.perform(get("/dog"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(new MediaType(MediaType.APPLICATION_JSON.getType(),
                        MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"))))
                .andDo(print())
                .andReturn();
        List dogs = new ObjectMapper().readValue(result.getResponse().getContentAsString(), new TypeReference<List<Dog>>() {
        });

        Assert.assertTrue(new ReflectionEquals(DOGS).matches(dogs));
    }
}
