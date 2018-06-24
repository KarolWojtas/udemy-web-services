package com.in28minutes.rest.webservices.filtering;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringController {
	
	@GetMapping("/filtering-2")
	public MappingJacksonValue retrieveSomeBean2() {
		SomeBean bean = new SomeBean("value1","value2","value3");
		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", SimpleBeanPropertyFilter.filterOutAllExcept("field3","field2")) ;
		MappingJacksonValue mapping = new MappingJacksonValue(bean);
		mapping.setFilters(filters);
		return mapping;
	}
}
