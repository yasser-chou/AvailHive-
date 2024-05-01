package com.AvailHive1.AvailHive1.Controller;


import com.AvailHive1.AvailHive1.dto.AdDTO;
import com.AvailHive1.AvailHive1.services.company.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @PostMapping("/ad/{userId}")
    public ResponseEntity<?> postAd(@PathVariable Long userId, @ModelAttribute AdDTO adDTO) throws IOException {
        boolean success = companyService.postAd(userId, adDTO);
        if(success){
            return ResponseEntity.status(HttpStatus.OK).build();

        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
