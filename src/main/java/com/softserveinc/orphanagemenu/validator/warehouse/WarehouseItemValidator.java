package com.softserveinc.orphanagemenu.validator.warehouse;




import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class WarehouseItemValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		return WarehouseItemForm.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		WarehouseItemForm form = (WarehouseItemForm) target;
		
			ValidationUtils.rejectIfEmpty(errors, "itemName", "messageWrongFormat");
			ValidationUtils.rejectIfEmpty(errors, "quantity", "messageWrongFormat");
			ValidationUtils.rejectIfEmpty(errors, "dimension", "messageWrongFormat");
		
		validateQuantity(form, errors);
		
			
		
	}
	private void validateQuantity(WarehouseItemForm form, Errors errors){
		Pattern pattern = Pattern.compile("^[\\d]+\\.?[\\d]{0,2}?$");
		Matcher matcher = pattern.matcher(form.getQuantity());

		if (!matcher.matches()){ 
			errors.rejectValue("quantity", "messageWrongFormat");
			return;
		}
	}
	
}


