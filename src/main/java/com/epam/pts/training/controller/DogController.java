package com.epam.pts.training.controller;

import com.epam.pts.training.entity.Dog;
import com.epam.pts.training.service.DogService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class DogController {
    private DogService dogService;

    @RequestMapping("/dog")
    public Collection<Dog> getDogs() {
        return dogService.getDogs();
    }

    public void setDogService(DogService dogService) {
        this.dogService = dogService;
    }
}