package com.example.finaljava.services;

import com.example.finaljava.CFV.FirmConverter;
import com.example.finaljava.models.Firm;
import com.example.finaljava.repository.interfaces.FirmRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FirmService {
    FirmRepositoryInterface firmRepositoryInterface;
    FirmConverter firmConverter;
    private CacheManager cacheManager;

    @Resource(name = "cacheManager")
    public void setCacheManager(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    @Autowired
    public FirmService(FirmRepositoryInterface firmRepositoryInterface, FirmConverter firmConverter) {
        this.firmRepositoryInterface = firmRepositoryInterface;
        this.firmConverter = firmConverter;
    }

    public List<Firm> getAll(){
        try {
            return firmRepositoryInterface.getAll().stream().sorted(Comparator.comparingInt(Firm::getId)).collect(Collectors.toList());
        } catch (Exception e){
            return null;
        }
    }
    public Firm findById(int id){
        try{
            return firmRepositoryInterface.findById(id);
        } catch (Exception e){
            return null;
        }
    }
    public int[] batchUpdate(List<Firm> firms){
        return firmRepositoryInterface.batchUpdate(firms);
    }
    public Firm addByConverter(String s){
        try {
            return firmConverter.convert(s);
        } catch (Exception e){
            return null;
        }
    }
    public int save(Firm firm){
        try {
            return firmRepositoryInterface.save(firm);
        } catch (Exception e){
            return 0;
        }
    }
}
