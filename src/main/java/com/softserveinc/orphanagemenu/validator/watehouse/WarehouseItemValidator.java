package com.softserveinc.orphanagemenu.validator.watehouse;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class WarehouseItemValidator implements Validator{

	@Override
	public boolean supports(Class<?> clas) {
		
		return WarehouseItemForm.class.isAssignableFrom(clas);
	}

	@Override
	public void validate(Object target, Errors errors) {
		WarehouseItemForm form = (WarehouseItemForm) target;
		
		
		if (form.getQuontity().isNaN()) {
			errors.rejectValue("quontity", "messageQuontityWrongFormat");
		}
		
	}

}
