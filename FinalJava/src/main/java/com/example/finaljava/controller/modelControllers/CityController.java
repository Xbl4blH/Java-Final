package com.example.finaljava.controller.modelControllers;

import com.example.finaljava.models.City;
import com.example.finaljava.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/city")
public class CityController {
    CityService cityService;

    @Autowired
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping("/all")
    public List<City> getAllCity(){
        return cityService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<City> getCityById(@PathVariable("id") int id) {
        City city = cityService.findById(id);
        if (city != null) {
            return new ResponseEntity<>(city, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateCity(@PathVariable("id") int id, @RequestBody City city) {
        City city1= cityService.findById(id);
        if (city1 != null) {
            city1.setId(id);
            city1.setName(city.getName());
            city1.setCoefficient(city.getCoefficient());
            city1.setPopulation(city.getPopulation());
            cityService.update(city1);
            return new ResponseEntity<>("City was updated successfully.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Cannot find City with id=" + id, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.HEAD, value = "/head")
    public void getCityByIdHeader(@RequestHeader("header") String id, HttpServletResponse httpServletResponse){
        httpServletResponse.addHeader("Customer", cityService.findById(Integer.parseInt(id)).toString());
    }

    @RequestMapping(method = RequestMethod.OPTIONS, value = "/options")
    public ResponseEntity<?> cityOptions(){
        return ResponseEntity.ok().allow().build();
    }
}
