package com.AvailHive1.AvailHive1.services.Employer;

import com.AvailHive1.AvailHive1.dto.AdDTO;
import com.AvailHive1.AvailHive1.dto.EmployerDTO;
import com.AvailHive1.AvailHive1.entity.Employer;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface EmployerService {

    boolean postEmployer(Long userId, EmployerDTO employerDTO) throws IOException;
    List<EmployerDTO> getAllEmployers(Long userId);
    List<EmployerDTO> getEmployers();
    EmployerDTO getEmployerById(Long employerId);
    boolean updateEmployer(Long EmployerId,EmployerDTO employerDTO) throws IOException;
    boolean deleteEmployer(Long EmployerId);
    boolean existsByPhone(String phone);
    boolean existsByEmail(String email);



}
