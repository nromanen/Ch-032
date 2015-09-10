package com.softserveinc.orphanagemenu.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.softserveinc.orphanagemenu.forms.WarehouseItemForm;

@Component
public class WarehouseItemValidator implements Validator {

	private static final String MESSAGE_EMPTY_FIELD = "messageEmptyField";

	public boolean supports(Class<?> clazz) {
		return WarehouseItemForm.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		WarehouseItemForm form = (WarehouseItemForm) target;

		ValidationUtils.rejectIfEmpty(errors, "itemName", MESSAGE_EMPTY_FIELD);

		ValidationUtils.rejectIfEmpty(errors, "dimension", MESSAGE_EMPTY_FIELD);

		validateQuantity(form, errors);

	}

	private void validateQuantity(WarehouseItemForm form, Errors errors) {
		if (form.getQuantity().isEmpty()) {
			errors.rejectValue("quantity", MESSAGE_EMPTY_FIELD);
			return;
		}

		Pattern pattern = Pattern.compile("^\\-?[\\d]+\\.?[\\d]{0,5}?$");
		Matcher matcher = pattern.matcher(form.getQuantity());
		if (!matcher.matches()) {
			errors.rejectValue("quantity", "messageWrongNumber");
			return;
		}
	}

}
