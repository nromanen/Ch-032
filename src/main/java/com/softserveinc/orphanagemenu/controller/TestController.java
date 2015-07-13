package com.softserveinc.orphanagemenu.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.softserveinc.orphanagemenu.dao.ProductDao;
import com.softserveinc.orphanagemenu.model.Product;

@Controller
public class TestController {

	@Autowired
	private ProductDao productDAO;
	
	@RequestMapping({ "/test" })
	// Process requests to test page
	public String showHomePage(Map<String, Object> model) {
		String realLogger = System.getProperty("org.jboss.logging.provider");
		model.put("realLogger", realLogger);
		
		Product product = new Product("tee", "ml");
		productDAO.saveProduct(product);
		
		return "test";
	}
}