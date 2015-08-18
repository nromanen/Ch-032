
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
		if(errors.getFieldErrorCount("dishName") > 0 ){
			return;
		}
		
		if(!dishForm.getDishName().matches("^[A-ZА-ЯЄІЇ][\\sA-ZА-ЯЄІЇa-zа-яєії'0-9]*$")) {
			errors.rejectValue("dishName", "dishNameIllegalCharacters");
			return;
		}
		
		if((dishForm.getDishName().length())<2){
			errors.rejectValue("dishName", "dishNameTooShort");
			return;
		}
		
		if((dishForm.getDishName().length())>15) {
			errors.rejectValue("dishName", "dishNameTooLong");
			return;
		}
		
		Dish dish = dishDao.getDish(dishForm.getDishName());
		if(dish!=null) //&& (!(dishForm.getId().toString().equals(dish.getId().toString())))){
			{errors.rejectValue("dishName", "dishAlreadyExist");
			return;
		}
	}
	}
