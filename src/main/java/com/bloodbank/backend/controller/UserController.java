package com.bloodbank.backend.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.bloodbank.backend.service.UserService;
import org.modelmapper.ModelMapper;
import com.bloodbank.backend.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bloodbank.backend.model.User;
import com.bloodbank.backend.beans.UserDTO;
import com.bloodbank.backend.repository.UserRepository;
import com.bloodbank.backend.exception.ResourceNotFoundException;

//sudo lsof -t -i tcp:4200 | xargs kill -9
@RestController @CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private EmailService emailService;

    @GetMapping("/users")
    public List<UserDTO> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return users.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    @GetMapping("/users/id/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable(value = "id") Long userId)
    {
        User user = userService.getUserById(userId).get();
		    return ResponseEntity.ok().body(convertToDto(user));
    }

    @GetMapping("/users/email/{emailId}")
    public ResponseEntity<User> getUserByEmail(@PathVariable(value = "emailId") String userEmail)
            throws ResourceNotFoundException
    {
        User user = userService.getUserByEmail(userEmail)
            .orElseThrow(() -> new ResourceNotFoundException("User not found for this email : " + userEmail));
        return ResponseEntity.ok().body(user);
    }

    @PutMapping("/users/updatepassword/{emailId}")
    public ResponseEntity<User> updateUserPassword(@PathVariable(value = "emailId") String userEmail,
                                     @Valid @RequestBody String newpassWord)
    {
        User user = userService.getUserByEmail(userEmail).get();
        userRepository.updatePassword(newpassWord, userEmail);
        return  ResponseEntity.ok(user);
    }

    @PostMapping("/users")
    public User createUser(@Valid @RequestBody UserDTO userDTO) {

        System.out.println("UserDetails "+userDTO.getClass());
        return userService.createUser(convertToEntity(userDTO));
    }

    @PutMapping("/users/id/{id}")
    public ResponseEntity<User> updateUser(@PathVariable(value = "id") Long userId,
                                                   @Valid @RequestBody User userDetails) throws ResourceNotFoundException {
        User user = userService.getUserById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("user not found for this id :: " + userId));

        user.setUserName(userDetails.getUserName());
        user.setEmailId(userDetails.getEmailId());
        user.setMobileNumber(userDetails.getMobileNumber());
        final User updatedUser = userService.updateUser(user);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/users/id/{id}")
    public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Long userId)
            throws ResourceNotFoundException {
        User user = userService.getUserById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));

        userService.deleteUser(user);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @GetMapping("/users/sendpasswordemail/{emailId}")
    public String updatePasswordByEmail(@PathVariable(value = "emailId") String userEmail)
    {
        String password = emailService.sendPasswordByEmail(userEmail);
        userRepository.updatePassword(password, userEmail);
        return  "Password Sent Successfully.";
    }

    public UserDTO convertToDto(User user) {
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        return userDTO;
    }

    public User convertToEntity(UserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);
        return user;
    }
}