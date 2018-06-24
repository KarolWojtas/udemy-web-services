package com.in28minutes.rest.webservices.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.in28minutes.rest.webservices.entities.Post;
import com.in28minutes.rest.webservices.entities.User;
import com.in28minutes.rest.webservices.repositories.PostRepository;
import com.in28minutes.rest.webservices.repositories.UserRepository;
@Service
public class PostService implements PostServiceInterface{
	private PostRepository postRepository;
	private UserRepository userRepository;
	
	@Autowired
	public PostService(PostRepository postRepository, UserRepository userRepository) {
		super();
		this.postRepository = postRepository;
		this.userRepository = userRepository;
	}

	@Override
	@Transactional
	public Post savePost(Post post, Long userId) {
		Optional<User> userOptional = userRepository.findById(userId);
		if(userOptional.isPresent()) {
			
			User user = userOptional.get();
			post.setUser(user);
			return postRepository.save(post);
			
		}
		throw new RuntimeException("saving not succeded");
	}


	@Override
	public Post getPost(Long id) {
		Optional<Post> post = postRepository.findById(id);
		return post.orElseThrow(NoSuchElementException::new);
	}

	@Override
	public List<Post> getAllPosts(Long userId) {
		return postRepository.findPostsByUserId(userId);
	}

	@Override
	public void deletePost(Long id) {
		postRepository.deleteById(id);
		
	}

	@Override
	public List<Post> findAll() {
		// TODO Auto-generated method stub
		return postRepository.findAll();
	}

	@Override
	public Post save(Post post) {
		// TODO Auto-generated method stub
		return postRepository.save(post);
	}

}
