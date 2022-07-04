package com.example.finaljava.repository.interfaces;

import com.example.finaljava.models.City;

import java.util.List;

public interface CityRepositoryInterface {
    List<City> getAll();
    City findById(int id);
    int update(City city);
}
