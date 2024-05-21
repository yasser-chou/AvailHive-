package com.AvailHive1.AvailHive1.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;

@Entity
@Data
public class Reclamation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @Temporal(TemporalType.TIMESTAMP) //create current date automatically
    private Date reclamationDate= new Date();



    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="employer_id")
    private Employer employer;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="book_id")
    private Reservation reservation;
}
