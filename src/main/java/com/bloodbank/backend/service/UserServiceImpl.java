package com.bloodbank.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import  com.bloodbank.backend.model.User;
import  com.bloodbank.backend.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(Long userId) {
        return this.userRepository.findById(userId);
    }

    @Override
    public Optional<User> getUserByEmail(String userEmail) {
        return this.userRepository.findByEmail(userEmail);
    }


    @Override
    public User createUser(User user) {
        return this.userRepository.save(user);
    }

    @Override
    public User updateUser(User userDetails) {
        return this.userRepository.save(userDetails);
    }

    @Override
    public void deleteUser(User user) {
        this.userRepository.delete(user);
    }

    @Override
    public void updateUserPassword(String newPassword, String userEmail) {
       this.userRepository.updatePassword(newPassword, userEmail);
    }

}