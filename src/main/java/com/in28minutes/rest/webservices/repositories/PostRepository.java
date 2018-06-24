package com.in28minutes.rest.webservices.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.in28minutes.rest.webservices.entities.Post;

public interface PostRepository extends JpaRepository<Post, Long>{
	public List<Post> findPostsByUserId(Long id);
	@Modifying
	@Query("DELETE FROM Post post WHERE post.id = :userId")
	public void deleteAllByUser(@Param("userId") Long userId);
}
