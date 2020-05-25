package com.bloodbank.backend;

import com.bloodbank.backend.controller.UserController;
import com.bloodbank.backend.repository.UserRepository;
import com.bloodbank.backend.service.EmailService;
import com.bloodbank.backend.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
class BackendApplicationTests {

	@Autowired
	UserController userController;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private EmailService emailService;

	@Test
	public void testUserControllerInitialization()	{
		assertThat (userController).isNotNull();
	}

	@Test
	public void testUserRepositoryInitialization()	{
		assertThat (userRepository).getClass();
	}

	@Test
	public void testUserServiceInitialization()	{
		assertThat (userService).isNotNull();
	}
}
