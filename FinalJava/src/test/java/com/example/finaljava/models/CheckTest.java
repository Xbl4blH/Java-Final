package com.example.finaljava.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CheckTest {
    Check check = new Check(0, 0, 0, 0);

    @Test
    void testSetId() {
        check.setId(0);
    }

    @Test
    void testSetCustomerId() {
        check.setCustomerId(0);
    }

    @Test
    void testSetMonth() {
        check.setMonth(0);
    }

    @Test
    void testSetPrice() {
        check.setPrice(0);
    }

    @Test
    void testEquals() {
        boolean result = check.equals("o");
        Assertions.assertNotNull(result);
    }

    @Test
    void testCanEqual() {
        boolean result = check.canEqual("other");
        Assertions.assertNotNull(result);
    }

    @Test
    void testHashCode() {
        int result = check.hashCode();
        Assertions.assertNotNull(result);
    }

    @Test
    void testToString() {
        String result = check.toString();
        Assertions.assertEquals("Check(id=0, customerId=0, month=0, price=0)", result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme