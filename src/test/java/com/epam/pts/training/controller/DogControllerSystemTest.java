package com.epam.pts.training.controller;


import com.epam.pts.training.entity.Dog;
import com.epam.pts.training.utils.DogTestUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.unitils.reflectionassert.ReflectionAssert.assertReflectionEquals;

@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:test-training-servlet.xml"})
//Make the tests to rollback the transactions after each test
@Transactional
public class DogControllerSystemTest extends AbstractTransactionalTestNGSpringContextTests {
    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @BeforeMethod
    void postConstruct() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    //Implement Component REST tests by using Spring’s MockMVC
    //Ensure the test names follow BDD
    @Test()
    public void dogsShouldReturnThreeCorrectDogs() throws Exception {//todo: should save and check for contains
        MvcResult result = mockMvc.perform(get("/dogs"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(new MediaType(MediaType.APPLICATION_JSON.getType(),
                        MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"))))
                .andDo(print())
                .andReturn();
        List actualDogs = new ObjectMapper().readValue(result.getResponse().getContentAsString(), new TypeReference<List<Dog>>() {});

        assertReflectionEquals(DogTestUtils.DOGS, actualDogs);
    }
    //todo: check all endpoints
    //todo: if needed add validation tests
}
