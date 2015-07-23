package com.softserveinc.orphanagemenu.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.softserveinc.orphanagemenu.dao.RoleDao;
import com.softserveinc.orphanagemenu.dao.UserAccountDao;
import com.softserveinc.orphanagemenu.model.Role;
import com.softserveinc.orphanagemenu.model.UserAccount;
import com.softserveinc.orphanagemenu.service.UserAccountService;
import com.softserveinc.orphanagemenu.controller.validator.user.UserValidator;
import com.softserveinc.orphanagemenu.controller.validator.user.UserForm;

/*@Controller*/
public class UserAccountControllerOld {
	@Autowired
	private UserValidator userValidator;

	@Autowired
	@Qualifier("userAccountDao")
	private UserAccountDao userAccountDao;
	
	@Autowired
	@Qualifier("roleDao")
	private RoleDao roleDao;
	
	@Autowired
	@Qualifier("userAccountService")
	private UserAccountService userAccountService;
	
	@RequestMapping({"/user_account_list" })
	public String showAllUserAccounts(Map<String, Object> model) {
		List<UserAccount> userAccounts = userAccountDao.getAll();
		model.put("userAccounts", userAccounts);
		return "user_account_list";
	}
	
	@RequestMapping(value = "/user_account_create", method = RequestMethod.GET)
	public String createUserAccount(Map<String, Object> model) {
		
		UserForm userForm = new UserForm();
		model.put("userForm", userForm);
		return "user_account_create";
	}
			
	@RequestMapping(value = "/user_account_create", method = RequestMethod.POST)
	public String createUserAccount(UserForm userForm, BindingResult result) {

		userValidator.validate(userForm, result);
		if (result.hasErrors()) {
			return "user_account_create";
		}

		return "redirect:/user_account_list";
		
		
/*		if (requestParams.get("submit").equals("create")){
			
			// TODO validation
//			System.out.println(requestParams);
			
			UserAccount userAccount = new UserAccount();
			userAccount.setLogin(requestParams.get("login"));
			userAccount.setFirstName(requestParams.get("firstName"));
			userAccount.setLastName(requestParams.get("lastName"));
			userAccount.setEmail(requestParams.get("email"));
			userAccount.setPassword(requestParams.get("password"));
			
			Set<Role> roles = new HashSet<>();
			if (requestParams.get("administrator") != null){
				Role roleAdministrator = roleDao.getRoleByName("Administrator");
				roles.add(roleAdministrator);
			}
			if (requestParams.get("operator") != null){
				Role roleAdministrator = roleDao.getRoleByName("Operator");
				roles.add(roleAdministrator);
			}
			userAccount.setRoles(roles);
			userAccountDao.save(userAccount);
			return "redirect:/user_account_list";
		} else {
			return "redirect:/user_account_list";
		} 
*/
	}
	
	@RequestMapping(value = "/user_account_update", method = RequestMethod.POST )
	public String updateUserAccount(@RequestParam Map<String, String> requestParams, 
										Map<String, Object> model) {
		if (requestParams.get("submit").equals("update")){
			// TODO validation
			UserAccount userAccount = userAccountDao
										.getByID(Long.parseLong(requestParams.get("id")));
			userAccount.setLogin(requestParams.get("login"));
			userAccount.setFirstName(requestParams.get("firstName"));
			userAccount.setLastName(requestParams.get("lastName"));
			userAccount.setEmail(requestParams.get("email"));
			userAccount.setPassword(requestParams.get("password"));
			Set<Role> roles = new HashSet<>();
			if (requestParams.get("administrator") != null){
				Role roleAdministrator = roleDao.getRoleByName("Administrator");
				roles.add(roleAdministrator);
			}
			if (requestParams.get("operator") != null){
				Role roleAdministrator = roleDao.getRoleByName("Operator");
				roles.add(roleAdministrator);
			}
			userAccount.setRoles(roles);
			userAccountDao.save(userAccount);
			return "redirect:/user_account_list";
		} else {
			return "redirect:/user_account_list";
		} 

	}
	
	@RequestMapping(value = "/user_account", method = RequestMethod.GET)
	public String createUserAccount(@RequestParam("id") Long id, 
										Map<String, Object> model) {
		UserAccount userAccount = userAccountService.getByID(id);
		String administratorChecked = userAccountService.
				hasRole(userAccount, "Administrator") ? "checked=checked" : "";
		String operatorChecked = userAccountService.
				hasRole(userAccount, "Operator") ? "checked=checked" : "";
		model.put("administratorChecked", administratorChecked);
		model.put("operatorChecked", operatorChecked);
		model.put("userAccount", userAccount);
		return "user_account_update";
	}
	
	@RequestMapping(value = "/user_account_delete", method = RequestMethod.GET)
	public String deleteUserAccount(@RequestParam("id") Long id,
										Map<String, Object> model) {
		userAccountDao.delete(userAccountDao.getByID(id));
		return "redirect:/user_account_list";
	}
	
}