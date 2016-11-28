package com.epam.pts.training.controller;

import com.epam.pts.training.entity.Dog;
import com.epam.pts.training.utils.DogTestUtils;
import com.jayway.restassured.response.Response;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.Test;

import java.util.Arrays;

import static com.jayway.restassured.RestAssured.given;
import static org.unitils.reflectionassert.ReflectionAssert.assertReflectionEquals;

@Transactional
public class DogControllerEndPointTest {
    //Create a TestNG + RestAssured test that accesses that JSON, parses it and compares to the expected result
    @Test()
    public void whenGettingDogsResponseIsValidJson() {
        Response response = given().log().all().
                    contentType("application/json").
                when().
                    get("/dogs").
                then().
                    statusCode(200).
                extract().
                    response();
        assertReflectionEquals(DogTestUtils.DOGS, Arrays.asList(response.as(Dog[].class)));
    }
}
