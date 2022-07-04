package com.example.finaljava.services;

import com.example.finaljava.models.City;
import com.example.finaljava.models.Manager;
import com.example.finaljava.models.Station;
import com.example.finaljava.repository.interfaces.StationRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class StationService {
    StationRepositoryInterface stationRepositoryInterface;
    CityService cityService;

    @Autowired
    public StationService(StationRepositoryInterface stationRepositoryInterface, CityService cityService) {
        this.cityService = cityService;
        this.stationRepositoryInterface = stationRepositoryInterface;
    }

    public List<Station> getAll(){
        try {
            return stationRepositoryInterface.getAll();
        } catch (Exception e){
            return null;
        }
    }
    public Station findById(int id){return stationRepositoryInterface.findById(id);}
    public int save(Station station){
        try{
            List<City> citys= cityService.getAll();
            for (City c: citys) {
                c.setCoefficient(c.getCoefficient()-0.1);
                cityService.update(c);
            }
            return stationRepositoryInterface.save(station);
        }catch(Exception e){
            return 0;
        }
    }

    public Map<Station, Manager> getManagersWithStations(){
        try {
            return stationRepositoryInterface.getAllManagersWithStations();
        } catch (Exception e){
            return null;
        }
    }
}
