package com.softserveinc.orphanagemenu.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.softserveinc.orphanagemenu.model.UserAccount;
import com.softserveinc.orphanagemenu.service.UserAccountService;
import com.softserveinc.orphanagemenu.validator.user.UserForm;
import com.softserveinc.orphanagemenu.validator.user.UserValidator;

@Controller
public class UserAccountController {

	@Autowired
	private UserValidator userValidator;
	
	@Autowired
	@Qualifier("userAccountService")
	private UserAccountService userAccountService;
	
	@RequestMapping({"/userAccountList"})
	public String showAllUserAccounts(Map<String, Object> model) {
		List<UserAccount> userAccounts = userAccountService.getAll();
		model.put("userAccounts", userAccounts);
		model.put("pageTitle", "Адміністрування користувачів");
		return "userAccountList";
	}

	@RequestMapping(value = "/userAccountDelete", method = RequestMethod.GET)
	public String deleteUserAccount(@RequestParam("id") Long id,
										Map<String, Object> model) {
		userAccountService.deleteByID(id);
		return "redirect:/userAccountList";
	}
		
	@RequestMapping(value = {	"/userAccountCreate",
								"/userAccountUpdate"},
					method = RequestMethod.GET)
	public String showUserAccount(@RequestParam Map<String, String> requestParams,
												Map<String, Object> model) {
		UserForm userForm = null;
		if (requestParams.get("id") == null){
			userForm = userAccountService.getUserFormByUserAccountId(null);
			model.put("pageTitle", "Новий користувач");
		} else {
			Long id = Long.parseLong(requestParams.get("id"));
			userForm = userAccountService.getUserFormByUserAccountId(id);
			model.put("pageTitle", "Редагування користувача");
		}
		model.put("userForm", userForm);
		return "userAccount";
	}

	@RequestMapping(value = "/userAccountSave",
					method = RequestMethod.POST)
	public String saveUserAccount(	@RequestParam Map<String, String> requestParams,
									Map<String, Object> model, 
									UserForm userForm, 
									BindingResult result) {
		
			userValidator.validate(userForm, result);
			if (result.hasErrors()) {
				model.put("pageTitle", requestParams.get("pageTitle"));
				return "userAccount";
			}
			
			UserAccount userAccount = userAccountService.getUserAccountByUserForm(userForm);
			userAccountService.save(userAccount);

		return "redirect:/userAccountList";
	}
}