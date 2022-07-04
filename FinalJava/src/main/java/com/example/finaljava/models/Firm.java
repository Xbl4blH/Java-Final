package com.example.finaljava.models;

import com.example.finaljava.CFV.FirmValidation;
import lombok.Data;
import lombok.extern.java.Log;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Data
@Log
@FirmValidation
public class Firm {
    private int id;
    private String name;
    private int workers;

    public Firm(int id, String name, int workers) {
        this.id = id;
        this.name = name;
        this.workers = workers;
    }

    public Firm(){}


    @PostConstruct
    public void myInit() {
        log.info("Initializing bean: " + Firm.class);
    }

    @PreDestroy
    public void myDestroy() {
        log.info("Destroying Bean: " + Firm.class);
    }
}
