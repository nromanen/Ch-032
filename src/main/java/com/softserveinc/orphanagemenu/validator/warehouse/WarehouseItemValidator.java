package com.softserveinc.orphanagemenu.validator.warehouse;

import org.hamcrest.core.IsNot;
import org.hamcrest.core.IsNull;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
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
	System.out.println(form);
		if(form.getQuantity().isNaN())
			errors.reject("quantity","messageQuontityWrongFormat");
		
	}
	

}
