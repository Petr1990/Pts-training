package com.epam.pts.training.dao;

import com.epam.pts.training.entity.Dog;

import java.util.Arrays;
import java.util.Collection;

public class DogDaoImpl implements DogDao {
    public final static Collection<Dog> DOGS = Arrays.asList(new Dog("First"), new Dog("Second"));

    public Collection<Dog> getDogs() {
        return DOGS;
    }
}
