package com.epam.pts.training.controller;

import com.epam.pts.training.entity.Dog;
import com.epam.pts.training.service.DogService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class DogController {
    private DogService dogService;

    @RequestMapping(value = "/dogs", method = RequestMethod.GET)
    public Collection<Dog> getDogs() {
        return dogService.getDogs();
    }

    @RequestMapping(value = "/dog", method = RequestMethod.POST)
    public Dog createDog(@RequestBody Dog dog) {
        return dogService.createDog(dog);
    }

    @RequestMapping(value = "/dog/{id}", method = RequestMethod.GET)
    public Dog getDog(@PathVariable Integer id) {
        return dogService.getDog(id);
    }

    @RequestMapping(value = "/dog/{id}", method = RequestMethod.PUT)
    public Dog updateDog(@PathVariable Integer id, @RequestBody Dog dog) {
        dog.setId(id);

        return dogService.updateDog(dog);
    }

    @RequestMapping(value = "/dog/{id}", method = RequestMethod.DELETE)
    public void deleteDog(@PathVariable Integer id) {
        dogService.deleteDog(id);
    }

    public void setDogService(DogService dogService) {
        this.dogService = dogService;
    }
}