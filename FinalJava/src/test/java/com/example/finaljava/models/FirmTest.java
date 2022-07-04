package com.example.finaljava.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FirmTest {
    Firm firm = new Firm(0, "name", 0);

    @Test
    void testMyInit() {
        firm.myInit();
    }

    @Test
    void testMyDestroy() {
        firm.myDestroy();
    }

    @Test
    void testSetId() {
        firm.setId(0);
    }

    @Test
    void testSetName() {
        firm.setName("name");
    }

    @Test
    void testSetWorkers() {
        firm.setWorkers(0);
    }

    @Test
    void testEquals() {
        boolean result = firm.equals("o");
        Assertions.assertNotNull(result);
    }

    @Test
    void testCanEqual() {
        boolean result = firm.canEqual("other");
        Assertions.assertNotNull(result);
    }

    @Test
    void testHashCode() {
        int result = firm.hashCode();
        Assertions.assertNotNull(result);
    }

    @Test
    void testToString() {
        String result = firm.toString();
        Assertions.assertEquals("Firm(id=0, name=name, workers=0)", result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme