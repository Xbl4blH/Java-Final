package com.example.finaljava.models;

import lombok.Data;

@Data
public class StationType {
    private int id;
    private String name;
    private int price;

    public StationType(int id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public StationType(){}
}
