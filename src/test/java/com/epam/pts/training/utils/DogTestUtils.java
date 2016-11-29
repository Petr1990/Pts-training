package com.epam.pts.training.utils;

import com.epam.pts.training.entity.Dog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DogTestUtils {
    public final static List<Dog> DOGS = new ArrayList<>();
    static {
        Dog firstDog = new Dog("FirstDog");
        firstDog.setId(0);
        Dog secondDog = new Dog("SecondDog");
        secondDog.setId(1);
        Dog thirdDog = new Dog("ThirdDog");
        thirdDog.setId(2);
        DOGS.addAll(Arrays.asList(firstDog, secondDog, thirdDog));
    }
}
