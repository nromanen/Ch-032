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
		
		String login = userForm.getLogin();
		UserAccount userAccount = userAccountDao.getByLogin(login);;
		
		if ((userAccount != null) && ( !userForm.getId().equals(userAccount.getId().toString()) )){
			errors.rejectValue("login", "login.dublicates", "Такий логін вже існує.");
		}
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login", "login.empty", "Приклад."); // 
		String username = userForm.getLogin();
		if ((username.length()) > 20) {
			errors.rejectValue("login", "login.tooLong", "Р›РѕРіС–РЅ РЅРµ РјРѕР¶Рµ РјР°С‚Рё Р±С–Р»СЊС€Рµ, РЅС–Р¶ 20 СЃРёРјРІРѕР»С–РІ.");
		}
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "firstName.empty", "Р†РјСЏ РЅРµ РјРѕР¶Рµ Р±СѓС‚Рё РїСѓСЃС‚РёРј.");
		String firstName = userForm.getFirstName();
		if ((firstName.length()) > 20) {
			errors.rejectValue("firstName", "firstName.tooLong", "Р†РјСЏ РЅРµ РјРѕР¶Рµ РјР°С‚Рё Р±С–Р»СЊС€Рµ, РЅС–Р¶ 20 СЃРёРјРІРѕР»С–РІ.");
		}
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "lastName.empty", "РџСЂС–Р·РІРёС‰Рµ РЅРµ РјРѕР¶Рµ Р±СѓС‚Рё РїСѓСЃС‚РёРј.");
		String lastName = userForm.getLastName();
		if ((lastName.length()) > 20) {
			errors.rejectValue("lastName", "lastName.tooLong", "РџСЂС–Р·РІРёС‰Рµ РЅРµ РјРѕР¶Рµ РјР°С‚Рё Р±С–Р»СЊС€Рµ, РЅС–Р¶ 20 СЃРёРјРІРѕР»С–РІ.");
		}
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "password.empty", "РџР°СЂРѕР»СЊ РЅРµ РјРѕР¶Рµ Р±СѓС‚Рё РїСѓСЃС‚РёРј.");
		if (userForm.getPassword().length()>20) {
			errors.rejectValue("password", "password.tooLong", "РџР°СЂРѕР»СЊ РЅРµ РјРѕР¶Рµ РјР°С‚Рё Р±С–Р»СЊС€Рµ, РЅС–Р¶ 20 СЃРёРјРІРѕР»С–РІ.");
		}
		
		if( !EmailValidator.getInstance().isValid( userForm.getEmail() ) ){
			errors.rejectValue("email", "email.notValid", "Р’РІРµРґС–С‚СЊ РїСЂР°РІРёР»СЊРЅРёР№ Email.");
		}
	}
}

