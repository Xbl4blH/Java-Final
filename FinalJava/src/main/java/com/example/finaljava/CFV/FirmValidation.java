package com.example.finaljava.CFV;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Constraint(validatedBy= FirmValidator.class)
@Documented
public @interface FirmValidation {
    String message() default "Firm validation fail!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

