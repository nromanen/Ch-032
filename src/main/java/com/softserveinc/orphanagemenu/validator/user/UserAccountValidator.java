package com.softserveinc.orphanagemenu.validator.user;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.softserveinc.orphanagemenu.dao.UserAccountDao;
import com.softserveinc.orphanagemenu.model.UserAccount;

@Component
public class UserAccountValidator implements Validator {

	@Autowired
	@Qualifier("userAccountDao")
	private UserAccountDao userAccountDao;

	public boolean supports(Class<?> clazz) {
		return UserAccountForm.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		UserAccountForm userForm = (UserAccountForm) target;
		
		UserAccount userAccount = userAccountDao.getByLogin(userForm.getLogin());;
		
		if ((userAccount != null) && ( !userForm.getId().equals(userAccount.getId().toString()) )){
			errors.rejectValue("login", "loginAlreadyExist");
		}

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login", "loginEmpty");
		
		if ((userForm.getLogin().length()) < 3) {
			errors.rejectValue("login", "loginTooShort");
		}
		if (!userForm.getLogin().matches("^[a-zA-Z0-9]+$")){
			errors.rejectValue("login", "loginIllegalCharacters");
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "firstNameEmpty");

		if ((userForm.getFirstName().length()) > 20) {
			errors.rejectValue("firstName", "firstNameTooLong");
		}
		if (!userForm.getFirstName().matches("^[A-ZА-ЯЄІЇ][a-zа-яєії']+$")){
			errors.rejectValue("firstName", "firstNameIllegalCharacters");
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "lastNameEmpty");

		if ((userForm.getLastName().length()) > 30) {
			errors.rejectValue("lastName", "lastNameTooLong");
		}
		
		if (!userForm.getLastName().matches("^[A-ZА-ЯЄІЇ]([a-zа-яєії']+|[a-zа-яєії']+[-][A-ZА-ЯЄІЇ][a-zа-яєії']+)$")){
			errors.rejectValue("lastName", "lastNameIllegalCharacters");
		}
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "passwordEmpty");

		if ( userForm.getPassword().length() < 6 || userForm.getPassword().length() > 15) {
			errors.rejectValue("password", "passwordTooShortOrTooLong");
		}
		
		if (!userForm.getPassword().matches("^[A-Za-z0-9,:!@#%_\\.\\?\\$\\*\\+\\-]+$")){
			errors.rejectValue("password", "passwordIllegalCharacters");
		}
		
		if( !EmailValidator.getInstance().isValid(userForm.getEmail()) ){
			errors.rejectValue("email", "emailNotValid");
		}
		
		if (!userForm.isAdministrator() && !userForm.isOperator()) {
			errors.rejectValue("administrator", "roleEmpty");
		}
	}
}
