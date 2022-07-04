package com.example.finaljava.repository.interfaces;

import com.example.finaljava.models.Manager;
import com.example.finaljava.models.Station;

import java.util.List;
import java.util.Map;

public interface StationRepositoryInterface {
    List<Station> getAll();
    Station findById(int id);
    int save(Station station);
    Map<Station, Manager> getAllManagersWithStations();
}
