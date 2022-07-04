package com.example.finaljava.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class StationManagerTest {
    StationManager stationManager = new StationManager(0, 0, 0);

    @Test
    void testSetId() {
        stationManager.setId(0);
    }

    @Test
    void testSetStationId() {
        stationManager.setStationId(0);
    }

    @Test
    void testSetManagerId() {
        stationManager.setManagerId(0);
    }

    @Test
    void testEquals() {
        boolean result = stationManager.equals("o");
        Assertions.assertNotNull(result);
    }

    @Test
    void testCanEqual() {
        boolean result = stationManager.canEqual("other");
        Assertions.assertNotNull(result);
    }

    @Test
    void testHashCode() {
        int result = stationManager.hashCode();
        Assertions.assertNotNull(result);
    }

    @Test
    void testToString() {
        String result = stationManager.toString();
        Assertions.assertEquals("StationManager(id=0, stationId=0, managerId=0)", result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme