package com.example.finaljava.services;

import com.example.finaljava.models.StationManager;
import com.example.finaljava.repository.interfaces.StationManagerRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StationManagerService {
    StationManagerRepositoryInterface stationManagerRepositoryInterface;

    @Autowired
    public StationManagerService(StationManagerRepositoryInterface stationManagerRepositoryInterface) {
        this.stationManagerRepositoryInterface = stationManagerRepositoryInterface;
    }

    public List<StationManager> getAll(){
        try {
            return stationManagerRepositoryInterface.getAll();
        } catch (Exception e){
            return null;
        }
    }
    public StationManager findById(int id) {
        try {
            return stationManagerRepositoryInterface.findById(id);
        } catch (Exception e){
            return null;
        }
    }
}
