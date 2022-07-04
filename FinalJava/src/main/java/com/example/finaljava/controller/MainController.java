//package com.example.finaljava.controller;
//
//import com.example.finaljava.models.Check;
//import com.example.finaljava.models.Customer;
//import com.example.finaljava.services.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//public class MainController {
//    CheckService checksService;
//    CityService cityService;
//    CustomerService customerService;
//    FirmService firmService;
//    ManagerService managerService;
//    PaymentCardService paymentCardService;
//    StationManagerService stationManagerService;
//    StationService stationService;
//    StationTypeService stationTypeService;
//    TelephoneNumberService telephoneNumberService;
//
//    @Autowired
//    public MainController(CheckService checksService, CustomerService customerService, FirmService firmService,
//                          ManagerService managerService, PaymentCardService paymentCardService, StationManagerService stationManagerService,
//                          StationService stationService, StationTypeService stationTypeService, TelephoneNumberService telephoneNumberService,
//                          CityService cityService) {
//        this.checksService = checksService;
//        this.cityService = cityService;
//        this.customerService = customerService;
//        this.firmService = firmService;
//        this.managerService = managerService;
//        this.paymentCardService = paymentCardService;
//        this.stationManagerService = stationManagerService;
//        this.stationService = stationService;
//        this.stationTypeService = stationTypeService;
//        this.telephoneNumberService = telephoneNumberService;
//    }
//
//    @GetMapping("/checks/getall")
//    public List<Check> getAllChecks(){
//        return checksService.getAll();
//    }
//
//    @GetMapping("/customer/getall")
//    public List<Customer> getAllCustomer(){
//        return customerService.getAll();
//    }
//
//    @GetMapping("/customer/getall")
//    public List<Customer> getAllCustomer(){
//        return customerService.getAll();
//    }
//
//    @GetMapping("/customer/getall")
//    public List<Customer> getAllCustomer(){
//        return customerService.getAll();
//    }
//
//    @GetMapping("/customer/getall")
//    public List<Customer> getAllCustomer(){
//        return customerService.getAll();
//    }
//}
