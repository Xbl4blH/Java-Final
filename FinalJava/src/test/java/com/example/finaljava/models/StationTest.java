package com.example.finaljava.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class StationTest {
    Station station = new Station(0, 0, 0, 0, true);

    @Test
    void testSetId() {
        station.setId(0);
    }

    @Test
    void testSetFirmId() {
        station.setFirmId(0);
    }

    @Test
    void testSetStationTypeId() {
        station.setStationTypeId(0);
    }

    @Test
    void testSetMaxPerformance() {
        station.setMaxPerformance(0);
    }

    @Test
    void testSetActive() {
        station.setActive(true);
    }

    @Test
    void testEquals() {
        boolean result = station.equals("o");
        Assertions.assertNotNull(result);
    }

    @Test
    void testCanEqual() {
        boolean result = station.canEqual("other");
        Assertions.assertNotNull(result);
    }

    @Test
    void testHashCode() {
        int result = station.hashCode();
        Assertions.assertNotNull(result);
    }

    @Test
    void testToString() {
        String result = station.toString();
        Assertions.assertEquals("Station(id=0, firmId=0, stationTypeId=0, maxPerformance=0, isActive=true)", result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme