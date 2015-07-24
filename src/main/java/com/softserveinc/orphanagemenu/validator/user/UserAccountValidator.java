package com.softserveinc.orphanagemenu.validator.user;

import javax.persistence.NoResultException;

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
		
		String login = userForm.getLogin();
		UserAccount userAccount = userAccountDao.getByLogin(login);;
		
		if ((userAccount != null) && ( !userForm.getId().equals(userAccount.getId().toString()) )){
			errors.rejectValue("login", "login.dublicates", "Такий логін вже існує.");
		}
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login", "login.empty", "�������� �� �������."); // 
		String username = userForm.getLogin();
		if ((username.length()) > 20) {
			errors.rejectValue("login", "login.tooLong", "Логін не може мати більше, ніж 20 символів.");
		}
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "firstName.empty", "Імя не може бути пустим.");
		String firstName = userForm.getFirstName();
		if ((firstName.length()) > 20) {
			errors.rejectValue("firstName", "firstName.tooLong", "Імя не може мати більше, ніж 20 символів.");
		}
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "lastName.empty", "Прізвище не може бути пустим.");
		String lastName = userForm.getLastName();
		if ((lastName.length()) > 20) {
			errors.rejectValue("lastName", "lastName.tooLong", "Прізвище не може мати більше, ніж 20 символів.");
		}
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "password.empty", "Пароль не може бути пустим.");
		if (userForm.getPassword().length()>20) {
			errors.rejectValue("password", "password.tooLong", "Пароль не може мати більше, ніж 20 символів.");
		}
		
		if( !EmailValidator.getInstance().isValid( userForm.getEmail() ) ){
			errors.rejectValue("email", "email.notValid", "Введіть правильний Email.");
		}
	}
}

