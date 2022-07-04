package com.example.finaljava.models;

import lombok.Data;

@Data
public class Manager {
    private int id;
    private String firstName;
    private String lastName;

    public Manager(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Manager(){}
}
