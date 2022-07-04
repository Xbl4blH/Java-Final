package com.example.finaljava.controller.modelControllers;

import com.example.finaljava.models.City;
import com.example.finaljava.models.Customer;
import com.example.finaljava.models.PaymentCard;
import com.example.finaljava.models.TelephoneNumber;
import com.example.finaljava.services.TelephoneNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/telephonenumber")
public class TelephoneNumberController {
    TelephoneNumberService telephoneNumberService;

    @Autowired
    public TelephoneNumberController(TelephoneNumberService telephoneNumberService) {
        this.telephoneNumberService = telephoneNumberService;
    }

    @GetMapping("/all")
    public List<TelephoneNumber> getAllTelephoneNumber(){
        return telephoneNumberService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TelephoneNumber> getTelephoneNumberById(@PathVariable("id") int id) {
        TelephoneNumber telephoneNumber = telephoneNumberService.findById(id);
        if (telephoneNumber != null) {
            return new ResponseEntity<>(telephoneNumber, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTelephoneNumber(@PathVariable("id") int id) {
        try {
            int result = telephoneNumberService.deleteById(id);
            if (result == 0) {
                return new ResponseEntity<>("Cannot find telephone number with id=" + id, HttpStatus.OK);
            }
            return new ResponseEntity<>("Telephone number was deleted successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Cannot delete telephone number.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<String> createTelephoneNumber(@RequestBody TelephoneNumber telephoneNumber) {
        try {
            telephoneNumberService.save(telephoneNumber);
            return new ResponseEntity<>("Telephone number was created successfully.", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateTelephoneNumber(@PathVariable("id") int id, @RequestBody TelephoneNumber telephoneNumber) {
        TelephoneNumber telephoneNumber1 = telephoneNumberService.findById(id);
        if (telephoneNumber1 != null) {
            telephoneNumber1.setId(id);
            telephoneNumber1.setManagerId(telephoneNumber.getManagerId());
            telephoneNumber1.setNumber(telephoneNumber.getNumber());
            telephoneNumber1.setOperator(telephoneNumber.getOperator());
            telephoneNumberService.update(telephoneNumber1);
            return new ResponseEntity<>("Telephone number was updated successfully.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Cannot find Telephone number with id=" + id, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.OPTIONS, value = "/options")
    public ResponseEntity<?> telephoneNumberOptions(){
        return ResponseEntity.ok().allow().build();
    }
}
