package com.example.finaljava.AOP;

import lombok.extern.java.Log;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Date;

@Aspect
@Component
@Log
public class OtherLogger {
    private Date date = new Date();

    @Pointcut("within(com.example.finaljava.repository.CustomerRepository)")
    public void pointcut() {
    };

    @Before("pointcut()")
    public void starter(JoinPoint jp){
        log.info("Your "+jp.getSignature().getName()+" started, time: "+date.toString());
    }

    @After("pointcut()")
    public void ender(JoinPoint jp){
        log.info("Your "+jp.getSignature().getName()+" ended, time: "+date.toString());
    }

    @AfterReturning(pointcut = "execution(public * com.example.finaljava.services.CustomerService.findById(..))", returning = "result")
    public void returnTrigger(JoinPoint jp, Object result) {
        log.info("From "+jp.getSignature().getName()+" was returned "+result.toString());
    }
}
