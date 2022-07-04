package com.example.finaljava.services;

import com.example.finaljava.models.PaymentCard;
import com.example.finaljava.repository.interfaces.PaymentCardRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentCardService {
    PaymentCardRepositoryInterface paymentCardRepositoryInterface;

    @Autowired
    public PaymentCardService(PaymentCardRepositoryInterface paymentCardRepositoryInterface) {
        this.paymentCardRepositoryInterface = paymentCardRepositoryInterface;
    }

    public List<PaymentCard> getAll(){
        try {
            return paymentCardRepositoryInterface.getAll();
        } catch (Exception e){
            return null;
        }
    }
    public PaymentCard findById(int id) {
        try {
            return paymentCardRepositoryInterface.findById(id);
        } catch (Exception e){
            return null;
        }
    }
    public int update(PaymentCard paymentCard){
        try {
            return paymentCardRepositoryInterface.update(paymentCard);
        } catch (Exception e){
            return 0;
        }
    }
}
