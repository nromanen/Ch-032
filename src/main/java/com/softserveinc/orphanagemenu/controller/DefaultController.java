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
		return "pageNotFound";
	}
	@RequestMapping({ "/errorPage" })
	public String showErrorPage(Map<String, Object> model) {
		model.put("pageTitle", "На сторінці виникла помилка");
		return "errorPage";
	}
	@RequestMapping({ "/exampleException" })
	public String generateErrorPage(Map<String, Object> model) throws FileNotFoundException{
		throw new FileNotFoundException("Повідомлення про помилку");
	}
}