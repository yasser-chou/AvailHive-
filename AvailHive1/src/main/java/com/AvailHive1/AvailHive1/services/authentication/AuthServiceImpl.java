package com.AvailHive1.AvailHive1.services.authentication;

import com.AvailHive1.AvailHive1.Repository.UserRepository;
import com.AvailHive1.AvailHive1.dto.SignUpRequestDTO;
import com.AvailHive1.AvailHive1.dto.UserDto;
import com.AvailHive1.AvailHive1.entity.User;
import com.AvailHive1.AvailHive1.enums.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService{

    @Autowired

    private UserRepository userRepository;

    public UserDto signupClient(SignUpRequestDTO signUpRequestDTO){
        User user=new User();

        user.setName(signUpRequestDTO.getName());
        user.setLastname(signUpRequestDTO.getLastname());
        user.setEmail(signUpRequestDTO.getEmail());
        user.setPhone(signUpRequestDTO.getPhone());
        user.setPassword(new BCryptPasswordEncoder().encode(signUpRequestDTO.getPassword()));

        user.setRole(UserRole.CLIENT);

        return userRepository.save(user).getDto();

    }

    @Override
    public boolean presentByEmail(String email) {
        return userRepository.findFirstByEmail(email) !=null;
    }

//    public boolean presentByEmail(String email){
//        return userRepository.findFirstByEmail(email) !=null;
//        //user exist return true
//    }

    public UserDto signupCompany(SignUpRequestDTO signUpRequestDTO){
        User user=new User();

        user.setName(signUpRequestDTO.getName());
        user.setEmail(signUpRequestDTO.getEmail());
        user.setPhone(signUpRequestDTO.getPhone());
        user.setPassword(new BCryptPasswordEncoder().encode(signUpRequestDTO.getPassword()));

        user.setRole(UserRole.COMPANY);

        return userRepository.save(user).getDto();

    }
}
