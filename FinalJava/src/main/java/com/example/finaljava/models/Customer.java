package com.example.finaljava.models;

import lombok.Data;
import lombok.extern.java.Log;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Log
@Data
public class Customer {
    private int id;
    private int stationId;
    private int cityId;
    private String firstName;
    private String lastName;
    private int consumption;

    public Customer(int id, int stationId, int cityId, String firstName, String lastName, int consumption) {
        this.id = id;
        this.stationId = stationId;
        this.cityId = cityId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.consumption = consumption;
    }

    public Customer(){}


    @PostConstruct
    public void myInit() {
        log.info("Initializing bean: " + Customer.class);
    }

    @PreDestroy
    public void myDestroy() {
        log.info("Destroying Bean: " + Customer.class);
    }
}
