package com.AvailHive1.AvailHive1.dto;

import com.AvailHive1.AvailHive1.entity.Employer;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;


@Data
public class ReclamationDTO {
    private Long id;

    private String description;

    private Date reclamationDate;




    private Long reservationId;

    private Long employerId;


}
