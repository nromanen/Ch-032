package com.softserveinc.orphanagemenu.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.softserveinc.orphanagemenu.dao.ProductDao;
import com.softserveinc.orphanagemenu.forms.ProductForm;
import com.softserveinc.orphanagemenu.model.Product;

public class ProductValidator implements Validator {

	@Autowired
	@Qualifier("productDaoImpl")
	private ProductDao productDao;

	@Override
	public boolean supports(Class<?> clazz) {
		return ProductForm.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ProductForm productForm = (ProductForm) target;
		productNameCheck(productForm, errors);
		productWeightCheck(productForm, errors);

	}

	private void productNameCheck(ProductForm productForm, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "productName",
				"productNameEmpty");
		if (errors.getFieldErrorCount("productName") > 0) {
			return;
		}

		Product product = productDao.getProduct(productForm.getName());
		if (product != null) {
			errors.rejectValue("productName", "productAlreadyExist");
			return;
		}

		if ((productForm.getName().length()) < 3) {
			errors.rejectValue("productName", "productNameTooShort");
			return;
		}

		if (!productForm.getName().matches("^[а-яєіїА-ЯЄІЇ0-9]+$")) {
			errors.rejectValue("productName", "productNameIllegalCharacters");
		}
	}

	private void productWeightCheck(ProductForm productForm, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "productWeight",
				"productWeightEmpty");
		if (errors.getFieldErrorCount("productWeight") > 0) {
			return;
		}

	}
}
