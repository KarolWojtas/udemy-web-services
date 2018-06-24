package com.in28minutes.rest.webservices.filtering;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonFilter("SomeBeanFilter")
public class SomeBean {
	private String field1;
	private String field2;
	//@JsonIgnore
	private String field3;
}
