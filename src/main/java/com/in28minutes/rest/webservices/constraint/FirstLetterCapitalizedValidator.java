package com.in28minutes.rest.webservices.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FirstLetterCapitalizedValidator implements ConstraintValidator<FirstLetterCapitalized, String>{

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(value.substring(0, 1).matches("^\\p{Lu}")) {
			return true;
		}
		return false;
	}

}
