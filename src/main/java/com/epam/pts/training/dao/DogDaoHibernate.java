package com.epam.pts.training.dao;

import com.epam.pts.training.entity.Dog;

import java.util.Collection;

//Implement an alternative implementation of DAO. Instead of re-writing the previous one, create interfaces that are implemented
// both by InMemory DAO and by Hibernate DAO. It should be possible to switch between DAO implementations between startups
// via Spring means (find your own way).
public class DogDaoHibernate extends AbstractDao implements DogDao {
    public Collection<Dog> getDogs() {
        return getSession().createQuery("from Dog", Dog.class).getResultList();
    }
}
