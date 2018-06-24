package com.in28minutes.rest.webservices.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="post_id")
	private Long id;
	private String description;
	@ManyToOne(cascade= {CascadeType.ALL}, fetch=FetchType.EAGER)
	@JsonIgnore
	private User user;
}
