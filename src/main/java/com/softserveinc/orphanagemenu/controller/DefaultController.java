package com.softserveinc.orphanagemenu.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Vladimir Perepeliuk
 */
@Controller
public class DefaultController {

	@RequestMapping({ "/login" })
	public String showLoginPage(@RequestParam Map<String, String> requestParams, Map<String, Object> model) {
		if (requestParams.get("error") != null){
			model.put("error", "error");
		}
		return "login";
	}

	@RequestMapping({ "/*" })
	public String pageNotFound(Map<String, Object> model) {
		model.put("pageTitle", "error.wrongPath");
		return "/errors/pageNotFound";
	}

	@RequestMapping({ "/errors/errorPage" })
	public String showErrorPage(Map<String, Object> model) {
		model.put("pageTitle", "error.errorPage");
		return "/errors/errorPage";
	}

	@RequestMapping({ "/errors/403" })
	public String show403Page(Map<String, Object> model) {
		model.put("pageTitle", "error.error403");
		return "/errors/403";
	}

	@RequestMapping({ "/errors/404" })
	public String show404Page(Map<String, Object> model) {
		model.put("pageTitle", "error.error404");
		return "/errors/404";
	}

	@RequestMapping({ "/errors/500" })
	public String show500Page(Map<String, Object> model) {
		model.put("pageTitle", "error.error500");
		return "/errors/500";
	}
}