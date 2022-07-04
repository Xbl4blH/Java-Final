package com.example.finaljava.controller.modelControllers;

import com.example.finaljava.models.Customer;
import com.example.finaljava.models.Firm;
import com.example.finaljava.services.FirmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/firm")
public class FirmController {
    FirmService firmService;

    @Autowired
    public FirmController(FirmService firmService) {
        this.firmService = firmService;
    }

    @GetMapping("/all")
    public List<Firm> getAllFirm(){
        return firmService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Firm> getFirmById(@PathVariable("id") int id) {
        Firm firm = firmService.findById(id);
        if (firm != null) {
            return new ResponseEntity<>(firm, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.HEAD, value = "/head")
    public void getFirmByIdHeader(@RequestHeader("header") String id, HttpServletResponse httpServletResponse){
        httpServletResponse.addHeader("Customer", firmService.findById(Integer.parseInt(id)).toString());
    }

    @RequestMapping(method = RequestMethod.OPTIONS, value = "/options")
    public ResponseEntity<?> firmOptions(){
        return ResponseEntity.ok().allow(HttpMethod.GET,HttpMethod.HEAD).build();
    }

    @PutMapping("/batchupdate")
    public ResponseEntity<String> updateFirm(@RequestBody List<Firm> firms) {
        List<Firm> firmRes = new ArrayList<>();
        for (Firm f:firms) {
            Firm firmExist = firmService.findById(f.getId());
            if (firmExist !=null){
                firmExist.setId(f.getId());
                firmExist.setName(f.getName());
                firmExist.setWorkers(f.getWorkers());
                firmRes.add(firmExist);
            }
        }
        firmService.batchUpdate(firmRes);
        return ResponseEntity.ok("BatchComplete!");
    }

    @PostMapping("/add/converter")
    public ResponseEntity<String> addByConverter(@RequestBody String s){
        try {
            firmService.save(firmService.addByConverter(s));
            return ResponseEntity.ok("Converted succesfull");
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.ok("Error!");
        }
    }
}
