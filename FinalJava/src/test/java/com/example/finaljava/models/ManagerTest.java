package com.example.finaljava.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ManagerTest {
    Manager manager = new Manager(0, "firstName", "lastName");

    @Test
    void testSetId() {
        manager.setId(0);
    }

    @Test
    void testSetFirstName() {
        manager.setFirstName("firstName");
    }

    @Test
    void testSetLastName() {
        manager.setLastName("lastName");
    }

    @Test
    void testEquals() {
        boolean result = manager.equals("o");
        Assertions.assertNotNull(result);
    }

    @Test
    void testCanEqual() {
        boolean result = manager.canEqual("other");
        Assertions.assertNotNull(result);
    }

    @Test
    void testHashCode() {
        int result = manager.hashCode();
        Assertions.assertNotNull(result);
    }

    @Test
    void testToString() {
        String result = manager.toString();
        Assertions.assertEquals("Manager(id=0, firstName=firstName, lastName=lastName)", result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme