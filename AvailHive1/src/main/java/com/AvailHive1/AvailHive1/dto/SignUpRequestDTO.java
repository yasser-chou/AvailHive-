package com.AvailHive1.AvailHive1.dto;

import lombok.Data;

@Data
public class SignUpRequestDTO {

    private Long id;
    private String email;
    private String password;
    private String name;
    private String lastname;
    private String phone;

}