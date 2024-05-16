package com.AvailHive1.AvailHive1.services.Employer;

import com.AvailHive1.AvailHive1.Repository.EmployerRepository;
import com.AvailHive1.AvailHive1.entity.Employer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EmployerServiceImpl implements EmployerService{

    @Autowired
    private EmployerRepository employerRepository;

    //Get All Employees
    public List<Employer> getAllEmployers(){
        return employerRepository.findAll();
    }

    //Get Employer by ID
    public Optional<Employer> getEmployerById(Long id){
        return employerRepository.findById(id);
    }

    // Save new employer
    public Employer saveEmployer(Employer employer) {
        return employerRepository.save(employer);
    }

    // Delete employer by ID
    public void deleteEmployer(Long id) {
        employerRepository.deleteById(id);
    }

    // Update employer details
    public Employer updateEmployer(Long id, Employer employerDetails) {
        Employer employer = employerRepository.findById(id).orElseThrow(() -> new RuntimeException("Employer not found"));
        employer.setName(employerDetails.getName());
        employer.setPosition(employerDetails.getPosition());
        employer.setSalary(employerDetails.getSalary());
        employer.setStartDate(employerDetails.getStartDate());
        employer.setImg(employerDetails.getImg());
        return employerRepository.save(employer);
    }



}
