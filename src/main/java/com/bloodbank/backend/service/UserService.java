package com.bloodbank.backend.service;

import java.util.List;
import java.util.Optional;

import com.bloodbank.backend.model.User;

public interface UserService {

    List<User> getAllUsers();

    Optional<User> getUserById(Long userId);

    Optional<User> getUserByEmail(String userEmail);

    User createUser(User user);

    User updateUser(User user);

    void updateUserPassword(String newPassword, String userEmail);

    void deleteUser(User user);
}