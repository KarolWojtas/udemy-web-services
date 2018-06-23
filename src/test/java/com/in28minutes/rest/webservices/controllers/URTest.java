package com.in28minutes.rest.webservices.controllers;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.in28minutes.rest.webservices.RestfulWebServicesApplication;
import com.in28minutes.rest.webservices.entities.User;
import com.in28minutes.rest.webservices.services.UserService;
import com.in28minutes.rest.webservices.services.UserServiceInterface;
@SpringBootTest
@RunWith(SpringRunner.class)
public class URTest {
	@Mock
	UserServiceInterface userService;
	MockMvc mockMvc;
	@Autowired
	WebApplicationContext context;
	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
		MockitoAnnotations.initMocks(this);
	}
	@Test
	public void testContext() {
		assertNotNull(context);
	}
	@Test
	public void testFindUserbyId() throws Exception {
		
		mockMvc.perform(get("/users/1"))
		.andExpect(status().isOk());
		
	}

}
