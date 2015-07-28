package com.softserveinc.orphanagemenu.controller;

import java.io.FileNotFoundException;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DefaultController {

	@RequestMapping({ "/*" })
	public String pageNotFound(Map<String, Object> model) {
		model.put("pageTitle", "Вибачте, ми не можемо знайти таку сторінку");
		return "/errors/pageNotFound";
	}
	@RequestMapping({ "/errors/errorPage" })
	public String showErrorPage(Map<String, Object> model) {
		model.put("pageTitle", "На сторінці виникла помилка");
		return "/errors/errorPage";
	}
	@RequestMapping({ "/exampleException" })
	public String generateErrorPage(Map<String, Object> model) throws FileNotFoundException{
		throw new FileNotFoundException("Повідомлення про помилку");
	}
	@RequestMapping({ "/errors/403" })
	public String show403Page(Map<String, Object> model) {
		model.put("message", "error403");
		return "/errors/403";
	}
}