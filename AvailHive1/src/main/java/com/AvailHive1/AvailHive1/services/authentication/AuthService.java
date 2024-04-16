package com.AvailHive1.AvailHive1.services.authentication;

import com.AvailHive1.AvailHive1.dto.SignUpRequestDTO;
import com.AvailHive1.AvailHive1.dto.UserDto;

public interface AuthService {
    UserDto signupClient(SignUpRequestDTO signUpRequestDTO);

    boolean presentByEmail(String email);

    UserDto signupCompany(SignUpRequestDTO signUpRequestDTO);

}