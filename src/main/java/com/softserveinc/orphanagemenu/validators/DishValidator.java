//package com.softserveinc.orphanagemenu.validators;
//
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.stereotype.Component;
//import org.springframework.validation.Errors;
//import org.springframework.validation.ValidationUtils;
//import org.springframework.validation.Validator;
//
//import com.softserveinc.orphanagemenu.dao.DishDao;
//import com.softserveinc.orphanagemenu.json.DishForm;
//import com.softserveinc.orphanagemenu.model.Dish;
//
//
//@Component
//public class DishValidator implements Validator {
//
//	@Autowired
//	@Qualifier("dishDao")
//	private DishDao dishDao;
//
//	@Override
//	public boolean supports(Class<?> clazz) {
//		return DishForm.class.isAssignableFrom(clazz);
//	}
//
//	@Override
//	public void validate(Object target, Errors errors) {
//		DishForm dishForm = (DishForm) target;
//		dishNameCheck(dishForm, errors);
//
//	}
//
//	private void dishNameCheck(DishForm dishForm, Errors errors) {
//		ValidationUtils.rejectIfEmpty(errors, "dishName", "productNameIllegalCharacters");
//		if (errors.getFieldErrorCount("dishName") > 0) {
//			return;
//		}
//		if (!dishForm.getDishName().matches(
//				"^[A-ZА-ЯЄІЇ][\\sA-ZА-ЯЄІЇa-zа-яєії'0-9]*$")) {
//			errors.rejectValue("dishName", "productNameIllegalCharacters");
//			return;
//		}
//		if ((dishForm.getDishName().length()) < 2) {
//			errors.rejectValue("dishName", "productNameIllegalCharacters");
//			return;
//		}
//		if ((dishForm.getDishName().length()) > 40) {
//			errors.rejectValue("dishName", "productNameIllegalCharacters");
//			return;
//		}
//		// FormId = 0 if dish not exist
//		Dish dish = dishDao.getDishByName(dishForm.getDishName());
//		if ((dish != null)
//				&& (!(dishForm.getId().toString().equals (dish.getId()
//						.toString())))) {
//			errors.rejectValue("dishName", "productNameIllegalCharacters");
//			return;
//		}
//	}
//
//	private void dishDimensionCheck(Errors errors) {
//		ValidationUtils.rejectIfEmpty(errors, "dimension", "fieldEmpty");
//		if (errors.getFieldErrorCount("dimension") > 0) {
//			return;
//		}
//	}
//
//	private void dishWeightCheck(DishForm dishForm, Errors errors) {
//		for (Map.Entry<Long, String> formWeight : dishForm.getCategory()
//				.entrySet()) {
//			ValidationUtils.rejectIfEmptyOrWhitespace(errors,
//					"weightList[" + formWeight.getKey() + "]", "fieldEmpty");
//			if (errors.getFieldErrorCount("weightList[" + formWeight.getKey()
//					+ "]") > 0) {
//				return;
//			}
//
//			if (!formWeight.getValue().matches(
//					"^([0-9])*([,]{0,1})[0-9]*$")) {
//				errors.rejectValue("weightList[" + formWeight.getKey() + "]", "weightIllegalCharacters");
//				return;
//			}
//			
//			if ((formWeight.getValue().length()) > 7) {
//				errors.rejectValue("weightList[" + formWeight.getKey() + "]",
//						"dishNormTooLong");
//				return;
//			}
//		}
//	}
//}
package com.softserveinc.orphanagemenu.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.softserveinc.orphanagemenu.dao.DishDao;
import com.softserveinc.orphanagemenu.forms.DishForm;
import com.softserveinc.orphanagemenu.model.Dish;

@Component
public class DishValidator implements Validator {

	@Autowired
	private DishDao dishDao;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return DishForm.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		DishForm dishForm = (DishForm) target;
		dishNameCheck(dishForm, errors);
	}
	
	private void dishNameCheck(DishForm dishForm, Errors errors){
		ValidationUtils.rejectIfEmpty(errors, "dishName", "fieldEmpty");
		
		
		if((dishForm.getDishName().length())==0){
			errors.rejectValue("dishName", "dishNameTooShort");
			return;
		}
		
		Dish dish = dishDao.getDish(dishForm.getDishName());
		if(dish!=null) {
			errors.rejectValue("dishName", "dishAlreadyExist");
			return;
		}
	}
}
