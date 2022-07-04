package com.example.finaljava.controller.modelControllers;

import com.example.finaljava.JMS.JavaMessagingService;
import com.example.finaljava.models.Customer;
import com.example.finaljava.models.Station;
import com.example.finaljava.services.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/station")
public class StationController {
    StationService stationService;
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
    public void setStationService(StationService stationService) {
        this.stationService = stationService;
    }

    @GetMapping("/all")
    public List<Station> getAllStation(){
        return stationService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Station> getStationById(@PathVariable("id") int id) {
        Station station = stationService.findById(id);
        if (station != null) {
            return new ResponseEntity<>(station, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<String> createStation(@RequestBody Station station) {
        try {
            stationService.save(station);
            return new ResponseEntity<>("Station was created successfully.", HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(method = RequestMethod.OPTIONS, value = "/options")
    public ResponseEntity<?> stationOptions(){
        return ResponseEntity.ok().allow().build();
    }

    @GetMapping("/send/{id}")
    public ResponseEntity<?> sendStationMessage(@PathVariable(value = "id") int id){
        try {
            javaMessagingService.sendJmsMessageStation(stationService.findById(id));
            return ResponseEntity.ok("Success!!!");
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Fail to send jms");
        }
    }

    @GetMapping("/receive/{id}")
    public ResponseEntity<?> showMessageStation(@PathVariable(value = "id") int id){
        try {
            return ResponseEntity.ok(cacheManager.getCache("stations").get(id).get());
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Fail to get jms");
        }
    }

    @GetMapping("/managersAndStations")
    public ResponseEntity<?> showManagersWithStations(){
        try {
            return ResponseEntity.ok(stationService.getManagersWithStations());
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
