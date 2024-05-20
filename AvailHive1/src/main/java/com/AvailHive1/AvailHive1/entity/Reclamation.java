package com.AvailHive1.AvailHive1.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Reclamation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descritpion;

    @Temporal(TemporalType.TIMESTAMP) //create current date automatically
    private Date reclamationDate= new Date();

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="employer_id")
    private Employer employer;
}
