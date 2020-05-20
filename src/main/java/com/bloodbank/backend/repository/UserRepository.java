package com.bloodbank.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bloodbank.backend.model.User;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long>{

    @Query("select u from User u where u.emailId = ?1")
    Optional<User> findByEmail(String userEmail);

    @Modifying
    @Query("UPDATE User u set u.passWord = ?1 WHERE u.emailId=?2 ")
    void updatePassword(String password , String userEmail);
}