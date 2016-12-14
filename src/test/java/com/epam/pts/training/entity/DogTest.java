package com.epam.pts.training.entity;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import static io.qala.datagen.RandomShortApi.alphanumeric;
//import static io.qala.datagen.RandomDate.*;

public class DogTest {

    private static Validator validator;

    @BeforeClass
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void dogIdShouldntBeNull() {
        Dog nullDog = new Dog("nullDog");

        nullDog.setId(null);

        Set<ConstraintViolation<Dog>> constraintViolations = validator.validate(nullDog);

        Assert.assertEquals(1, constraintViolations.size());
        Iterator<ConstraintViolation<Dog>> iterator = constraintViolations.iterator();
        Assert.assertEquals("may not be null", iterator.next().getMessage());
    }

    @Test
    public void dogNameShouldBeBetweenOneAndHundredSymbols() {
        Dog badNameDog = new Dog("badNameDog");

        badNameDog.setName("");

        Set<ConstraintViolation<Dog>> constraintViolations = validator.validate(badNameDog);

        Assert.assertEquals(1, constraintViolations.size());
        Iterator<ConstraintViolation<Dog>> iterator = constraintViolations.iterator();
        Assert.assertEquals("size must be between 1 and 100", iterator.next().getMessage());

        badNameDog.setName(alphanumeric(101));

        constraintViolations = validator.validate(badNameDog);

        Assert.assertEquals(1, constraintViolations.size());
        iterator = constraintViolations.iterator();
        Assert.assertEquals("size must be between 1 and 100", iterator.next().getMessage());
    }

    @Test
    public void dogBirthDateShouldBeInPast() {
        Dog futureDog = new Dog("futureDog");

        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, 1);
        futureDog.setBirthDate(c.getTime());

        Set<ConstraintViolation<Dog>> constraintViolations = validator.validate(futureDog);

        Assert.assertEquals(1, constraintViolations.size());
        Iterator<ConstraintViolation<Dog>> iterator = constraintViolations.iterator();
        Assert.assertEquals("must be in the past", iterator.next().getMessage());
    }

    @Test
    public void dogSizeAndWeightShouldBeLargerThanZero() {
        Dog negativeDog = new Dog("negativeDog");

        negativeDog.setHeight(0);
        negativeDog.setWeight(-1);

        Set<ConstraintViolation<Dog>> constraintViolations = validator.validate(negativeDog);

        Assert.assertEquals(2, constraintViolations.size());
        Iterator<ConstraintViolation<Dog>> iterator = constraintViolations.iterator();
        Assert.assertEquals("must be greater than or equal to 1", iterator.next().getMessage());
        Assert.assertEquals("must be greater than or equal to 1", iterator.next().getMessage());
    }
}
