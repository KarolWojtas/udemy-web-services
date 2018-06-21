package com.in28minutes.rest.webservices.entities;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@Data
@NoArgsConstructor
@XmlRootElement
public class HelloWorldBean {
	private String message;
}
