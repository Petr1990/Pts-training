package com.epam.pts.training.dao;

import com.epam.pts.training.entity.Dog;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

public class DogDaoHibernate extends AbstractDao implements DogDao {
    @Transactional
    public Collection<Dog> getDogs() {
        return getSession().createQuery("from Dog", Dog.class).getResultList();
    }
}
