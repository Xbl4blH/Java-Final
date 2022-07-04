package com.example.finaljava.repository.interfaces;

import com.example.finaljava.models.StationType;

import java.util.List;

public interface StationTypeRepositoryInterface {
    List<StationType> getAll();
    StationType findById(int id);
}
