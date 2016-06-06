package com.epam.pts.training.controller;

import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.expect;
import static org.junit.matchers.JUnitMatchers.hasItems;

public class DogControllerTest {

    @Test()
    public void testGetDogs() {
        expect().
                statusCode(200).
                body("name", hasItems("First", "Second")).
                when().
                get("/pts-training/dog");
    }
}
