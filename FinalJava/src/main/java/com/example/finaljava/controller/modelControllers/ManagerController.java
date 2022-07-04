package com.example.finaljava.controller.modelControllers;

import com.example.finaljava.JMS.JavaMessagingService;
import com.example.finaljava.models.Customer;
import com.example.finaljava.models.Manager;
import com.example.finaljava.services.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/manager")
public class ManagerController {
    ManagerService managerService;
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
    public ManagerController(ManagerService managerService) {
        this.managerService = managerService;
    }

    @GetMapping("/all")
    public List<Manager> getAllManager(){
        return managerService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Manager> getManagerById(@PathVariable("id") int id) {
        Manager manager = managerService.findById(id);
        if (manager != null) {
            return new ResponseEntity<>(manager, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteManager(@PathVariable("id") int id) {
        try {
            int result = managerService.deleteById(id);
            if (result == 0) {
                return new ResponseEntity<>("Cannot find manager with id=" + id, HttpStatus.OK);
            }
            return new ResponseEntity<>("Manager was deleted successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Cannot delete manager.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<String> createManager(@RequestBody Manager manager) {
        try {
            managerService.save(manager);
            return new ResponseEntity<>("Manager was created successfully.", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateManager(@PathVariable("id") int id, @RequestBody Manager manager) {
        Manager manager1 = managerService.findById(id);
        if (manager1 != null) {
            manager1.setId(id);
            manager1.setFirstName(manager.getFirstName());
            manager1.setLastName(manager.getLastName());
            managerService.update(manager1);
            return new ResponseEntity<>("Manager was updated successfully.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Cannot find Manager with id=" + id, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.OPTIONS, value = "/options")
    public ResponseEntity<?> managerOptions(){
        return ResponseEntity.ok().allow(HttpMethod.DELETE,HttpMethod.GET,HttpMethod.POST,HttpMethod.PUT).build();
    }

    @GetMapping("/send/{id}")
    public ResponseEntity<?> sendMangerMessage(@PathVariable(value = "id") int id){
        try {
            javaMessagingService.sendJmsMessageManager(managerService.findById(id));
            return ResponseEntity.ok("Success!!!");
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Fail to send jms");
        }
    }

    @GetMapping("/receive/{id}")
    public ResponseEntity<?> showMessageManager(@PathVariable(value = "id") int id){
        try {
            return ResponseEntity.ok(cacheManager.getCache("managers").get(id).get());
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Fail to get jms");
        }
    }
}
