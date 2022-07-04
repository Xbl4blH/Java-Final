package com.example.finaljava.services;

import com.example.finaljava.models.Check;
import com.example.finaljava.models.Customer;
import com.example.finaljava.repository.CheckRepository;
import com.example.finaljava.repository.interfaces.CheckRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CheckService {
    private CheckRepositoryInterface checkRepositoryInterface;
    private CityService cityService;
    private StationTypeService stationTypeService;
    private CustomerService customerService;
    private StationService stationService;

    private CacheManager cacheManager;

    Logger log = LoggerFactory.getLogger(this.getClass());

    @Resource(name = "cacheManager")
    public void setCacheManager(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }


    @Autowired
    public CheckService(CheckRepositoryInterface checkRepositoryInterface, CityService cityService, StationTypeService stationTypeService, CustomerService customerService, StationService stationService) {
        this.checkRepositoryInterface = checkRepositoryInterface;
        this.cityService = cityService;
        this.stationTypeService = stationTypeService;
        this.customerService = customerService;
        this.stationService = stationService;
    }

    public List<Check> getAll(){
        cacheManager.getCache("lastExecutedMethod").put("CheckService","getAll");
        try {
            return checkRepositoryInterface.getAll().stream().sorted(Comparator.comparingInt(Check::getId)).collect(Collectors.toList());
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public Check findById(int id) {
        cacheManager.getCache("lastExecutedMethod").put("CheckService","findById");
        try {
            return checkRepositoryInterface.findById(id);
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    //проверить на всякий
    public int deleteById(int id) {
        cacheManager.getCache("lastExecutedMethod").put("CheckService","deleteById");
        try {
            return checkRepositoryInterface.deleteById(id);
        } catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    public int save(Check check){
        cacheManager.getCache("lastExecutedMethod").put("CheckService","save");
        try {
            return checkRepositoryInterface.save(check);
        } catch (Exception e){
            return 0;
        }
    }

    // check all month
    public boolean currentMonthCheck(){
        cacheManager.getCache("lastExecutedMethod").put("CheckService","currentMonthCheck");
        try {
        List<Customer> customers = customerService.getAll();
        for (Customer c : customers) {
            double price = 0;
            double coefficient = cityService.findById(c.getCityId()).getCoefficient();
            double priceElectricity = stationTypeService.findById(stationService.findById(c.getStationId()).getStationTypeId()).getPrice();
            price = coefficient*c.getConsumption()*priceElectricity;
            Date date = new Date();
            LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            int month = localDate.getMonthValue();
            checkRepositoryInterface.save(new Check(0,c.getId(),month,(int)price));
        }
        return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Scheduled(cron = "0 0 12 L * ?")
    public int[] sendMonthlyCheck(){
        currentMonthCheck();
        log.info("THE LAST DAY OF THE MONTH -> SENDING OUT CHECKS TO CUSTOMERS");
        return checkRepositoryInterface.sendMonthlyCheck();
    }
}
