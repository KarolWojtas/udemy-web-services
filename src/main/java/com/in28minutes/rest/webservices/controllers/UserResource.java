package com.in28minutes.rest.webservices.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.in28minutes.rest.webservices.entities.User;
import com.in28minutes.rest.webservices.services.UserServiceInterface;

@RestController
public class UserResource {
	private UserServiceInterface userService;
	@Autowired
	public UserResource(UserServiceInterface userService) {
		super();
		this.userService = userService;
	}
	@GetMapping("/users")
	public List<User> findAllUsers(){
		return userService.findAllUsers();
	}
	@GetMapping("/users/{id}")
	public User findUserbyId(@PathVariable String id) {
		return userService.findUser(Long.valueOf(id));
	}
	@PostMapping("/users")
	@ResponseStatus(value=HttpStatus.CREATED)
	public ResponseEntity<User> saveUser(@RequestBody User user) {
		User savedUser = userService.saveUser(user);
		return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest()
					.path("/{id}")
					.buildAndExpand(savedUser.getId().toString())
					.toUri())
				.body(savedUser);
	}
}
