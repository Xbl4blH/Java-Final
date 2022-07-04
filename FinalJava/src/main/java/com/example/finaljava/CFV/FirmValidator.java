package com.example.finaljava.CFV;

import com.example.finaljava.models.Firm;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraints.AssertTrue;

public class FirmValidator implements ConstraintValidator<FirmValidation, Firm> {
    @Override
    public void initialize(FirmValidation constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @AssertTrue(message = "Firm is invalid")
    @Override
    public boolean isValid(Firm firm, ConstraintValidatorContext constraintValidatorContext) {
        if (firm.getWorkers()<0 || firm.getName()==null || firm.getName().equals(""))
            return  false;
        return true;
    }
}