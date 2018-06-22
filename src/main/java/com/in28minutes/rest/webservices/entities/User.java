package com.in28minutes.rest.webservices.entities;

import java.util.Date;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.springframework.lang.Nullable;

import com.in28minutes.rest.webservices.constraint.FirstLetterCapitalized;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
	
	private static final Long DAY_AS_MILLIS = Long.valueOf(1000*3600*24);
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Null
	private Long id;
	@NotNull
	@NotBlank
	@Size(min=2)
	//@FirstLetterCapitalized
	private String name;
	@Nullable
	@Past
	private Date birthDate;
	
	public void setBirthDateFromLocalDate(LocalDate date) {
		this.setBirthDate(new Date(date.toEpochDay()*DAY_AS_MILLIS));
	}
}
