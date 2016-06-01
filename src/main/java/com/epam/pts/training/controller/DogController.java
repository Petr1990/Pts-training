package com.epam.pts.training.controller;

import com.epam.pts.training.entity.Dog;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Collection;

@RestController
public class DogController {

    public final static Collection<Dog> DOGS = Arrays.asList(new Dog("First"), new Dog("Second"));

    @RequestMapping("/dog")
    public Collection<Dog> getDogs() {
        return DOGS;
    }
}