package com.example.finaljava.repository.interfaces;

import com.example.finaljava.models.TelephoneNumber;

import java.util.List;

public interface TelephoneNumberRepositoryInterface {
    List<TelephoneNumber> getAll();
    TelephoneNumber findById(int id);
    int deleteById(int id);
    int save(TelephoneNumber telephoneNumber);
    int update(TelephoneNumber telephoneNumber);
}
