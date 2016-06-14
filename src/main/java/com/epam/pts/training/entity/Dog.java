package com.epam.pts.training.entity;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

public class Dog {
    private static AtomicInteger count = new AtomicInteger(0);

    private Integer id;
    private String name;
    private Date birthDate;
    private Integer height;
    private Integer weight;

    public Dog() {
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

    public void setName(String name) {
        this.name = name;
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
}
