package com.example.finaljava;

import com.example.finaljava.config.ApplicationConfig;
import com.example.finaljava.config.JmsConfig;
import com.example.finaljava.repository.*;
import com.example.finaljava.repository.interfaces.CheckRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;

@SpringBootApplication

@Import({/*PlayerConfig.class, ApplicationConfig2.class, */ApplicationConfig.class, JmsConfig.class})
public class FinalJavaApplication {
    private static ApplicationContext applicationContext;

    public static void main(String[] args) {
        applicationContext =  SpringApplication.run(FinalJavaApplication.class, args);
    }

}
