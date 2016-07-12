package com.epam.pts.training.dao;

import com.epam.pts.training.entity.Dog;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collection;

public class DogDaoImplTest {
    private DogDao dogDao = new DogDaoImpl();

    //Implement a unit test for DAO layer - it checks static collections against expected result
    @Test()
    public void shouldCompareGetDogResponseWithStaticCollection() {
        Dog firstDog = new Dog("First");
        firstDog.setId(0);
        Dog secondDog = new Dog("Second");
        secondDog.setId(1);
        Collection<Dog> referenceDogs = Arrays.asList(firstDog, secondDog);

        Collection<Dog> dogs = dogDao.getDogs();

        Assert.assertEquals(dogs, referenceDogs);
    }
}
