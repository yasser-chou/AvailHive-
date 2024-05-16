package com.AvailHive1.AvailHive1.entity;

import com.AvailHive1.AvailHive1.dto.EmployerDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String position;
    private double salary;

    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Lob
    @Column(columnDefinition = "longblob")
    private byte[] img;

    @ManyToOne(fetch = FetchType.LAZY, optional = false) // User can create many employers
    @JoinColumn(name = "user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    public EmployerDTO getEmployerDTO(){
        EmployerDTO employerDTO = new EmployerDTO();
        employerDTO.setId(id);
        employerDTO.setName(name);
        employerDTO.setPosition(position);
        employerDTO.setSalary(salary);
        employerDTO.setStartDate(startDate);
        employerDTO.setReturnedImg(img);

        return employerDTO;

    }

}
