package com.in28minutes.rest.webservices.controllers;

import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.in28minutes.rest.webservices.entities.User;
import com.in28minutes.rest.webservices.exceptions.ExceptionResponse;

@ControllerAdvice
@ResponseBody
public class CustomExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
	public ExceptionResponse handleAllExceptions(Exception e, WebRequest request) {
		return new ExceptionResponse(LocalDate.now(), e.getMessage(), request.getDescription(true));
	}
	
	@ExceptionHandler(NoSuchElementException.class)
	@ResponseStatus(value=HttpStatus.NOT_FOUND)
	public ExceptionResponse handleNoSuchElement(NoSuchElementException e) {
		return new ExceptionResponse(LocalDate.now(), e.getMessage(), e.getCause().toString());
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
	

	
}
