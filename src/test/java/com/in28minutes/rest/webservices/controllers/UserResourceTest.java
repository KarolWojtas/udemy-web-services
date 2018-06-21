package com.in28minutes.rest.webservices.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.assertj.core.error.NoElementsShouldMatch;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.in28minutes.rest.webservices.entities.User;
import com.in28minutes.rest.webservices.services.UserService;

class UserResourceTest {
	@Mock
	UserService userService;
	UserResource userResource;
	MockMvc mockMvc;
	List<User> users;
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		users = new ArrayList<User>();
		users.add(User.builder().id(1l).build());
		userResource = new UserResource(userService);
		mockMvc = MockMvcBuilders.standaloneSetup(userResource)
				.setControllerAdvice(new CustomExceptionHandler()).build();
	}

	@Test
	void testFindAllUsers() throws Exception {
		
		when(userService.findAllUsers()).thenReturn(users);
		
		mockMvc.perform(get("/users/").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.[0].id", CoreMatchers.equalTo(1)));
			
	}
	@Test
	void testThrowsNoSuchElement() throws Exception {
		when(userService.findUser(Mockito.anyLong())).thenThrow(NoSuchElementException.class);
		
		mockMvc.perform(get("/users/2").contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isNotFound());
		
	}

}
