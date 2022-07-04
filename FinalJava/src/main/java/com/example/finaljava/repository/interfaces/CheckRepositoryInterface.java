package com.example.finaljava.repository.interfaces;

import com.example.finaljava.models.Check;

import java.util.List;

public interface CheckRepositoryInterface {
    List<Check> getAll();
    Check findById(int id);
    int deleteById(int id);
    int save(Check check);
    int[] sendMonthlyCheck();
}
