package com.example.finaljava.controller.modelControllers;

import com.example.finaljava.models.StationType;
import com.example.finaljava.services.StationTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/stationtype")
public class StationTypeController {
    StationTypeService stationTypeService;

    @Autowired
    public StationTypeController(StationTypeService stationTypeService) {
        this.stationTypeService = stationTypeService;
    }

    @GetMapping("/all")
    public List<StationType> getAllStationType(){
        return stationTypeService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<StationType> getStationTypeById(@PathVariable("id") int id) {
        StationType stationType = stationTypeService.findById(id);
        if (stationType != null) {
            return new ResponseEntity<>(stationType, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.HEAD, value = "/head")
    public void getStationTypeByIdHeader(@RequestHeader("header") String id, HttpServletResponse httpServletResponse){
        httpServletResponse.addHeader("Customer", stationTypeService.findById(Integer.parseInt(id)).toString());
    }

    @RequestMapping(method = RequestMethod.OPTIONS, value = "/options")
    public ResponseEntity<?> stationTypeOptions(){
        return ResponseEntity.ok().allow().build();
    }
}
