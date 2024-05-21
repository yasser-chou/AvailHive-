package com.AvailHive1.AvailHive1.entity;


import com.AvailHive1.AvailHive1.dto.ReservationDTO;
import com.AvailHive1.AvailHive1.enums.ReclamationStatus;
import com.AvailHive1.AvailHive1.enums.ReservationStatus;
import com.AvailHive1.AvailHive1.enums.ReviewStatus;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    private ReservationStatus reservationStatus;

    private ReviewStatus reviewStatus;

    private ReclamationStatus reclamationStatus;

    private Date bookDate;

    @ManyToOne(fetch= FetchType.LAZY, optional = false)
    @JoinColumn(name="user_id",nullable = false)
    @OnDelete(action= OnDeleteAction.CASCADE)
    private User user;

    @ManyToOne(fetch= FetchType.LAZY, optional = false)
    @JoinColumn(name="company_id",nullable = false)
    @OnDelete(action= OnDeleteAction.CASCADE)
    private User company;

    @ManyToOne(fetch= FetchType.LAZY, optional = false)
    @JoinColumn(name="Ad_id",nullable = false)
    @OnDelete(action= OnDeleteAction.CASCADE)
    private Ad ad;

    @OneToMany()
    private List<Reclamation> reclamations = new ArrayList<>();

    public ReservationDTO getReservationDto(){
        ReservationDTO dto = new ReservationDTO();

        dto.setId(id);
        dto.setServiceName(ad.getServiceName());
        dto.setBookDate(bookDate);
        dto.setReservationStatus(reservationStatus);
        dto.setReviewStatus(reviewStatus);

        dto.setAdId(ad.getId());

        dto.setCompanyId(company.getId());

        dto.setUserName(user.getName());

        return dto;
    }
}
