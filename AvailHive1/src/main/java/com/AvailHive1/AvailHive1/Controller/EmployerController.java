package com.AvailHive1.AvailHive1.Controller;

import com.AvailHive1.AvailHive1.entity.Employer;
import com.AvailHive1.AvailHive1.services.Employer.EmployerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employer")
public class EmployerController {

    @Autowired
    private EmployerService employerService;

    @GetMapping
    public List<Employer> getAllEmployers() {
        return employerService.getAllEmployers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employer> getEmployerById(@PathVariable Long id){
        return employerService.getEmployerById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());

    }

    @PostMapping("/add")
    public Employer createEmployer(@RequestBody Employer employer){
        return employerService.saveEmployer(employer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employer> updateEmployer(@PathVariable Long id, @RequestBody Employer employerDetails) {
        return ResponseEntity.ok(employerService.updateEmployer(id, employerDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployer(@PathVariable Long id) {
        employerService.deleteEmployer(id);
        return ResponseEntity.noContent().build();
    }


}
