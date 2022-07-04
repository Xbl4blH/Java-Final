package com.example.finaljava.models;

import lombok.Data;

@Data
public class City {
    private int id;
    private String name;
    private double coefficient;
    private int population;

    public City(int id, String name, double coefficient, int population) {
        this.id = id;
        this.name = name;
        this.coefficient = coefficient;
        this.population = population;
    }

    public City(){}
}
