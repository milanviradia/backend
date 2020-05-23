package com.bloodbank.backend.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.validation.Valid;
import com.bloodbank.backend.service.UserService;
import com.bloodbank.backend.service.EmailService;
import com.bloodbank.backend.service.SmsService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.bloodbank.backend.model.User;
import com.bloodbank.backend.repository.UserRepository;
import com.bloodbank.backend.been.UserDTO;
import com.bloodbank.backend.been.AddressDTO;
import com.bloodbank.backend.exception.ResourceNotFoundException;

@RestController @CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;
    
    @Autowired
    private SmsService smsService;

    @Autowired
    private EmailService emailService;
    
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/users")
    public List<User> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return users.stream().collect(Collectors.toList());
    }
    @GetMapping("/users/id/{id}")
    public ResponseEntity<User> getUserById(@PathVariable(value = "id") Long userId)
    {
        User user = userService.getUserById(userId).get();
		    return ResponseEntity.ok().body(user);
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
    public User createUser(@Valid @RequestBody User user) {
        return userService.createUser(user);
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
    
    public UserDTO convertToDto(User user) {
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        return userDTO;
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
    
    @PostMapping("/donnerList1")
    public void demo(String s) {
    	System.out.println(s);
    }
    
	@PostMapping("/donnerList")
    public List<UserDTO> getDonners(@Valid @RequestBody User user) {
    	System.out.println(user);
    	List<User> users = userService.getAllNearByUsers(user);
    	return users.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

    }
	
	@PostMapping("/sendSMS")
    public void sendSMS(@Valid @RequestBody AddressDTO addressDTO) {
		List<UserDTO> user = addressDTO.getUsers();
		String address = addressDTO.getAddress();
		for(UserDTO u:user) smsService.sendSMS(u.getMobileNumber(),address);
    }
	
}