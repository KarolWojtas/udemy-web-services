package com.in28minutes.rest.webservices.services;

import java.util.List;
import com.in28minutes.rest.webservices.entities.User;

public interface UserServiceInterface {
	public User saveUser(User user);
	public User findUser(Long id);
	public List<User> findAllUsers();
}
