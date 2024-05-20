package com.AvailHive1.AvailHive1.dto;

import com.AvailHive1.AvailHive1.entity.Employer;
import lombok.Data;

import java.util.Date;


@Data
public class ReclamationDTO {
    private Long id;

    private String descritpion;

    private Date reclamationDate;

    private String employerName;

    private String serviceName;

    private Long employerId;


}
