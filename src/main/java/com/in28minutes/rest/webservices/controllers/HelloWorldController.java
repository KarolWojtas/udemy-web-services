package com.in28minutes.rest.webservices.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.in28minutes.rest.webservices.entities.HelloWorldBean;


@RestController
public class HelloWorldController {
	
	@GetMapping("/hello")
	public String sayHello() {
		return "Hello world";
	}
	@GetMapping(path="/helloBean", produces="application/json")
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("Hello world");
	}
}
