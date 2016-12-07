package com.epam.pts.training.dao;

import com.epam.pts.training.entity.Dog;
import com.epam.pts.training.utils.DogTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ContextConfiguration(locations = {"classpath:test-training-servlet.xml"})
//Make the tests to rollback the transactions after each test
//@Transactional
public class DogDaoHibernateTest extends AbstractTransactionalTestNGSpringContextTests {
    @Autowired
    private DogDao dogDao;

    //Implement Hibernate Dao Tests - they need to import spring context and start the embedded H2 DB
    @Test()
    public void shouldCompareGetDogResponseWithStaticCollection() {
        List<Dog> dogs = new ArrayList<>(dogDao.getDogs());

        Assert.assertEquals(dogs, DogTestUtils.DOGS);
    }

    @Test(expectedExceptions = javax.persistence.PersistenceException.class)
    public void shouldFailIfTryingCreateDogWithNullId() {
        Dog zeroDog = new Dog("zeroDog");

        zeroDog.setId(null);

        dogDao.createDog(zeroDog);
    }

    @Test(/*expectedExceptions = javax.persistence.PersistenceException.class*/)
    public void shouldCheckHibernateValidation() {
        Dog zeroDog = new Dog("zeroDog");

        zeroDog.setHeight(-1);
        zeroDog.setWeight(-1);

        zeroDog = dogDao.createDog(zeroDog);
        zeroDog = dogDao.getDog(zeroDog.getId());
    }
}
