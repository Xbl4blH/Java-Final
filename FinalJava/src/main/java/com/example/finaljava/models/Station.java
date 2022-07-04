package com.example.finaljava.models;

import lombok.Data;

@Data
public class Station {
    private int id;
    private int firmId;
    private int stationTypeId;
    private int maxPerformance;
    private boolean isActive;

    public Station(int id, int firmId, int stationTypeId, int maxPerformance, boolean isActive) {
        this.id = id;
        this.firmId = firmId;
        this.stationTypeId = stationTypeId;
        this.maxPerformance = maxPerformance;
        this.isActive = isActive;
    }

    public Station(){}
}
