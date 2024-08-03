package com.eatdaily.Onlineorderingfood.repository;

import com.eatdaily.Onlineorderingfood.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface UserRepository extends JpaRepository<User, Long> {
    public User findByEmail(String username);
}
