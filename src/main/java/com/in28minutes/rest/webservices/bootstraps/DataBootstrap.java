package com.in28minutes.rest.webservices.bootstraps;

import java.sql.Date;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.in28minutes.rest.webservices.entities.User;
import com.in28minutes.rest.webservices.services.UserService;
@Component
public class DataBootstrap implements CommandLineRunner{
	private UserService userService;
	@Autowired
	public DataBootstrap(UserService userService) {
		super();
		this.userService = userService;
	}
	@Override
	public void run(String... args) throws Exception {
		User user1 = User.builder().name("Karol")
				.build();
		user1.setBirthDateFromLocalDate(LocalDate.of(1991, 8, 21));
		userService.saveUser(user1);
		
		
	}

}
