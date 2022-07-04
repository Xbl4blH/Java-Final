package com.example.finaljava.config;

import com.example.finaljava.CFV.FormatterServiceBeanFactory;
import com.example.finaljava.models.Customer;
import com.example.finaljava.models.Firm;
import com.example.finaljava.services.CustomerService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.*;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.text.ParseException;
import java.util.Locale;

@Configuration
@Log
@EnableScheduling
@PropertySource("classpath:application.properties")
@EnableAsync
public class ApplicationConfig {
    CustomerService customerService;
    FormatterServiceBeanFactory formatterServiceBeanFactory;

    @Bean(initMethod = "myInit", destroyMethod = "myDestroy")
    @Scope("prototype")
    public Customer customer() {
        return new Customer();
    }

    @Bean(initMethod = "myInit", destroyMethod = "myDestroy")
    @DependsOn(value = "customer")
    @Lazy
    public Firm firm() {
        return new Firm(1,"test",1313);
    }


    @Autowired
    public void setFormatterServiceBeanFactory(FormatterServiceBeanFactory formatterServiceBeanFactory) {
        this.formatterServiceBeanFactory = formatterServiceBeanFactory;
    }

    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Scheduled(fixedDelayString = "${sceduledTaskTime}", initialDelay = 1000)
    public void scheduledCheckConnectionToDB(){
        if (customerService.getAll().size()>0){
            log.info("Connected To DB");
        }
        else{
            log.info("No connection to DB");
        }
    }

    @Scheduled(fixedRate = 5000)
    public void scheduleTaskRate() {
        log.info("Parallel task");
    }


    @Bean
    public void checkFormatter() throws ParseException {
        System.out.println("Custom Format:");
        // Custom Format
        System.out.println(formatterServiceBeanFactory.getDateTimeFormatter().parse("23-11-01", Locale.ENGLISH));
    }

}
