package com.softserveinc.orphanagemenu.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
//		productWeightCheck(productForm, errors);
	}

	private void productNameCheck(ProductForm productForm, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name",
				"productNameEmpty");
		if (errors.getFieldErrorCount("name") > 0) {
			return;
		}
		//FormId = 0 if product not exist
		System.out.println("HELOOOOOOOOOOOOOOOOO");
		Product product = productDao.getProduct(productForm.getName());
		System.out.println("HELOOOOOOOOOOOOOOOOO");
		if ((product != null) && (!productForm.getId().equals(product.getId()))){
			errors.rejectValue("name", "productAlreadyExist");
			return;
		}

		if ((productForm.getName().length()) < 2) {
			errors.rejectValue("name", "productNameTooShort");
			return;
		}
		
		if ((productForm.getName().length()) > 30) {
			errors.rejectValue("name", "productNameTooLong");
			return;
		}

		if (!productForm.getName().matches("^[A-ZА-ЯЄІЇ][a-zа-яєії'0-9]*$")) {
			errors.rejectValue("name", "productNameIllegalCharacters");
		}
	}

//	private void productWeightCheck(ProductForm productForm, Errors errors) {
////		productForm.getWeightList().get(2L);
//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "productWeight",
//				"productWeightEmpty");
//		if (errors.getFieldErrorCount("productWeight") > 0) {
//			return;
//		}
//	}
}
