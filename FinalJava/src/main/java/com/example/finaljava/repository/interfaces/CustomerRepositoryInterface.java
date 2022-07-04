package com.example.finaljava.repository.interfaces;

import com.example.finaljava.models.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomerRepositoryInterface {
    List<Customer> getAll();
    Page<Customer> getAllPaging(Pageable page);
    Customer findById(int id);
    int deleteById(int id);
    int save(Customer customer);
    int update(Customer customer);
}
