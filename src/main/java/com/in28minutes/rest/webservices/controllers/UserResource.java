package com.in28minutes.rest.webservices.controllers;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.in28minutes.rest.webservices.entities.User;
import com.in28minutes.rest.webservices.services.UserServiceInterface;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ResponseHeader;
import springfox.documentation.swagger.readers.operation.ResponseHeaders;

@RestController
public class UserResource {
	private UserServiceInterface userService;
	@Autowired
	public UserResource(UserServiceInterface userService) {
		super();
		this.userService = userService;
	}
	@ApiOperation(value="Get all users")
	@GetMapping(value="/users", produces= {"application/json","application/xml"})
	public List<User> findAllUsers(){
		return userService.findAllUsers();
	}
	@GetMapping("/users/{id}")
	public Resource<User> findUserbyId(@PathVariable String id) {
			User user = userService.findUser(Long.valueOf(id));
			Resource<User> resource = new Resource<User>(user);
			Link linkToAll = linkTo(methodOn(this.getClass()).findAllUsers()).withRel("all-users");
			resource.add(linkToAll);
			
		return resource;
	}
	@PostMapping("/users")
	@ResponseStatus(value=HttpStatus.CREATED)
	@ApiOperation("Save a user")
	public ResponseEntity<User> saveUser(@RequestBody User user) {
		User savedUser = userService.saveUser(user);
		return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest()
					.path("/{id}")
					.buildAndExpand(savedUser.getId().toString())
					.toUri())
				.body(savedUser);
	}
	@DeleteMapping("/users/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable String id){
		userService.deleteUser(Long.valueOf(id));
		return ResponseEntity.noContent().build();
	}
}
