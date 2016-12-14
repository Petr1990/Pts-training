package com.epam.pts.training.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

import static io.qala.datagen.RandomShortApi.integer;
import static io.qala.datagen.RandomShortApi.nullOr;
import static io.qala.datagen.RandomShortApi.unicode;
import static io.qala.datagen.RandomValue.between;

public class Dog {
    private static final Logger logger = LogManager.getLogger(Dog.class);

    private static AtomicInteger count = new AtomicInteger(0);

    //Add a unique integer identifier to Dog
    //Implement validation with Hibernate Validator (see the validation rules at the top), test the validation
    private Integer id;
    @Size(min = 1, max = 100)
    private String name;
    @Past
    private Date birthDate;
    @Min(1)
    private Integer height;
    @Min(1)
    private Integer weight;

    public Dog() {
        this.id = count.getAndIncrement();
    }

    public Dog(String name) {
        this.id = count.getAndIncrement();
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getName() {
        return name;
    }

    public Dog setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (id != null ? id.hashCode() : 0);
        result = prime * result + (name != null ? name.hashCode() : 0);
        result = prime * result + (birthDate != null ? birthDate.hashCode() : 0);
        result = prime * result + (height != null ? height.hashCode() : 0);
        result = prime * result + (weight != null ? weight.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Dog other = (Dog) obj;

        return id != null && id.equals(other.id)
                && ((name == null && other.name == null) || (name != null && name.equals(other.name)))
                && ((birthDate == null && other.birthDate == null) || (birthDate != null && birthDate.equals(other.birthDate)))
                && ((height == null && other.height == null) || (height != null && height.equals(other.height)))
                && ((weight == null && other.weight == null) || (weight != null && weight.equals(other.weight)));
    }

    @Override
    public String toString() {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        try {
            return ow.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            logger.error(e);
        }

        return null;
    }

    public static Dog random() {
        Dog dog = new Dog(unicode(1, 100));
        dog.birthDate = between(new Date().getTime() + 1, Long.MAX_VALUE).date();
        dog.setHeight(integer(1, Integer.MAX_VALUE));
        dog.setWeight(integer(1, Integer.MAX_VALUE));
        return dog;
    }
}
