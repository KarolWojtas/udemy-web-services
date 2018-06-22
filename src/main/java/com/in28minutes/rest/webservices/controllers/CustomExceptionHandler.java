package com.in28minutes.rest.webservices.controllers;

import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import com.in28minutes.rest.webservices.exceptions.ExceptionResponse;

@ControllerAdvice
@ResponseBody
public class CustomExceptionHandler{
	
	
	
	@ExceptionHandler(NoSuchElementException.class)
	@ResponseStatus(value=HttpStatus.NOT_FOUND)
	public ExceptionResponse handleNoSuchElement(NoSuchElementException e, WebRequest request) {
		ExceptionResponse response = new ExceptionResponse(LocalDate.now(), e.getMessage(), request.getDescription(false));
		return response;
	}
	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(value=HttpStatus.BAD_REQUEST)
	public ExceptionResponse handleConstraintViolation(ConstraintViolationException e, WebRequest request) {
		String body = e.getConstraintViolations().stream()
				.map(cv->{
					String message = "Violation for property: "+cv.getPropertyPath()
							+", on value: "+cv.getInvalidValue()
							+", cause: "+cv.getMessage();
					return message;
				})
				.collect(Collectors.joining("; "));
		return new ExceptionResponse(LocalDate.now(), body, request.getDescription(true));
	}
	@ExceptionHandler(Exception.class)
	@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
	public ExceptionResponse handleAllExceptions(Exception e , WebRequest request) {
		ExceptionResponse response = new ExceptionResponse(LocalDate.now(), e.getMessage(), request.getDescription(false));
		return response;
	}
	

	
}
