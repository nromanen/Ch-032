package com.softserveinc.orphanagemenu.validators;

import java.util.Map;

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
		productDimensionCheck(productForm, errors);
		productWeightCheck(productForm, errors);
	}

	private void productNameCheck(ProductForm productForm, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name",
				"fieldEmpty");
		if (errors.getFieldErrorCount("name") > 0) {
			return;
		}

		if (!productForm.getName().matches("^[A-ZА-ЯЄІЇ][A-ZА-ЯЄІЇa-zа-яєії'0-9]*$")) {
			errors.rejectValue("name", "productNameIllegalCharacters");
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

		// FormId = 0 if product not exist
		Product product = productDao.getProduct(productForm.getName());
		if ((product != null)
				&& (!(productForm.getId().toString().equals(product.getId()
						.toString())))) {
			errors.rejectValue("name", "productAlreadyExist");
			return;
		}

	}
	
	private void productDimensionCheck(ProductForm productForm, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dimension",
				"fieldEmpty");
		if (errors.getFieldErrorCount("name") > 0) {
			return;
		}
	}

	private void productWeightCheck(ProductForm productForm, Errors errors) {
		
		for (Map.Entry<Long, Double> formWeight : productForm.getWeightList().entrySet()) {
			 ValidationUtils.rejectIfEmpty(errors, "weightList["+formWeight.getKey()+"]",
			 "fieldEmpty");
			 
			 if (errors.getFieldErrorCount("weightList["+formWeight.getKey()+"]") > 0) {
				 System.out.println(errors.getFieldErrorCount("weightList["+formWeight.getKey()+"]"));
			 return;
			 }

		}
		// ValidationUtils.rejectIfEmptyOrWhitespace(errors, "weightList",
		// "productWeightEmpty");
		// if (errors.getFieldErrorCount("weightList") > 0) {
		// return;
		// }
	}
}
