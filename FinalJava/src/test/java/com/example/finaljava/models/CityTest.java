package com.example.finaljava.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CityTest {
    City city = new City(0, "name", 0d, 0);

    @Test
    void testSetId() {
        city.setId(0);
    }

    @Test
    void testSetName() {
        city.setName("name");
    }

    @Test
    void testSetCoefficient() {
        city.setCoefficient(0d);
    }

    @Test
    void testSetPopulation() {
        city.setPopulation(0);
    }

    @Test
    void testEquals() {
        boolean result = city.equals("o");
        Assertions.assertNotNull(result);
    }

    @Test
    void testCanEqual() {
        boolean result = city.canEqual("other");
        Assertions.assertNotNull(result);
    }

    @Test
    void testHashCode() {
        int result = city.hashCode();
        Assertions.assertNotNull(result);
    }

    @Test
    void testToString() {
        String result = city.toString();
        Assertions.assertEquals("City(id=0, name=name, coefficient=0.0, population=0)", result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme