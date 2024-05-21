package com.AvailHive1.AvailHive1.services.Employer;

import com.AvailHive1.AvailHive1.repository.EmployerRepository;
import com.AvailHive1.AvailHive1.repository.UserRepository;
import com.AvailHive1.AvailHive1.dto.EmployerDTO;
import com.AvailHive1.AvailHive1.entity.Employer;
import com.AvailHive1.AvailHive1.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployerServiceImpl implements EmployerService{

    @Autowired
    private EmployerRepository employerRepository;

    @Autowired
    private UserRepository userRepository;


    public boolean postEmployer(Long userId, EmployerDTO employerDTO) throws IOException {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
           Employer employer=new Employer();
            employer.setName(employerDTO.getName());
            employer.setPosition(employerDTO.getPosition());
            employer.setSalary(employerDTO.getSalary());
            employer.setEmail(employerDTO.getEmail());
            employer.setPhone(employerDTO.getPhone());
            employer.setStartDate(employerDTO.getStartDate());
            employer.setImg(employerDTO.getImg().getBytes());

            employer.setUser(optionalUser.get());

            employerRepository.save(employer);
            return true;
        }
        return false;
    }

    public boolean existsByEmail(String email){
        return employerRepository.existsByEmail(email);
    }
    public boolean existsByPhone(String phone) {
        return employerRepository.existsByPhone(phone);
    }

    public List<EmployerDTO> getAllEmployers(Long userId){
        return employerRepository.findAllByUserId(userId).stream().map(Employer::getEmployerDTO).collect(Collectors.toList());
    }

    @Override
    public List<EmployerDTO> getEmployers() {
        return employerRepository.findAll().stream().map(Employer::getEmployerDTO).collect(Collectors.toList());
    }

    public EmployerDTO getEmployerById(Long employerId){
        Optional<Employer> optionalEmployer = employerRepository.findById(employerId);
        if(optionalEmployer.isPresent()){
            return optionalEmployer.get().getEmployerDTO();
        }
        return null;
    }



    public boolean updateEmployer(Long EmployerId,EmployerDTO employerDTO) throws IOException {
        Optional<Employer> optionalEmployer = employerRepository.findById(EmployerId);
        if(optionalEmployer.isPresent()){
            Employer employer = optionalEmployer.get();

            employer.setName(employerDTO.getName());
            employer.setPosition(employerDTO.getPosition());
            employer.setStartDate(employerDTO.getStartDate());
            employer.setSalary(employerDTO.getSalary());
            employer.setEmail(employerDTO.getEmail());
            employer.setPhone(employerDTO.getPhone());
            if(employerDTO.getImg() != null){
                employer.setImg(employerDTO.getImg().getBytes());
            }

            employerRepository.save(employer);
            return true;

        }else{
            return false;
        }
    }

    public boolean deleteEmployer(Long EmployerId){
        Optional<Employer> optionalEmployer = employerRepository.findById(EmployerId);
        if(optionalEmployer.isPresent()){
            employerRepository.delete(optionalEmployer.get());
            return true;
        }
        return false;
    }


}
