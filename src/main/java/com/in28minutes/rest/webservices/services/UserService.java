package com.in28minutes.rest.webservices.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.assertj.core.util.Arrays;
import org.springframework.stereotype.Service;

import com.in28minutes.rest.webservices.entities.User;
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
	@Override
	public User deleteUser(Long id) {
		User deletedUser = userRepository.findById(id).orElseThrow(
					()->new NoSuchElementException("No user with id: "+id+" found")
				);
		userRepository.delete(deletedUser);
		return deletedUser;
		
	}
	@Override
	public void saveAllUsers(User... users) {
		Stream.of(users).filter(user -> !user.getName().equals(null))
					.forEach(userRepository::save);
		
	}
	
}
