package com.in28minutes.rest.webservices.exceptions;

import java.time.LocalDate;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResponse {
	private LocalDate timestamp;
	private String message;
	private String details;
}
