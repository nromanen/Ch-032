package com.softserveinc.orphanagemenu.controller;

/**
 * @author Vladimir Perepeliuk
 */
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

	@RequestMapping({ "/login" })
	public String showLoginPage(@RequestParam Map<String, String> requestParams, Map<String, Object> model) {
		if (requestParams.get("error") != null){
			model.put("error", "error");
		}
		return "login";
	}
	
}