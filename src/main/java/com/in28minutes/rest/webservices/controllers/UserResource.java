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

import com.in28minutes.rest.webservices.entities.Post;
import com.in28minutes.rest.webservices.entities.User;
import com.in28minutes.rest.webservices.services.PostService;
import com.in28minutes.rest.webservices.services.PostServiceInterface;
import com.in28minutes.rest.webservices.services.UserServiceInterface;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ResponseHeader;
import springfox.documentation.swagger.readers.operation.ResponseHeaders;

@RestController
public class UserResource {
	private UserServiceInterface userService;
	private PostServiceInterface postService;
	
	public UserResource(UserServiceInterface userService, PostServiceInterface postService) {
		super();
		this.userService = userService;
		this.postService = postService;
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
	@GetMapping("/users/{userId}/posts")
	public List<Post> getAllPostByUser(@PathVariable String userId){
		return postService.getAllPosts(Long.valueOf(userId));
	}
	@PostMapping("/users/{userId}/posts")
	public Resource<Post> savePost(@PathVariable String userId, @RequestBody Post post) {
		Post savedPost =  postService.savePost(post, Long.valueOf(userId));
		Resource<Post> resource = new Resource<Post>(savedPost);
		Link linkToAll = linkTo(methodOn(this.getClass()).getAllPostByUser(userId)).withRel("all-posts");
		resource.add(linkToAll);
		return resource;
	}
}
