package com.example.finaljava.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CustomerTest {
    Customer customer = new Customer(0, 0, 0, "firstName", "lastName", 0);

    @Test
    void testSetId() {
        customer.setId(0);
    }

    @Test
    void testSetStationId() {
        customer.setStationId(0);
    }

    @Test
    void testSetCityId() {
        customer.setCityId(0);
    }

    @Test
    void testSetFirstName() {
        customer.setFirstName("firstName");
    }

    @Test
    void testSetLastName() {
        customer.setLastName("lastName");
    }

    @Test
    void testSetConsumption() {
        customer.setConsumption(0);
    }

    @Test
    void testEquals() {
        boolean result = customer.equals("o");
        Assertions.assertNotNull(result);
    }

    @Test
    void testCanEqual() {
        boolean result = customer.canEqual("other");
        Assertions.assertNotNull(result);
    }

    @Test
    void testHashCode() {
        int result = customer.hashCode();
        Assertions.assertNotNull(result);
    }

    @Test
    void testToString() {
        String result = customer.toString();
        Assertions.assertEquals("Customer(id=0, stationId=0, cityId=0, firstName=firstName, lastName=lastName, consumption=0)", result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme