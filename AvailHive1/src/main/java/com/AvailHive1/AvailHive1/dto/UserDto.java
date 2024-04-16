package com.AvailHive1.AvailHive1.dto;

import com.AvailHive1.AvailHive1.enums.UserRole;
import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String email;
    private String password;
    private String name;
    private String lastname;
    private String phone;
    private UserRole role;
}