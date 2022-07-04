package com.example.finaljava.controller.modelControllers;

import com.example.finaljava.models.Check;
import com.example.finaljava.services.CheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/check")
public class CheckController {
    CheckService checkService;

    @Autowired
    public CheckController(CheckService checkService) {
        this.checkService = checkService;
    }

    @GetMapping("/all")
    public List<Check> getAllCheck(){
        return checkService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Check> getCheckById(@PathVariable("id") int id) {
        Check check = checkService.findById(id);
        if (check != null) {
            return new ResponseEntity<>(check, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCheck(@PathVariable("id") int id) {
        try {
            int result = checkService.deleteById(id);
            if (result == 0) {
                return new ResponseEntity<>("Cannot find check with id=" + id, HttpStatus.OK);
            }
            return new ResponseEntity<>("Check was deleted successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Cannot delete check.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<String> createCheck(@RequestBody Check check) {
        try {
            checkService.save(check);
            return new ResponseEntity<>("Check was created successfully.", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(method = RequestMethod.OPTIONS, value = "/options")
    public ResponseEntity<?> checkOptions(){
        return ResponseEntity.ok().allow(HttpMethod.DELETE,HttpMethod.GET,HttpMethod.POST).build();
    }

    @GetMapping("/currentmonth")
    public ResponseEntity<List<Check>> currentMonth(){
        if (checkService.currentMonthCheck())
            return ResponseEntity.ok(checkService.getAll());
        else
            return (ResponseEntity<List<Check>>) ResponseEntity.internalServerError();
    }
}
