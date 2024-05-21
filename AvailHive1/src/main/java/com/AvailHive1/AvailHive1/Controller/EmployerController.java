package com.AvailHive1.AvailHive1.Controller;

import com.AvailHive1.AvailHive1.dto.AdDTO;
import com.AvailHive1.AvailHive1.dto.EmployerDTO;
import com.AvailHive1.AvailHive1.entity.Employer;
import com.AvailHive1.AvailHive1.services.Employer.EmployerService;
import io.jsonwebtoken.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/company")
public class EmployerController {

    @Autowired
    private EmployerService employerService;

    @PostMapping("/employer/{userId}")
    public ResponseEntity<?> postEmployer(@PathVariable Long userId, @ModelAttribute EmployerDTO employerDTO) throws IOException, java.io.IOException {
        // Check for duplicate email or phone
        if (employerService.existsByEmail(employerDTO.getEmail()) || employerService.existsByPhone(employerDTO.getPhone())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Employee with this email or phone number already exists!");
        }

        boolean success = employerService.postEmployer(userId, employerDTO);
        if (success) {
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    @GetMapping("/employers/{userId}")
    public ResponseEntity<?> getAllEmployersByUserId(@PathVariable Long userId) {
        try {
            List<EmployerDTO> ads = employerService.getAllEmployers(userId);
            return ResponseEntity.ok(ads);
        } catch (Exception ex) {
            // Log the exception or handle it as needed
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while fetching employers");
        }
    }

    @GetMapping("/employers")
    public ResponseEntity<?> getAllEmployers() {
        try {
            List<EmployerDTO> ads = employerService.getEmployers();
            return ResponseEntity.ok(ads);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while fetching employers");
        }
    }


    @GetMapping("/empprofile/{employerId}")
    public ResponseEntity<?> getEmployerById(@PathVariable Long employerId){
        EmployerDTO employerDTO = employerService.getEmployerById(employerId);
        if(employerDTO != null){
            return ResponseEntity.ok(employerDTO);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/employer/{employerId}")
    public ResponseEntity<?> updateEmployer(@PathVariable Long employerId, @ModelAttribute EmployerDTO employerDTO) throws java.io.IOException {
        boolean success = employerService.updateEmployer(employerId,employerDTO);
        if(success){
            return ResponseEntity.status(HttpStatus.OK).build();
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    @DeleteMapping("/employer/{employerId}")
    public ResponseEntity<?> deleteEmployer(@PathVariable Long employerId){
        boolean success = employerService.deleteEmployer(employerId);
        if(success){

            return ResponseEntity.status(HttpStatus.OK).build();

        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
