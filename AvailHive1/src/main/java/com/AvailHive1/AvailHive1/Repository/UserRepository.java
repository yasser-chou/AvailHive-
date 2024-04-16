package com.AvailHive1.AvailHive1.Repository;

import com.AvailHive1.AvailHive1.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface UserRepository extends JpaRepository<User,Long> {
    User findFirstByEmail(String email);
}
