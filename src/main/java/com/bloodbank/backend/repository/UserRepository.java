package com.bloodbank.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bloodbank.backend.model.User;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    @Query("select u from User u where u.emailId = ?1")
    Optional<User> findByEmail(String userEmail);
}