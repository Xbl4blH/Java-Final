package com.example.finaljava.repository.interfaces;

import com.example.finaljava.models.StationManager;

import java.util.List;

public interface StationManagerRepositoryInterface {
    List<StationManager> getAll();
    StationManager findById(int id);
}
