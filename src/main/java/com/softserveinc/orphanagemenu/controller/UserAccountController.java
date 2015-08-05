package com.softserveinc.orphanagemenu.controller;

import java.util.ArrayList;
import java.util.Formatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.softserveinc.orphanagemenu.model.Role;
import com.softserveinc.orphanagemenu.model.UserAccount;
import com.softserveinc.orphanagemenu.service.UserAccountService;
import com.softserveinc.orphanagemenu.validator.user.UserAccountForm;
import com.softserveinc.orphanagemenu.validator.user.UserAccountValidator;

@Controller
public class UserAccountController {

	@Autowired
	private UserAccountValidator userValidator;

	@Autowired
	@Qualifier("userAccountService")
	private UserAccountService userAccountService;
	
	@Autowired
	ApplicationContext context;

	@RequestMapping({ "/userAccountList" })
	public String showAllUserAccounts(Map<String, Object> model) {
		List<UserAccount> userAccounts = userAccountService.getAllDto();
		model.put("userAccounts", userAccounts);
		model.put("pageTitle", "adminUser");
		return "userAccountList";
	}

	@RequestMapping(value = "/userAccountDelete", method = RequestMethod.GET)
	public String deleteUserAccount(final RedirectAttributes redirectAttributes,
									@RequestParam("id") Long id, 
									Map<String, Object> model) {
		boolean isDeleteSuccessful = userAccountService.deleteByID(id);
		if	(isDeleteSuccessful){
			redirectAttributes.addFlashAttribute("infoMessage", "deleteUserSuccessful");
		} else {
			redirectAttributes.addFlashAttribute("errorMessage", "deleteUserNotSuccessful");
		}
		
		return "redirect:/userAccountList";
	}

	@RequestMapping(value = { "/userAccountCreate", "/userAccountUpdate" }, method = RequestMethod.GET)
	public String showUserAccount(@RequestParam Map<String, String> requestParams,
									Map<String, Object> model) {
		UserAccountForm userAccountForm = null;
		if (requestParams.get("id") == null) {
			userAccountForm = userAccountService.getUserAccountFormByUserAccountId(null);
			model.put("action", "add");
			model.put("pageTitle", "addUser");
		} else {
			Long id = Long.parseLong(requestParams.get("id"));
			userAccountForm = userAccountService.getUserAccountFormByUserAccountId(id);
			model.put("action", "save");
			model.put("pageTitle", "editUser");
		}
		List<Role> allPossibleRoles = userAccountService.getAllPossibleRoles();
		model.put("allPossibleRoles", allPossibleRoles);
		model.put("userAccountForm", userAccountForm);
		model.put("validationMessages", getAllValidationMessagesAsMap());
		return "userAccount";
	}

	@RequestMapping(value = "/userAccountSave", method = RequestMethod.POST)
	public String saveUserAccount(final RedirectAttributes redirectAttributes,
									@RequestParam Map<String, String> requestParams,
									Map<String, Object> model, 
									UserAccountForm userAccountForm, 
									BindingResult result) {
		

		userValidator.validate(userAccountForm, result);
		if (result.hasErrors()) {
			model.put("action", requestParams.get("action"));
			model.put("pageTitle", requestParams.get("pageTitle"));
			List<Role> allPossibleRoles = userAccountService.getAllPossibleRoles();
			model.put("allPossibleRoles", allPossibleRoles);
			model.put("validationMessages", getAllValidationMessagesAsMap());
			return "userAccount";
		}


		UserAccount userAccount = userAccountService.getUserAccountByUserAccountForm(userAccountForm);
		if (userAccountService.save(userAccount)!=null){
			redirectAttributes.addFlashAttribute("infoMessage", "saveUserSuccessful");
		} else {
			redirectAttributes.addFlashAttribute("errorMessage", "saveUserError");
		}
		
		return "redirect:userAccountList";
	}
	
	public Map<String,String> getAllValidationMessagesAsMap(){
		Map<String,String> messages = new HashMap<>();
		messages.put("loginEmpty", context.getMessage("loginEmpty", null, LocaleContextHolder.getLocale()));
		messages.put("loginIllegalCharacters", context.getMessage("loginIllegalCharacters", null, LocaleContextHolder.getLocale()));
		return messages;
	}	
}
