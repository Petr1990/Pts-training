package com.epam.pts.training.controller;

import com.epam.pts.training.entity.Dog;
import com.epam.pts.training.utils.DogTestUtils;
import com.jayway.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Arrays;

import static com.jayway.restassured.RestAssured.given;
import static org.unitils.reflectionassert.ReflectionAssert.assertReflectionEquals;

public class DogControllerEndPointTest {
    //Create a TestNG + RestAssured test that accesses that JSON, parses it and compares to the expected result
    @Test()
    public void whenGettingDogsResponseIsValidJson() {
        DogTestUtils.DOGS.forEach(this::createDog);

        try {
            Response response = given().log().all().
                    contentType("application/json").
                    when().
                    get("/dogs").
                    then().
                    statusCode(200).
                    extract().
                    response();
            assertReflectionEquals(DogTestUtils.DOGS, Arrays.asList(response.as(Dog[].class)));
        } finally {
            DogTestUtils.DOGS.forEach(this::deleteDog);
        }
    }

    private void createDog(Dog dog) {
        Response response = given().log().all().
                contentType("application/json").
                body(dog.toString()).
                when().
                post("/dog").
                then().
                statusCode(200).
                extract().
                response();
    }

    private void deleteDog(Dog dog) {
        given().log().all().
                contentType("application/json").
                when().
                delete("/dog/" + dog.getId()).
                then().
                statusCode(200).
                extract().
                response();
    }
}
