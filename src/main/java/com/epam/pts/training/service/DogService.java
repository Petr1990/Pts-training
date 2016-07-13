package com.epam.pts.training.service;

import com.epam.pts.training.dao.DogDao;
import com.epam.pts.training.entity.Dog;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

public class DogService {
    private DogDao dogDao;

    @Transactional
    public Collection<Dog> getDogs() {
        return dogDao.getDogs();
    }

    @Transactional
    public Dog createDog(Dog dog) {
        return dogDao.createDog(dog);
    }

    @Transactional
    public Dog getDog(Integer id) {
        return dogDao.getDog(id);
    }

    @Transactional
    public Dog updateDog(Dog dog) {
        return dogDao.updateDog(dog);
    }

    @Transactional
    public void deleteDog(Integer id) {
        dogDao.deleteDog(id);
    }

    public void setDogDao(DogDao dogDao) {
        this.dogDao = dogDao;
    }
}
