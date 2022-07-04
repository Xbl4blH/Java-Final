package com.example.finaljava.services;

import com.example.finaljava.models.TelephoneNumber;
import com.example.finaljava.repository.interfaces.TelephoneNumberRepositoryInterface;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TelephoneNumberService {
    TelephoneNumberRepositoryInterface telephoneNumberRepositoryInterface;

    @Autowired
    public TelephoneNumberService(TelephoneNumberRepositoryInterface telephoneNumberRepositoryInterface) {
        this.telephoneNumberRepositoryInterface = telephoneNumberRepositoryInterface;
    }

    public List<TelephoneNumber> getAll(){
        try {
        return telephoneNumberRepositoryInterface.getAll();
        } catch (Exception e){
            return Collections.emptyList();
        }
    }
    public TelephoneNumber findById(int id){
        try {
            return telephoneNumberRepositoryInterface.findById(id);
        } catch (Exception e){
            return null;
        }
    }
    public int deleteById(int id){
        try {
            return telephoneNumberRepositoryInterface.deleteById(id);
        } catch (Exception e){
            return 0;
        }
    }
    public int save(TelephoneNumber telephoneNumber){
        try {
            return telephoneNumberRepositoryInterface.save(telephoneNumber);
        } catch (Exception e){
            return 0;
        }
    }
    public int update(TelephoneNumber telephoneNumber){
        try {
            return telephoneNumberRepositoryInterface.update(telephoneNumber);
        } catch (Exception e){
            return 0;
        }
    }
}
