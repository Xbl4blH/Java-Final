package com.example.finaljava.services;

import com.example.finaljava.models.City;
import com.example.finaljava.repository.interfaces.CityRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CityService {
    CityRepositoryInterface cityRepositoryInterface;
    private CacheManager cacheManager;

    @Resource(name = "cacheManager")
    public void setCacheManager(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    @Autowired
    public CityService(CityRepositoryInterface cityRepositoryInterface) {
        this.cityRepositoryInterface = cityRepositoryInterface;
    }

    public List<City> getAll(){
        cacheManager.getCache("lastExecutedMethod").put("CityService","getAll");
        try {
            return cityRepositoryInterface.getAll();
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public City findById(int id){
        try {
            return cityRepositoryInterface.findById(id);
        } catch (Exception e){
            return null;
        }
    }
    public int update(City city){
        try {
            return cityRepositoryInterface.update(city);
        } catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }
}
