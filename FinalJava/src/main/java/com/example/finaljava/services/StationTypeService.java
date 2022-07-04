package com.example.finaljava.services;

import com.example.finaljava.models.StationType;
import com.example.finaljava.repository.interfaces.StationTypeRepositoryInterface;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StationTypeService {
    StationTypeRepositoryInterface stationTypeRepositoryInterface;

    @Autowired
    public StationTypeService(StationTypeRepositoryInterface stationTypeRepositoryInterface) {
        this.stationTypeRepositoryInterface = stationTypeRepositoryInterface;
    }

    public List<StationType> getAll(){
        try {
            return stationTypeRepositoryInterface.getAll();
        } catch (Exception e){
            return Collections.emptyList();
        }
    }
    public StationType findById(int id) {
        try {
            return stationTypeRepositoryInterface.findById(id);
        } catch (Exception e){
            return null;
        }
    }
}
