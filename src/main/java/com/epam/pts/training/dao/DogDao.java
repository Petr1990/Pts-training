package com.epam.pts.training.dao;

import com.epam.pts.training.entity.Dog;

import java.util.Collection;

public interface DogDao {
    Collection<Dog> getDogs();

    Dog createDog(Dog dog);

    Dog getDog(Integer id);

    Dog updateDog(Dog dog);

    void deleteDog(Integer id);
}
