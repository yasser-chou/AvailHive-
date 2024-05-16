package com.AvailHive1.AvailHive1.dto;


import com.AvailHive1.AvailHive1.enums.ReservationStatus;
import com.AvailHive1.AvailHive1.enums.ReviewStatus;
import lombok.Data;

import java.util.Date;

@Data
public class ReservationDTO {

    private Long id;

    private Date BookDate;

    private String serviceName;

    private ReservationStatus reservationStatus;

    private ReviewStatus reviewStatus;

    private Long userId;

    private String userName;

    private Long companyId;

    private Long adId;



}
