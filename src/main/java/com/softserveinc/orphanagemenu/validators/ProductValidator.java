package com.softserveinc.orphanagemenu.validators;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.softserveinc.orphanagemenu.dao.ProductDao;
import com.softserveinc.orphanagemenu.forms.ProductForm;
import com.softserveinc.orphanagemenu.model.Product;

@Component
public class ProductValidator implements Validator {

	@Autowired
	private ProductDao productDao;

	@Override
	public boolean supports(Class<?> clazz) {
		return ProductForm.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ProductForm productForm = (ProductForm) target;
		productNameCheck(productForm, errors);
		productDimensionCheck(errors);
		productWeightCheck(productForm, errors);
	}

	private void productNameCheck(ProductForm productForm, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "name", "fieldEmpty");
		if (errors.getFieldErrorCount("name") > 0) {
			return;
		}
		if (!productForm.getName().matches(
				"^[A-ZА-ЯЄІЇ][\\sA-ZА-ЯЄІЇa-zа-яєії'0-9]*$")) {
			errors.rejectValue("name", "productNameIllegalCharacters");
			return;
		}
		if ((productForm.getName().length()) < 2) {
			errors.rejectValue("name", "productNameTooShort");
			return;
		}
		if ((productForm.getName().length()) > 40) {
			errors.rejectValue("name", "productNameTooLong");
			return;
		}
		// FormId = 0 if product not exist
		Product product = productDao.getProduct(productForm.getName());
		if ((product != null)
				&& (!productForm.getId().equals(product.getId().toString()))) {
			errors.rejectValue("name", "productAlreadyExist");
			return;
		}
	}

	private void productDimensionCheck(Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "dimension", "fieldEmpty");
		if (errors.getFieldErrorCount("dimension") > 0) {
			return;
		}
	}

	private void productWeightCheck(ProductForm productForm, Errors errors) {
		for (Map.Entry<Long, String> formWeight : productForm.getWeightList()
				.entrySet()) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "weightList["
					+ formWeight.getKey() + "]", "fieldEmpty");
			if (errors.getFieldErrorCount("weightList[" + formWeight.getKey()
					+ "]") > 0) {
				return;
			}

			if (!formWeight.getValue().matches("^([0-9])*([,\\.]{0,1})[0-9]*$")) {
				errors.rejectValue("weightList[" + formWeight.getKey() + "]",
						"weightIllegalCharacters");
				return;
			}

			if ((formWeight.getValue().length()) > 7) {
				errors.rejectValue("weightList[" + formWeight.getKey() + "]",
						"productNormTooLong");
				return;
			}
		}
	}
}
