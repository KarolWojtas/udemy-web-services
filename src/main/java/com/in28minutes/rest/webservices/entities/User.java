package com.in28minutes.rest.webservices.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;


import org.springframework.lang.Nullable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User{
	
	private static final Long DAY_AS_MILLIS = Long.valueOf(1000*3600*24);
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@JsonIgnore
	@Column(name="user_id")
	private Long id;
	@NotNull
	@NotBlank
	@Size(min=2)
	//@FirstLetterCapitalized
	@Column(unique=true)
	@ApiModelProperty(allowEmptyValue=false,required=true,example="John",notes="Min size is 2 chars")
	private String name;
	@Nullable
	@Past
	private Date birthDate;
	@OneToMany(mappedBy="user")
	@JsonIgnore
	private List<Post> posts = new ArrayList<Post>();
	@ApiModelProperty(hidden=true)
	public void setBirthDateFromLocalDate(LocalDate date) {
		this.setBirthDate(new Date(date.toEpochDay()*DAY_AS_MILLIS));
	}
}
