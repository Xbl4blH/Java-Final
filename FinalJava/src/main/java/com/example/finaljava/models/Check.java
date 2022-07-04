package com.example.finaljava.models;

import lombok.Data;

@Data
public class Check {
    private int id;
    private int customerId;
    private int month;
    private int price;

    public Check() {
    }

    public Check(int id, int customerId, int month, int price) {
        this.id = id;
        this.customerId = customerId;
        this.month = month;
        this.price = price;
    }
}
