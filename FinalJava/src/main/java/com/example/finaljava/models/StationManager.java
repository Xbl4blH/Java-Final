package com.example.finaljava.models;

import lombok.Data;

@Data
public class StationManager {
    private int id;
    private int stationId;
    private int managerId;

    public StationManager(int id, int stationId, int managerId) {
        this.id = id;
        this.stationId = stationId;
        this.managerId = managerId;
    }

    public StationManager(){}
}
