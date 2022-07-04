package com.example.finaljava.services;

import com.example.finaljava.AOP.TimerAOP;
import com.example.finaljava.models.City;
import com.example.finaljava.models.Customer;
import com.example.finaljava.repository.interfaces.CustomerRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    CustomerRepositoryInterface customerRepositoryInterface;
    CityService cityService;

    @Autowired
    public void setCityService(CityService cityService) {
        this.cityService = cityService;
    }

    private CacheManager cacheManager;

    @Resource(name = "cacheManager")
    public void setCacheManager(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }


    @Autowired
    public CustomerService(CustomerRepositoryInterface customerRepositoryInterface) {
        this.customerRepositoryInterface = customerRepositoryInterface;
    }

    public List<Customer> getAllPagination( Integer pageNo, Integer pageSize, String sortBy){
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.Direction.fromString("ASC"), sortBy);
        Page<Customer> pagedResult = customerRepositoryInterface.getAllPaging(paging);
        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<Customer>();
        }
    }

    @TimerAOP
    public List<Customer> getAll(){
        cacheManager.getCache("lastExecutedMethod").put("CustomerService","getAll");
        try {
            return customerRepositoryInterface.getAll().stream().sorted(Comparator.comparingInt(Customer::getId)).collect(Collectors.toList());
        } catch (Exception e){
            return null;
        }
    }
    public Customer findById(int id){
        cacheManager.getCache("lastExecutedMethod").put("CustomerService","findById");
        try {
            return customerRepositoryInterface.findById(id);
        } catch (Exception e){
            return null;
        }
    }
    public int deleteById(int id){
        cacheManager.getCache("lastExecutedMethod").put("CustomerService","deleteById");
        try {
            if (id<0)
                return 0;
            return customerRepositoryInterface.deleteById(id);
        } catch (Exception e){
            return 0;
        }
    }
    public int save(Customer customer){
        cacheManager.getCache("lastExecutedMethod").put("CustomerService","save");
        try{
            City city = cityService.findById(customer.getCityId());
            city.setPopulation(city.getPopulation()+1);
            cityService.update(city);
            return customerRepositoryInterface.save(customer);

        } catch (Exception e){
            return 0;
        }
    }
    @TimerAOP
    public int update(Customer customer){
        cacheManager.getCache("lastExecutedMethod").put("CustomerService","update");
        try {
            if (findById(customer.getId())!=null)
                return customerRepositoryInterface.update(customer);
            return 0;
        } catch (Exception e){
            return 0;
        }
    }
}
