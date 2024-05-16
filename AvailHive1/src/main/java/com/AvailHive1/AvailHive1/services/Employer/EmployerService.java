package com.AvailHive1.AvailHive1.services.Employer;

import com.AvailHive1.AvailHive1.entity.Employer;

import java.util.List;
import java.util.Optional;

public interface EmployerService {

    public List<Employer> getAllEmployers();
    public Optional<Employer> getEmployerById(Long id);
    public Employer saveEmployer(Employer employer);
    public void deleteEmployer(Long id);
    public Employer updateEmployer(Long id, Employer employerDetails);

}
