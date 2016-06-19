package com.epam.pts.training.service;

import com.epam.pts.training.dao.DogDao;
import com.epam.pts.training.entity.Dog;

import java.util.Collection;

public class DogService {
    private DogDao dogDao;

    public Collection<Dog> getDogs() {
        return dogDao.getDogs();
    }

    public void setDogDao(DogDao dogDao) {
        this.dogDao = dogDao;
    }
}
