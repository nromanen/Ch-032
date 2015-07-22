package com.softserveinc.orphanagemenu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping({ "/", "/home" })
	// Process requests to home page
	public String showHomePage() {
	
		return "home";
		
		
	}
}