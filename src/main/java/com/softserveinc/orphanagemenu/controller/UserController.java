package com.softserveinc.orphanagemenu.controller;

import java.util.Map;

import javax.sound.midi.SysexMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import antlr.collections.List;

import com.softserveinc.orphanagemenu.model.User;
import com.softserveinc.orphanagemenu.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping({ "/users" })
	public String showAllUsers(Map<String, Object> model) {
		model.put("users", userService.listUser());
		return "users";
	}

	@RequestMapping({ "/add_user" })
	public String addUser(Map<String, Object> model) {
		
		User user = new User();
		user.setEmail("email");
		user.setFirstName("firstName");
		user.setLastName("lastName");
		user.setLogin("login");
		user.setPassword("password");
		userService.saveUser(user);
		
		model.put("users", userService.listUser());
		
		return "add_user";
	}

	@RequestMapping({ "/save_user" })
	public String saveUser(Map<String, Object> model) {
		
		User user = new User();
		user.setEmail("email");
		user.setFirstName("firstName");
		user.setLastName("lastName");
		user.setLogin("login");
		user.setPassword("password");
		userService.saveUser(user);
		
		model.put("users", userService.listUser());
		
		return "users";
	}

	@RequestMapping({ "/edit_user" })
	public String editUser(Map<String, Object> model) {
		
		User user = new User();
		user.setEmail("email");
		user.setFirstName("firstName");
		user.setLastName("lastName");
		user.setLogin("login");
		user.setPassword("password");
		userService.saveUser(user);
		
		model.put("users", userService.listUser());
		
		return "users";
	}
}