package com.example.finaljava.controller.modelControllers;

import com.example.finaljava.JMS.JavaMessagingService;
import com.example.finaljava.models.Customer;
import com.example.finaljava.services.CustomerService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Log
@RestController
@RequestMapping("/customer")
public class CustomController {
    CustomerService customerService;
    JavaMessagingService javaMessagingService;
    CacheManager cacheManager;

    @Resource(name = "cacheManager")
    public void setCacheManager(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    @Autowired
    public void setJavaMessagingService(JavaMessagingService javaMessagingService) {
        this.javaMessagingService = javaMessagingService;
    }

    @Autowired
    public CustomController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/all")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<Customer> getAllCustomer(@RequestHeader Map<String, String> headers){
        headers.forEach((key, value) -> {
            log.info(String.format("Get all customer: '%s' = %s", key, value));
        });
        return customerService.getAll();
    }

    @GetMapping("/all/paging")
    public  ResponseEntity<?> getAllCustomerPaging(@RequestHeader Map<String, String> headers,
                                                   @RequestParam(defaultValue = "0") Integer pageNo,
                                                   @RequestParam(defaultValue = "10") Integer pageSize,
                                                   @RequestParam(defaultValue = "customer_id") String sortBy){
        try {
            headers.forEach((key, value) -> {
                log.info(String.format("Header '%s' = %s", key, value));
            });
            return ResponseEntity.ok(customerService.getAllPagination(pageNo, pageSize, sortBy));
        }catch (Exception ex){
            ex.printStackTrace();
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Error", ex);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable("id") int id) {
        Customer customer = customerService.findById(id);
        if (customer != null) {
            return new ResponseEntity<>(customer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.HEAD, value = "/head")
    public void getCustomerByIdHeader(@RequestHeader("header") String id, HttpServletResponse httpServletResponse){
        httpServletResponse.addHeader("Customer", customerService.findById(Integer.parseInt(id)).toString());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable("id") int id) {
        try {
            int result = customerService.deleteById(id);
            if (result == 0) {
                return new ResponseEntity<>("Cannot find customer with id=" + id, HttpStatus.OK);
            }
            return new ResponseEntity<>("Customer was deleted successfully.", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Cannot delete customer.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<String> createCustomer(@RequestBody Customer customer) {
        try {
            customerService.save(customer);
            return new ResponseEntity<>("Customer was created successfully.", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateCustomer(@PathVariable("id") int id, @RequestBody Customer customer) {
        Customer customer1= customerService.findById(id);
        if (customer1 != null) {
            customer1.setId(id);
            customer1.setStationId(customer.getStationId());
            customer1.setCityId(customer.getCityId());
            customer1.setFirstName(customer.getFirstName());
            customer1.setLastName(customer.getLastName());
            customer1.setConsumption(customer.getConsumption());
            customerService.update(customer1);
            return new ResponseEntity<>("Customer was updated successfully.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Cannot find Customer with id=" + id, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.OPTIONS, value = "/options")
    public ResponseEntity<?> customerOptions(){
        return ResponseEntity.ok().allow(HttpMethod.DELETE,HttpMethod.GET,HttpMethod.HEAD,HttpMethod.POST,HttpMethod.PUT).build();
    }

    @GetMapping("/send/{id}")
    public ResponseEntity<?> sendCustomerMessage(@PathVariable(value = "id") int id){
        try {
            javaMessagingService.sendJmsMessageCustomer(customerService.findById(id));
            return ResponseEntity.ok("Success!!!");
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Fail to send jms");
        }
    }

    @GetMapping("/receive/{id}")
    public ResponseEntity<?> showMessageCustomer(@PathVariable(value = "id") int id){
        try {
            return ResponseEntity.ok(cacheManager.getCache("customers").get(id).get());
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Fail to get jms");
        }
    }
}
