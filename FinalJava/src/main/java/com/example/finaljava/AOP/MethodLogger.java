package com.example.finaljava.AOP;

import lombok.extern.java.Log;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Log
public class MethodLogger {
    @Around("@annotation(TimerAOP)")
    public Object logExecutionTime(ProceedingJoinPoint jp) throws Throwable {
        long start = System.currentTimeMillis();

        Object proceed = jp.proceed();
        long executionTime = System.currentTimeMillis() - start;

        log.info(jp.getSignature()+" done in "+executionTime+"ms");
        return proceed;
    }

    @AfterThrowing(pointcut = "@annotation(com.example.finaljava.AOP.TimerAOP)", throwing = "ex")
    public void logException(JoinPoint joinPoint, Exception ex){
        log.info("Exception in "+joinPoint.getSignature()+", "+ex.getMessage());
    }
}
