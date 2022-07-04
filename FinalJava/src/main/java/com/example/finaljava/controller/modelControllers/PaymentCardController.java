package com.example.finaljava.controller.modelControllers;

import com.example.finaljava.models.Manager;
import com.example.finaljava.models.PaymentCard;
import com.example.finaljava.services.PaymentCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/paymentcard")
public class PaymentCardController {
    PaymentCardService paymentCardService;

    @Autowired
    public PaymentCardController(PaymentCardService paymentCardService) {
        this.paymentCardService = paymentCardService;
    }

    @GetMapping("/all")
    public List<PaymentCard> getAllPaymentCard(){
        return paymentCardService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentCard> getPaymentCardById(@PathVariable("id") int id) {
        PaymentCard paymentCard = paymentCardService.findById(id);
        if (paymentCard != null) {
            return new ResponseEntity<>(paymentCard, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updatePaymentCard(@PathVariable("id") int id, @RequestBody PaymentCard paymentCard) {
        PaymentCard paymentCard1 = paymentCardService.findById(id);
        if (paymentCard1 != null) {
            paymentCard1.setId(id);
            paymentCard1.setCustomerId(paymentCard.getCustomerId());
            paymentCard1.setNumber(paymentCard.getNumber());
            paymentCard1.setCvv(paymentCard.getCvv());
            paymentCard1.setValidThru(paymentCard.isValidThru());
            paymentCardService.update(paymentCard1);
            return new ResponseEntity<>("Payment card was updated successfully.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Cannot find Payment card with id=" + id, HttpStatus.NOT_FOUND);
        }
    }
    @RequestMapping(method = RequestMethod.OPTIONS, value = "/options")
    public ResponseEntity<?> paymentCardOptions(){
        return ResponseEntity.ok().allow(HttpMethod.DELETE,HttpMethod.GET,HttpMethod.HEAD,HttpMethod.PUT).build();
    }
}
