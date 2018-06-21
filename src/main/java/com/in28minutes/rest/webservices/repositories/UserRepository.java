package com.in28minutes.rest.webservices.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.in28minutes.rest.webservices.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
