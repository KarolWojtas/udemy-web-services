package com.in28minutes.rest.webservices.controllers;


import java.util.Arrays;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.in28minutes.rest.webservices.entities.HelloWorldBean;


@RestController
public class HelloWorldController {
	
	private MessageSource messageSource;
	@Autowired
	public HelloWorldController(MessageSource messageSource) {
		super();
		this.messageSource = messageSource;
	}
	@GetMapping("/hello")
	public String sayHello() {
		return "Hello world";
	}
	@GetMapping(path="/helloBean", produces="application/json")
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("Hello world");
	}
	@GetMapping("/hello-localized")
	public String sayHelloLocalized() {
		Object[] args = {"Karol"};
		return messageSource.getMessage("good.morning.message",args,LocaleContextHolder.getLocale());
	}
}
