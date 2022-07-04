package com.example.finaljava.models;

import lombok.Data;

@Data
public class TelephoneNumber {
    private int id;
    private int managerId;
    private String number;
    private String operator;

    public TelephoneNumber(int id, int managerId, String number, String operator) {
        this.id = id;
        this.managerId = managerId;
        this.number = number;
        this.operator = operator;
    }

    public TelephoneNumber(){}
}
