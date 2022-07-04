package com.example.finaljava.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PaymentCardTest {
    PaymentCard paymentCard = new PaymentCard(0, 0, "number", 0, true);

    @Test
    void testSetId() {
        paymentCard.setId(0);
    }

    @Test
    void testSetCustomerId() {
        paymentCard.setCustomerId(0);
    }

    @Test
    void testSetNumber() {
        paymentCard.setNumber("number");
    }

    @Test
    void testSetCvv() {
        paymentCard.setCvv(0);
    }

    @Test
    void testSetValidThru() {
        paymentCard.setValidThru(true);
    }

    @Test
    void testEquals() {
        boolean result = paymentCard.equals("o");
        Assertions.assertNotNull(result);
    }

    @Test
    void testCanEqual() {
        boolean result = paymentCard.canEqual("other");
        Assertions.assertNotNull(result);
    }

    @Test
    void testHashCode() {
        int result = paymentCard.hashCode();
        Assertions.assertNotNull(result);
    }

    @Test
    void testToString() {
        String result = paymentCard.toString();
        Assertions.assertEquals("PaymentCard(id=0, customerId=0, number=number, cvv=0, validThru=true)", result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme