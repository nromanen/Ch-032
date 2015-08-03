package com.softserveinc.orphanagemenu.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.softserveinc.orphanagemenu.forms.ProductForm;

public class ProductValidator implements Validator {
	
	

	@Override
	public boolean supports(Class<?> clazz) {
		return ProductForm.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ProductForm productForm = (ProductForm) target;
		productNameCheck(productForm, errors);
		
		
	}

	private void productNameCheck(ProductForm productForm, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "productName", "productNameEmpty");
		if (errors.getFieldErrorCount("productName") > 0 ) {
			return;
		}
	}

}
