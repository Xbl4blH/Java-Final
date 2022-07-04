package com.example.finaljava.repository.interfaces;

import com.example.finaljava.models.PaymentCard;

import java.util.List;

public interface PaymentCardRepositoryInterface {
    List<PaymentCard> getAll();
    PaymentCard findById(int id);
    int update(PaymentCard paymentCard);
}
