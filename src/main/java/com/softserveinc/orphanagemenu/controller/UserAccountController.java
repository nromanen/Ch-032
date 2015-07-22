package com.softserveinc.orphanagemenu.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.softserveinc.orphanagemenu.dao.RoleDao;
import com.softserveinc.orphanagemenu.dao.UserAccountDao;
import com.softserveinc.orphanagemenu.model.Role;
import com.softserveinc.orphanagemenu.model.UserAccount;
import com.softserveinc.orphanagemenu.service.UserAccountService;

@Controller
public class UserAccountController {

	@Autowired
	@Qualifier("userAccountDao")
	private UserAccountDao userAccountDao;
	
	@Autowired
	@Qualifier("roleDao")
	private RoleDao roleDao;
	
	@Autowired
	@Qualifier("userAccountService")
	private UserAccountService userAccountService;
	
	@RequestMapping({"/", "/user_account_list" })
	public String showAllUserAccounts(Map<String, Object> model) {
		List<UserAccount> userAccounts = userAccountService.getAll();
		model.put("userAccounts", userAccounts);
		return "user_account_list";
	}
	
	@RequestMapping(value = "/user_account_create", method = RequestMethod.GET)
	public String showDialogCreateUserAccount(Map<String, Object> model) {
		return "user_account_create";
	}
			
	@RequestMapping(value = "/user_account_create", method = RequestMethod.POST )
	public String createUserAccount(@RequestParam Map<String, String> requestParams, 
										Map<String, Object> model) {
		if (requestParams.get("submit").equals("create")){
			// TODO validation
			System.out.println(requestParams);
			
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
		}
		return "redirect:/user_account_list";
	}
	
	@RequestMapping(value = "/user_account", method = RequestMethod.GET)
	public String showDialogUpdateUserAccount(@RequestParam("id") Long id, 
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
		}
		return "redirect:/user_account_list";	
	}
	
	@RequestMapping(value = "/user_account_delete", method = RequestMethod.GET)
	public String deleteUserAccount(@RequestParam("id") Long id,
										Map<String, Object> model) {
		userAccountService.deleteByID(id);
		return "redirect:/user_account_list";
	}

}