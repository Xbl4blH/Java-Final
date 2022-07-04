package com.example.finaljava.services;

import com.example.finaljava.models.Manager;
import com.example.finaljava.repository.interfaces.ManagerRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManagerService {
    ManagerRepositoryInterface managerRepositoryInterface;

    @Autowired
    public ManagerService(ManagerRepositoryInterface managerRepositoryInterface) {
        this.managerRepositoryInterface = managerRepositoryInterface;
    }

    public List<Manager> getAll(){
        try {
            return managerRepositoryInterface.getAll();
        } catch (Exception e){
            return null;
        }
    }
    public Manager findById(int id){
        try {
            return managerRepositoryInterface.findById(id);
        } catch (Exception e){
            return null;
        }
    }
    public int deleteById(int id){
        try {
            return managerRepositoryInterface.deleteById(id);
        } catch (Exception e){
            return 0;
        }
    }
    public int save(Manager manager){
        try {
            return managerRepositoryInterface.save(manager);
        } catch (Exception e){
            return 0;
        }
    }
    public int update(Manager manager){
        try {
            return managerRepositoryInterface.update(manager);
        } catch (Exception e){
            return 0;
        }
    }
}
