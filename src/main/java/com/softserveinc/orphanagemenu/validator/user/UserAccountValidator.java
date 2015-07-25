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
public class UserAccountValidator implements Validator{

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
			errors.rejectValue("login", "login.dublicates", 
				"Такий логін вже існує.");
		}
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login", "login.empty", 
				"Логін не може бути порожнім.");
		
		if ((userForm.getLogin().length()) < 3) {
			errors.rejectValue("login", "login.tooShort", 
				"Логін має бути не менше, ніж 3 символа.");
		}
		
		if (!userForm.getLogin().matches("^[a-zA-Z0-9]+$")){
			errors.rejectValue("login", "login.illegalCharacters", 
				"Допустимі символи логіна - будь які літери латинського алфавіту та цифри.");
		}
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "firstName.empty", 
				"Імя не може бути порожнім.");

		if ((userForm.getFirstName().length()) > 20) {
			errors.rejectValue("firstName", "firstName.tooLong", 
				"Імя має бути не більше, ніж 20 символів.");
		}
		
		if (!userForm.getFirstName().matches("^[A-ZА-ЯЄІЇ][a-zа-яєії']+$")){
			errors.rejectValue("firstName", "firstName.illegalCharacters", 
				"Допустимі символи імені - будь які літери.");
		}
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "lastName.empty", 
				"Прізвище не може бути порожнім.");

		if ((userForm.getLastName().length()) > 30) {
			errors.rejectValue("lastName", "lastName.tooLong", 
				"Прізвище має бути не більше, ніж 30 символів.");
		}
		
		if (!userForm.getLastName().matches("^[A-ZА-ЯЄІЇ]([a-zа-яєії']+|[a-zа-яєії']+[-][A-ZА-ЯЄІЇ][a-zа-яєії']+)$")){
			errors.rejectValue("lastName", "lastName.illegalCharacters", 
				"Допустимі символи прізвища - будь які літери і дефіс.");
		}
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "password.empty", 
				"Пароль не може бути порожнім.");

		if ( userForm.getPassword().length() < 6 || userForm.getPassword().length() > 15) {
			errors.rejectValue("password", "password.tooShortOrTooLong", 
				"Пароль має бути не менше, ніж 6 символів та не більше ніж 15 символів.");
		}
		
		if (!userForm.getPassword().matches("^[A-Za-z0-9,:!@#%_\\.\\?\\$\\*\\+\\-]+$")){
			errors.rejectValue("password", "password.illegalCharacters", 
				"Допустимі символи пароля - будь які літери латинського алфавіту, "
				+ "цифри, та наступні символи: . , ! ? @ # $ % : * - + _");
		}
		
		if( !EmailValidator.getInstance().isValid(userForm.getEmail()) ){
			errors.rejectValue("email", "email.notValid", 
				"Невалідний Email.");
		}
		
		if (!userForm.isAdministrator() && !userForm.isOperator()) {
			errors.rejectValue("administrator", "role.Empty", 
					"Необхідно вибрати хочаб одну роль.");
		}
	}
}
