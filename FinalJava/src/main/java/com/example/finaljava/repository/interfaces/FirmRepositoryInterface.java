package com.example.finaljava.repository.interfaces;

import com.example.finaljava.models.Firm;

import java.util.List;

public interface FirmRepositoryInterface {
    List<Firm> getAll();
    Firm findById(int id);
    int[] batchUpdate(List<Firm> firms);
    int save(Firm firm);
}
