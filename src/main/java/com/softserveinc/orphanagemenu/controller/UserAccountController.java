package com.softserveinc.orphanagemenu.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

import com.softserveinc.orphanagemenu.exception.NotSuccessDBException;
import com.softserveinc.orphanagemenu.forms.UserAccountForm;
import com.softserveinc.orphanagemenu.model.Role;
import com.softserveinc.orphanagemenu.model.UserAccount;
import com.softserveinc.orphanagemenu.service.UserAccountService;
import com.softserveinc.orphanagemenu.validators.UserAccountValidator;

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
		try {
			userAccountService.deleteByID(id);
			redirectAttributes.addFlashAttribute("infoMessage", "deleteUserSuccessful");
		} catch (NotSuccessDBException e){
			redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
		}
		return "redirect:/userAccountList";
	}

	@RequestMapping(value = { "/userAccountCreate" }, method = RequestMethod.GET)
	public String showUserAccountCreate(@RequestParam Map<String, String> requestParams,
									Map<String, Object> model) {
		UserAccountForm userAccountForm = userAccountService.getUserAccountFormByUserAccountId(null);
		model.put("action", "add");
		model.put("pageTitle", "addUser");
		List<Role> allPossibleRoles = userAccountService.getAllPossibleRoles();
		model.put("allPossibleRoles", allPossibleRoles);
		model.put("userAccountForm", userAccountForm);
		model.put("validationMessages", getAllValidationMessages());
		return "userAccount";
	}

	@RequestMapping(value = { "/userAccountUpdate" }, method = RequestMethod.GET)
	public String showUserAccountUpdate(@RequestParam Map<String, String> requestParams,
									Map<String, Object> model) {
		UserAccountForm userAccountForm = userAccountService.getUserAccountFormByUserAccountId(Long.parseLong(requestParams.get("id")));
		model.put("action", "save");
		model.put("pageTitle", "editUser");
		List<Role> allPossibleRoles = userAccountService.getAllPossibleRoles();
		model.put("allPossibleRoles", allPossibleRoles);
		model.put("userAccountForm", userAccountForm);
		model.put("validationMessages", getAllValidationMessages());
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
			model.put("validationMessages", getAllValidationMessages());
			return "userAccount";
		}

		UserAccount userAccount = userAccountService.getUserAccountByUserAccountForm(userAccountForm);
		try {
			userAccountService.save(userAccount);
			redirectAttributes.addFlashAttribute("infoMessage", "saveUserSuccessful");
		} catch (NotSuccessDBException e){
			redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
		}
		return "redirect:userAccountList";
	}
	
	public Set<String> getAllValidationMessages(){
		Set<String> messages = new HashSet<>();
		messages.add("loginAlreadyExist");
		messages.add("loginTooShort");
		messages.add("loginEmpty");
		messages.add("loginIllegalCharacters");
		messages.add("firstNameEmpty");
		messages.add("firstNameTooLong");
		messages.add("firstNameIllegalCharacters");
		messages.add("lastNameEmpty");
		messages.add("lastNameTooLong");
		messages.add("lastNameIllegalCharacters");
		messages.add("passwordEmpty");
		messages.add("passwordTooShortOrTooLong");
		messages.add("passwordIllegalCharacters");
		messages.add("emailEmpty");
		messages.add("emailNotValid");
		messages.add("roleEmpty");
		messages.add("lastAdministrator");
		messages.add("userExitConfirmation");
		messages.add("yes");
		messages.add("no");
		
		
		return messages;
	}	
}
