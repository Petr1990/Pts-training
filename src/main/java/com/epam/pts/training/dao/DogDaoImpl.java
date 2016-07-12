package com.epam.pts.training.dao;

import com.epam.pts.training.entity.Dog;

import java.util.Arrays;
import java.util.Collection;

//Create DAO layer and move all static collections into that layer. Inject DAO objects into services with Spring IoC.
public class DogDaoImpl implements DogDao {
    //Create a static collection of dogs that is shown in JSON format when we access /dog URL
    public final static Collection<Dog> DOGS = Arrays.asList(new Dog("First"), new Dog("Second"));

    public Collection<Dog> getDogs() {
        return DOGS;
    }
}
