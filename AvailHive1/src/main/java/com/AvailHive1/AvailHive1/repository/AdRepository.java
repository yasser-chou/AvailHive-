package com.AvailHive1.AvailHive1.repository;

import com.AvailHive1.AvailHive1.entity.Ad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdRepository extends JpaRepository<Ad,Long> {
        List<Ad> findAllByUserId(long userId);

        List<Ad> findAllByServiceNameContaining(String name);
}


