package com.example.finaljava.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class StationTypeTest {
    StationType stationType = new StationType(0, "name", 0);

    @Test
    void testSetId() {
        stationType.setId(0);
    }

    @Test
    void testSetName() {
        stationType.setName("name");
    }

    @Test
    void testSetPrice() {
        stationType.setPrice(0);
    }

    @Test
    void testEquals() {
        boolean result = stationType.equals("o");
        Assertions.assertNotNull(result);
    }

    @Test
    void testCanEqual() {
        boolean result = stationType.canEqual("other");
        Assertions.assertNotNull(result);
    }

    @Test
    void testHashCode() {
        int result = stationType.hashCode();
        Assertions.assertNotNull(result);
    }

    @Test
    void testToString() {
        String result = stationType.toString();
        Assertions.assertEquals("StationType(id=0, name=name, price=0)", result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme