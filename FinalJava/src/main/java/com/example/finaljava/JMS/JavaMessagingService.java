package com.example.finaljava.JMS;

import com.example.finaljava.models.Customer;
import com.example.finaljava.models.Manager;
import com.example.finaljava.models.Station;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class JavaMessagingService {
    private JmsTemplate jmsTemplate;

    private CacheManager cacheManager;

    @Resource(name = "cacheManager")
    public void setCacheManager(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    @Autowired
    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void sendJmsMessageCustomer(Customer msg) {
        jmsTemplate.convertAndSend("customer", msg);
    }

    public void sendJmsMessageStation(Station msg) {
        jmsTemplate.convertAndSend("station", msg);
    }

    public void sendJmsMessageManager(Manager msg) {
        jmsTemplate.convertAndSend("manager", msg);
    }

    @JmsListener(destination = "customer", containerFactory = "myFactory")
    public void receiveJmsMessageCustomer(Customer jmsMessage) {
        cacheManager.getCache("customers").put(jmsMessage.getId(),jmsMessage);
    }

    @JmsListener(destination = "station", containerFactory = "myFactory")
    public void receiveJmsMessageStation(Station jmsMessage) {
        cacheManager.getCache("stations").put(jmsMessage.getId(),jmsMessage);
    }

    @JmsListener(destination = "manager", containerFactory = "myFactory")
    public void receiveJmsMessageManager(Manager jmsMessage) {
        cacheManager.getCache("managers").put(jmsMessage.getId(),jmsMessage);
    }
}
