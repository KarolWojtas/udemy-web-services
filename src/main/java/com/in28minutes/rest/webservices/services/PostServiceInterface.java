package com.in28minutes.rest.webservices.services;

import java.util.List;

import com.in28minutes.rest.webservices.entities.Post;

public interface PostServiceInterface {
	public Post savePost(Post post, Long userId);
	public Post getPost(Long id);
	public List<Post> getAllPosts(Long userId);
	public void deletePost(Long id);
	public List<Post> findAll();
	public Post save(Post post);
}
