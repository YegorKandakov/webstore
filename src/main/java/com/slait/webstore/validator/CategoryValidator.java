package com.slait.webstore.validator;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CategoryValidator implements
ConstraintValidator<Category, String> {

	List<String>	allowedCategories;
	
	@Override
	public void initialize(Category arg0) {
		allowedCategories = new ArrayList<String>();
		allowedCategories.add("Smartphone");
		allowedCategories.add("Laptop");
		allowedCategories.add("Tablet");
	}

	@Override
	public boolean isValid(String category, 
			ConstraintValidatorContext context) {
		for (String allowedCategory : allowedCategories) {
			if(category.equalsIgnoreCase(allowedCategory)) {
				return true;
			}
		}
		return false;
	}

}
