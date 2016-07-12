package com.epam.pts.training.dao;

import com.epam.pts.training.entity.Dog;
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
@Transactional
public class DogDaoHibernateTest extends AbstractTransactionalTestNGSpringContextTests {
    @Autowired
    private DogDao dogDao;

    //Implement Hibernate Dao Tests - they need to import spring context and start the embedded H2 DB
    @Test()
    public void shouldCompareGetDogResponseWithStaticCollection() {
        Dog firstDog = new Dog("FirstDog");
        firstDog.setId(0);
        Dog secondDog = new Dog("SecondDog");
        secondDog.setId(1);
        Dog thirdDog = new Dog("ThirdDog");
        thirdDog.setId(2);
        List<Dog> referenceDogs = Arrays.asList(firstDog, secondDog, thirdDog);

        List<Dog> dogs = new ArrayList<Dog>(dogDao.getDogs());

        Assert.assertEquals(dogs, referenceDogs);
    }
}
