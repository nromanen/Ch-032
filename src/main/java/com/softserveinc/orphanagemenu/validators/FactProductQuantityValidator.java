package com.softserveinc.orphanagemenu.validators;

import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.softserveinc.orphanagemenu.forms.FactProductsQuantityForm;
import com.softserveinc.orphanagemenu.forms.ProductForm;

@Component
public class FactProductQuantityValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return ProductForm.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		FactProductsQuantityForm factProductsQuantityForm = (FactProductsQuantityForm) target;
		factProductQuantityFirstAgeCategoryCheck(factProductsQuantityForm,
				errors);
		factProductQuantitySecondAgeCategoryCheck(factProductsQuantityForm,
				errors);
		factProductQuantityThirdAgeCategoryCheck(factProductsQuantityForm,
				errors);
		factProductQuantityFourthAgeCategoryCheck(factProductsQuantityForm,
				errors);
	}

	private static void factProductQuantityFirstAgeCategoryCheck(
			FactProductsQuantityForm factProductsQuantityForm, Errors errors) {
		for (Map.Entry<Long, String> factProductQuantityFirstAgeCategory : factProductsQuantityForm
				.getFactProductQuantityFirstAgeCategory().entrySet()) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors,
					"factProductQuantityFirstAgeCategory["
							+ factProductQuantityFirstAgeCategory.getKey()
							+ "]", "fieldEmpty");
			if (errors
					.getFieldErrorCount("factProductQuantityFirstAgeCategory["
							+ factProductQuantityFirstAgeCategory.getKey()
							+ "]") > 0) {
				return;
			}

			if (!factProductQuantityFirstAgeCategory.getValue().matches(
					"^([0-9])*([,\\.]{0,1})[0-9]*$")) {
				errors.rejectValue("factProductQuantityFirstAgeCategory["
						+ factProductQuantityFirstAgeCategory.getKey() + "]",
						"weightIllegalCharacters");
				return;
			}

			if ((factProductQuantityFirstAgeCategory.getValue().length()) > 7) {
				errors.rejectValue("factProductQuantityFirstAgeCategory["
						+ factProductQuantityFirstAgeCategory.getKey() + "]",
						"productNormTooLong");
				return;
			}
		}
	}

	private static void factProductQuantitySecondAgeCategoryCheck(
			FactProductsQuantityForm factProductsQuantityForm, Errors errors) {
		for (Map.Entry<Long, String> factProductQuantitySecondAgeCategory : factProductsQuantityForm
				.getFactProductQuantitySecondAgeCategory().entrySet()) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors,
					"factProductQuantitySecondAgeCategory["
							+ factProductQuantitySecondAgeCategory.getKey()
							+ "]", "fieldEmpty");
			if (errors
					.getFieldErrorCount("factProductQuantitySecondAgeCategory["
							+ factProductQuantitySecondAgeCategory.getKey()
							+ "]") > 0) {
				return;
			}

			if (!factProductQuantitySecondAgeCategory.getValue().matches(
					"^([0-9])*([,\\.]{0,1})[0-9]*$")) {
				errors.rejectValue("factProductQuantitySecondAgeCategory["
						+ factProductQuantitySecondAgeCategory.getKey() + "]",
						"weightIllegalCharacters");
				return;
			}

			if ((factProductQuantitySecondAgeCategory.getValue().length()) > 7) {
				errors.rejectValue("factProductQuantitySecondAgeCategory["
						+ factProductQuantitySecondAgeCategory.getKey() + "]",
						"productNormTooLong");
				return;
			}
		}
	}

	private static void factProductQuantityThirdAgeCategoryCheck(
			FactProductsQuantityForm factProductsQuantityForm, Errors errors) {
		for (Map.Entry<Long, String> factProductQuantityThirdAgeCategory : factProductsQuantityForm
				.getFactProductQuantityThirdAgeCategory().entrySet()) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors,
					"factProductQuantityThirdAgeCategory["
							+ factProductQuantityThirdAgeCategory.getKey()
							+ "]", "fieldEmpty");
			if (errors
					.getFieldErrorCount("factProductQuantityThirdAgeCategory["
							+ factProductQuantityThirdAgeCategory.getKey()
							+ "]") > 0) {
				return;
			}

			if (!factProductQuantityThirdAgeCategory.getValue().matches(
					"^([0-9])*([,\\.]{0,1})[0-9]*$")) {
				errors.rejectValue("factProductQuantityThirdAgeCategory["
						+ factProductQuantityThirdAgeCategory.getKey() + "]",
						"weightIllegalCharacters");
				return;
			}

			if ((factProductQuantityThirdAgeCategory.getValue().length()) > 7) {
				errors.rejectValue("factProductQuantityThirdAgeCategory["
						+ factProductQuantityThirdAgeCategory.getKey() + "]",
						"productNormTooLong");
				return;
			}
		}
	}

	private static void factProductQuantityFourthAgeCategoryCheck(
			FactProductsQuantityForm factProductsQuantityForm, Errors errors) {
		for (Map.Entry<Long, String> factProductQuantityFourthAgeCategory : factProductsQuantityForm
				.getFactProductQuantityFourthAgeCategory().entrySet()) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors,
					"factProductQuantityFourthAgeCategory["
							+ factProductQuantityFourthAgeCategory.getKey()
							+ "]", "fieldEmpty");
			if (errors
					.getFieldErrorCount("factProductQuantityFourthAgeCategory["
							+ factProductQuantityFourthAgeCategory.getKey()
							+ "]") > 0) {
				return;
			}

			if (!factProductQuantityFourthAgeCategory.getValue().matches(
					"^([0-9])*([,\\.]{0,1})[0-9]*$")) {
				errors.rejectValue("factProductQuantityFourthAgeCategory["
						+ factProductQuantityFourthAgeCategory.getKey() + "]",
						"weightIllegalCharacters");
				return;
			}

			if ((factProductQuantityFourthAgeCategory.getValue().length()) > 7) {
				errors.rejectValue("factProductQuantityFourthAgeCategory["
						+ factProductQuantityFourthAgeCategory.getKey() + "]",
						"productNormTooLong");
				return;
			}
		}
	}
}
