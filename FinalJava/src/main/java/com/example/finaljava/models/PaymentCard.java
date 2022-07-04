package com.example.finaljava.models;

import lombok.Data;

import javax.validation.constraints.AssertTrue;

@Data
public class PaymentCard {
    private int id;
    private int customerId;
    private String number;
    private int cvv;
    private boolean validThru;

    public PaymentCard(int id, int customerId, String number, int cvv, boolean validThru) {
        this.id = id;
        this.customerId = customerId;
        this.number = number;
        this.cvv = cvv;
        this.validThru = validThru;
    }

    public PaymentCard(){}

    @AssertTrue(message = "PaymentCard is invalid")
    public boolean isValidThru() {
        return validThru;
    }
}
