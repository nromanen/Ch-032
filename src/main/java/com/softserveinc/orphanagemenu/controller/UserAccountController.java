﻿package com.softserveinc.orphanagemenu.controller;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.softserveinc.orphanagemenu.model.UserAccount;
import com.softserveinc.orphanagemenu.service.UserAccountService;
import com.softserveinc.orphanagemenu.validator.user.UserAccountForm;
import com.softserveinc.orphanagemenu.validator.user.UserAccountValidator;

@Controller
public class UserAccountController {

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private UserAccountValidator userValidator;

	@Autowired
	@Qualifier("userAccountService")
	private UserAccountService userAccountService;

	@RequestMapping({ "/userAccountList" })
	public String showAllUserAccounts(Locale locale, Map<String, Object> model) {
		List<UserAccount> userAccounts = userAccountService.getAll();
		model.put("userAccounts", userAccounts);
		model.put("adminUser", messageSource.getMessage("adminUser", null,
				"adminUser", locale));
		return "userAccountList";
	}

	@RequestMapping(value = "/userAccountDelete", method = RequestMethod.GET)
	public String deleteUserAccount(@RequestParam("id") Long id,
			Map<String, Object> model) {
		userAccountService.deleteByID(id);
		return "redirect:/userAccountList";
	}

	@RequestMapping(value = { "/userAccountCreate", "/userAccountUpdate" }, method = RequestMethod.GET)
	public String showUserAccount(Locale locale,
			@RequestParam Map<String, String> requestParams,
			Map<String, Object> model) {
		UserAccountForm userAccountForm = null;
		if (requestParams.get("id") == null) {
			userAccountForm = userAccountService
					.getUserAccountFormByUserAccountId(null);
			userAccountForm.setOperator(true);
			model.put("action", "add");
			model.put("pageTitle", messageSource.getMessage("addUser", null,
					"addUser", locale));
		} else {
			Long id = Long.parseLong(requestParams.get("id"));
			userAccountForm = userAccountService
					.getUserAccountFormByUserAccountId(id);
			model.put("action", "save");
			model.put("pageTitle", messageSource.getMessage("editUser", null,
					"editUser", locale));
		}
		model.put("userAccountForm", userAccountForm);
		return "userAccount";
	}

	@RequestMapping(value = "/userAccountSave", method = RequestMethod.POST)
	public String saveUserAccount(Locale locale,
			@RequestParam Map<String, String> requestParams,
			Map<String, Object> model, UserAccountForm userAccountForm,
			BindingResult result) {

		userValidator.validate(userAccountForm, result);
		if (result.hasErrors()) {
			model.put("action", requestParams.get("action"));
			model.put("pageTitle", requestParams.get("pageTitle"));
			return "userAccount";
		}

		UserAccount userAccount = userAccountService
				.getUserAccountByUserAccountForm(userAccountForm);
		userAccountService.save(userAccount);

		return "redirect:/userAccountList";
	}
}
