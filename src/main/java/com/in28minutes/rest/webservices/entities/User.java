package com.in28minutes.rest.webservices.entities;

import java.util.Date;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.data.annotation.Transient;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.lang.Nullable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.in28minutes.rest.webservices.constraint.FirstLetterCapitalized;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import springfox.documentation.annotations.ApiIgnore;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User{
	
	private static final Long DAY_AS_MILLIS = Long.valueOf(1000*3600*24);
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Null
	@JsonIgnore
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
	@ApiModelProperty(hidden=true)
	public void setBirthDateFromLocalDate(LocalDate date) {
		this.setBirthDate(new Date(date.toEpochDay()*DAY_AS_MILLIS));
	}
}
