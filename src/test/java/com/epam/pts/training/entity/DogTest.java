package com.epam.pts.training.entity;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import static io.qala.datagen.RandomShortApi.alphanumeric;
import static io.qala.datagen.RandomShortApi.*;
import static io.qala.datagen.RandomValue.between;
import static org.testng.Assert.*;

public class DogTest {

    private static Validator validator;

    @BeforeClass
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test public void validationPassesForValidDog() {
        Set<ConstraintViolation<Dog>> validationResults = validator.validate(Dog.random());
        assertEquals(validationResults.size(), 0);
    }

    @Test public void validationFailsForInvalidName() {
        assertValidationFails(Dog.random().setName(nullOrBlank()), "size must be between 1 and 100");
        assertValidationFails(Dog.random().setName(unicode(101)), "size must be between 1 and 100");
    }

    private void assertValidationFails(Dog badNameDog, String actual) {
        Set<ConstraintViolation<Dog>> constraintViolations = validator.validate(badNameDog);
        assertEquals(1, constraintViolations.size());
        assertEquals(actual, constraintViolations.iterator().next().getMessage());
    }

    @Test
    public void dogBirthDateShouldBeInPast() {
        Dog futureDog = new Dog("futureDog");

        futureDog.setBirthDate(between(new Date().getTime() + 1, Long.MAX_VALUE).date());

        Set<ConstraintViolation<Dog>> constraintViolations = validator.validate(futureDog);

        assertEquals(1, constraintViolations.size());
        Iterator<ConstraintViolation<Dog>> iterator = constraintViolations.iterator();
        assertEquals("must be in the past", iterator.next().getMessage());
    }

    @Test
    public void dogSizeAndWeightShouldBeLargerThanZero() {
        Dog negativeDog = new Dog("negativeDog");

        negativeDog.setHeight(0);
        negativeDog.setWeight(-1);

        Set<ConstraintViolation<Dog>> constraintViolations = validator.validate(negativeDog);

        assertEquals(2, constraintViolations.size());
        Iterator<ConstraintViolation<Dog>> iterator = constraintViolations.iterator();
        assertEquals("must be greater than or equal to 1", iterator.next().getMessage());
        assertEquals("must be greater than or equal to 1", iterator.next().getMessage());
    }
}
