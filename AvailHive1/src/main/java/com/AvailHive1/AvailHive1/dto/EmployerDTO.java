package com.AvailHive1.AvailHive1.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.Date;

@Data
public class EmployerDTO {
    private Long id;

    private String name;
    private String position;
    private double salary;
    private String email;
    private String phone;
    private LocalDate startDate;
    private MultipartFile img;
    private byte[] returnedImg;
    private Long userId;


}
