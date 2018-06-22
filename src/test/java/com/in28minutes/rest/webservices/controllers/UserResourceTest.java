package com.in28minutes.rest.webservices.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.assertj.core.error.NoElementsShouldMatch;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
	@Test
	void testFindUserById() throws Exception {
		when(userService.findUser(Mockito.anyLong())).thenReturn(User.builder().id(1l).build());
		ArgumentCaptor<Long> longCaptor = ArgumentCaptor.forClass(Long.class);
		mockMvc.perform(get("/users/1").contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.id", CoreMatchers.equalTo(1)));
			
			verify(userService, times(1)).findUser(longCaptor.capture());
			assertEquals(Long.valueOf(1), longCaptor.getValue());
	}
	@Test
	void testPostUser() throws JsonProcessingException, Exception {
		User user = new User();
		user.setId(2l);
		user.setBirthDateFromLocalDate(LocalDate.of(2000, 1, 1));
		when(userService.saveUser(Mockito.any(user.getClass()))).thenReturn(user);
		
		mockMvc.perform(post("/users")
						.content(new ObjectMapper().writeValueAsString(user))
						.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isCreated())
			.andExpect(jsonPath("$.id", CoreMatchers.equalTo(2)))
			.andExpect(jsonPath("$.birthDate", CoreMatchers.notNullValue()));
	}
	@Test
	void deleteUser() {
		
	}

}
