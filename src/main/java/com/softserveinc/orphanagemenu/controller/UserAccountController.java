package com.softserveinc.orphanagemenu.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.softserveinc.orphanagemenu.exception.LastAdministratorException;
import com.softserveinc.orphanagemenu.forms.UserAccountForm;
import com.softserveinc.orphanagemenu.model.Role;
import com.softserveinc.orphanagemenu.model.UserAccount;
import com.softserveinc.orphanagemenu.service.UserAccountService;
import com.softserveinc.orphanagemenu.validators.UserAccountValidator;

/**
 * @author Olexii Riabokon
 * @author Vladimir Perepeliuk
 */
@Controller
public class UserAccountController {

	@Autowired
	private UserAccountValidator userAccountValidator;

	@Autowired
	private UserAccountService userAccountService;
	
	@Autowired
	private ApplicationContext context;

	@RequestMapping({ "/userAccountList" })
	public ModelAndView showAllUserAccounts() {
		ModelAndView modelAndView = new ModelAndView("userAccountList");
		List<UserAccount> userAccounts = userAccountService.getAllDto();
		modelAndView.addObject("userAccounts", userAccounts);
		modelAndView.addObject("pageTitle", "adminUser");
		modelAndView.addObject("interfaceMessages", getInterfaceMessages());
		return modelAndView;
	}

	@RequestMapping(value = "/userAccountDelete", method = RequestMethod.GET)
	public String deleteUserAccount(final RedirectAttributes redirectAttributes,
									@RequestParam("id") Long id, 
									Map<String, Object> model) {
		try {
			userAccountService.deleteByID(id);
			redirectAttributes.addFlashAttribute("infoMessage", "deleteUserSuccessful");
		} catch (LastAdministratorException e){
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
		model.put("interfaceMessages", getInterfaceMessages());
		return "userAccount";
	}

	@RequestMapping(value = { "/userAccountEdit" }, method = RequestMethod.GET)
	public String showUserAccountEdit(@RequestParam Map<String, String> requestParams,
									Map<String, Object> model) {
		UserAccountForm userAccountForm = userAccountService.getUserAccountFormByUserAccountId(Long.parseLong(requestParams.get("id")));
		model.put("action", "save");
		model.put("pageTitle", "editUser");
		List<Role> allPossibleRoles = userAccountService.getAllPossibleRoles();
		model.put("allPossibleRoles", allPossibleRoles);
		model.put("userAccountForm", userAccountForm);
		model.put("interfaceMessages", getInterfaceMessages());
		return "userAccount";
	}

	@RequestMapping(value = "/userAccountSave", method = RequestMethod.POST)
	public String saveUserAccount(final RedirectAttributes redirectAttributes,
									@RequestParam Map<String, String> requestParams,
									Map<String, Object> model, 
									UserAccountForm userAccountForm, 
									BindingResult result) {

		userAccountValidator.validate(userAccountForm, result);
		if (result.hasErrors()) {
			model.put("action", requestParams.get("action"));
			model.put("pageTitle", requestParams.get("pageTitle"));
			List<Role> allPossibleRoles = userAccountService.getAllPossibleRoles();
			model.put("allPossibleRoles", allPossibleRoles);
			model.put("interfaceMessages", getInterfaceMessages());
			return "userAccount";
		}

		UserAccount userAccount = userAccountService.getUserAccountByUserAccountForm(userAccountForm);
		try {
			userAccountService.save(userAccount);
			redirectAttributes.addFlashAttribute("infoMessage", "saveUserSuccessful");
		} catch (LastAdministratorException e){
			redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
		}
		return "redirect:userAccountList";
	}
	
	private Set<String> getInterfaceMessages(){
		Set<String> interfaceMessages = new HashSet<>();
		interfaceMessages.add("loginAlreadyExist");
		interfaceMessages.add("loginTooShort");
		interfaceMessages.add("loginEmpty");
		interfaceMessages.add("loginIllegalCharacters");
		interfaceMessages.add("firstNameEmpty");
		interfaceMessages.add("firstNameTooLong");
		interfaceMessages.add("firstNameIllegalCharacters");
		interfaceMessages.add("lastNameEmpty");
		interfaceMessages.add("lastNameTooLong");
		interfaceMessages.add("lastNameIllegalCharacters");
		interfaceMessages.add("passwordEmpty");
		interfaceMessages.add("passwordTooShortOrTooLong");
		interfaceMessages.add("passwordIllegalCharacters");
		interfaceMessages.add("emailEmpty");
		interfaceMessages.add("emailNotValid");
		interfaceMessages.add("roleEmpty");
		interfaceMessages.add("lastAdministrator");
		interfaceMessages.add("userExitConfirmation");
		interfaceMessages.add("goNextConfirmation");
		interfaceMessages.add("yes");
		interfaceMessages.add("no");
		
		return interfaceMessages;
	}	
}
