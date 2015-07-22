package com.softserveinc.orphanagemenu.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DefaultController {

	@RequestMapping({ "/*" })
	// Process requests to home page
	public String showHomePage(Map<String, Object> model) {
//		model.put("message", "Hi everybody!!!");
		return "page404";
	}
}