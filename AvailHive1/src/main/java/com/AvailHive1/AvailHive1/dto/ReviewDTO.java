package com.AvailHive1.AvailHive1.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class ReviewDTO {
    private Long id;

    private Date reviewDate;

    private String review;

    private Long rating;

    @NotNull(message = "User ID must not be null")
    private Long userId;

    private Long adId;

    private String clientName;

    private String serviceName;

    @NotNull(message = "Booking ID must not be null")
    private Long bookId;
}
