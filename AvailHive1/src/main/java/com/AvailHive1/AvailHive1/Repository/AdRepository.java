package com.AvailHive1.AvailHive1.Repository;

import com.AvailHive1.AvailHive1.entity.Ad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdRepository extends JpaRepository<Ad,Long> {

}


