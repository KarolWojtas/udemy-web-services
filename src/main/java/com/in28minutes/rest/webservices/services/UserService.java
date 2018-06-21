package com.in28minutes.rest.webservices.services;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import org.springframework.stereotype.Service;

import com.in28minutes.rest.webservices.entities.User;
import com.in28minutes.rest.webservices.exceptions.ResourceNotFoundException;
import com.in28minutes.rest.webservices.repositories.UserRepository;

@Service
public class UserService implements UserServiceInterface{
	private UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}
	public User findUser(Long id) {
		return userRepository.findById(id).get();
	}
	public User saveUser(User user){
		return userRepository.save(user);
	}
	@Override
	public List<User> findAllUsers() {
		
		return userRepository.findAll();
	}
	
}
