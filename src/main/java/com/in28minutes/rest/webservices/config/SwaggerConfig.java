package com.in28minutes.rest.webservices.config;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.annotations.SwaggerDefinition;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableSwagger2
@Configuration
public class SwaggerConfig {
	
	private static final Set<String> PRODUCES = new HashSet<String>();
	@PostConstruct
	private void init() {
		PRODUCES.add("application/json");
		PRODUCES.add("application/xml");
		}
	@Bean 
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.consumes(PRODUCES)
				.produces(PRODUCES)
				.apiInfo(apiInfo());
	}
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.description("Rest udemy test")
				.build();
	}
}
