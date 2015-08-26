package com.softserveinc.orphanagemenu.validators;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.softserveinc.orphanagemenu.dao.UserAccountDao;
import com.softserveinc.orphanagemenu.forms.UserAccountForm;
import com.softserveinc.orphanagemenu.model.UserAccount;
import com.softserveinc.orphanagemenu.service.UserAccountService;

/**
 * @author Vladimir Perepeliuk
 * @author Olexii Riabokon
 */
@Component
public class UserAccountValidator implements Validator {

	@Autowired
	private UserAccountDao userAccountDao;
	
	@Autowired
	private UserAccountService userAccountService;
	
	public boolean supports(Class<?> clazz) {
		return UserAccountForm.class.isAssignableFrom(clazz);
	}

	public UserAccountValidator(){
	}
	
	public UserAccountValidator(UserAccountDao userAccountDao,
			UserAccountService userAccountService) {
		this.userAccountDao = userAccountDao;
		this.userAccountService = userAccountService;
	}

	public void validate(Object target, Errors errors) {
		UserAccountForm userAccountForm = (UserAccountForm) target;
		loginCheck(userAccountForm, errors);
		firstNameCheck(userAccountForm, errors);
		lastNameCheck(userAccountForm, errors);
		passwordCheck(userAccountForm, errors);
		emailCheck(userAccountForm, errors);
		atLeastOneRoleCheck(userAccountForm, errors);
		lastAdministratorUnassignCheck(userAccountForm, errors);
	}

	private void loginCheck(UserAccountForm userAccountForm, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login", "loginEmpty");
		if (errors.getFieldErrorCount("login") > 0 ) {
			return;
		}

		UserAccount userAccount = userAccountDao.getByLogin(userAccountForm.getLogin());
		if ((userAccount != null) && ( !userAccountForm.getId().equals(userAccount.getId().toString()) )){
			errors.rejectValue("login", "loginAlreadyExist");
			return;
		}

		if ((userAccountForm.getLogin().length()) < 3) {
			errors.rejectValue("login", "loginTooShort");
			return;
		}

		if (!userAccountForm.getLogin().matches("^[a-zA-Z0-9]+$")){
			errors.rejectValue("login", "loginIllegalCharacters");
			return;
		}
	}
	
	private void passwordCheck(UserAccountForm userAccountForm, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "passwordEmpty");
		if (errors.getFieldErrorCount("password") > 0 ) {
			return;
		}
		
		if ( userAccountForm.getPassword().length() < 6 || userAccountForm.getPassword().length() > 15) {
			errors.rejectValue("password", "passwordTooShortOrTooLong");
			return;
		}
		
		if (!userAccountForm.getPassword().matches("^[A-Za-z0-9,:!@#%_\\.\\?\\$\\*\\+\\-]+$")){
			errors.rejectValue("password", "passwordIllegalCharacters");
			return;
		}
	}
	
	private void firstNameCheck(UserAccountForm userAccountForm, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "firstNameEmpty");
		if (errors.getFieldErrorCount("firstName") > 0 ) {
			return;
		}

		if ((userAccountForm.getFirstName().length()) > 20) {
			errors.rejectValue("firstName", "firstNameTooLong");
			return;
		}
		
		if (!userAccountForm.getFirstName().matches("^[A-ZА-ЯЄІЇ][a-zа-яєії']*$")){
			errors.rejectValue("firstName", "firstNameIllegalCharacters");
			return;
		}
	}
	
	private void lastNameCheck(UserAccountForm userAccountForm, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "lastNameEmpty");
		if (errors.getFieldErrorCount("lastName") > 0 ) {
			return;
		}

		if ((userAccountForm.getLastName().length()) > 30) {
			errors.rejectValue("lastName", "lastNameTooLong");
			return;
		}
		
		if (!userAccountForm.getLastName().matches("^[A-ZА-ЯЄІЇ]([a-zа-яєії']+|[a-zа-яєії']*[-][A-ZА-ЯЄІЇ][a-zа-яєії']*)$")){
			errors.rejectValue("lastName", "lastNameIllegalCharacters");
			return;
		}
	}
	
	private void emailCheck(UserAccountForm userAccountForm, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "emailEmpty");
		if (errors.getFieldErrorCount("email") > 0 ) {
			return;
		}
		
		if( !EmailValidator.getInstance().isValid(userAccountForm.getEmail()) ){
			errors.rejectValue("email", "emailNotValid");
			return;
		}
	}
	
	private void lastAdministratorUnassignCheck(UserAccountForm userAccountForm, Errors errors) {
		if (!userAccountForm.getRoles().containsKey("Administrator")
				&& !"".equals(userAccountForm.getId()) 
				&& userAccountService.isLastAdministrator(Long.parseLong(userAccountForm.getId()))) {
			errors.rejectValue("roles", "lastAdministrator");
		}
	}
	
	private void atLeastOneRoleCheck(UserAccountForm userAccountForm, Errors errors) {
		if (userAccountForm.getRoles().isEmpty()) {
			errors.rejectValue("roles", "roleEmpty");
		}		
	}

}
