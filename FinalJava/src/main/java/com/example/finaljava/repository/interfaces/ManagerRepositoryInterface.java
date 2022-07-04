package com.example.finaljava.repository.interfaces;

import com.example.finaljava.models.Manager;

import java.util.List;

public interface ManagerRepositoryInterface {
    List<Manager> getAll();
    Manager findById(int id);
    int deleteById(int id);
    int save(Manager manager);
    int update(Manager manager);
}
