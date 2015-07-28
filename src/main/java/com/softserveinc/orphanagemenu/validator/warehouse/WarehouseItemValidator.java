package com.softserveinc.orphanagemenu.validator.warehouse;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class WarehouseItemValidator implements Validator {
	
	public boolean supports(Class<?> clazz) {
		return WarehouseItemForm.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		WarehouseItemForm form = (WarehouseItemForm) target;
	
		if(form.getQuantity().isNaN())
			errors.reject("quantity","messageQuontityWrongFormat");
		
	}
	

}
