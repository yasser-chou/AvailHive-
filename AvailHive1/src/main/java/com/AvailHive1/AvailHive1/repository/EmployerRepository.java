package com.AvailHive1.AvailHive1.repository;

import com.AvailHive1.AvailHive1.entity.Employer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface EmployerRepository extends JpaRepository<Employer,Long> {
    List<Employer> findAllByUserId(long userId);
    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);
}
