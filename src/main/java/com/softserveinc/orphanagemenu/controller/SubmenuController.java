package com.softserveinc.orphanagemenu.controller;

import java.text.ParseException;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.softserveinc.orphanagemenu.dao.DailyMenuDao;
import com.softserveinc.orphanagemenu.forms.FactProductsQuantityForm;
import com.softserveinc.orphanagemenu.service.AgeCategoryService;
import com.softserveinc.orphanagemenu.service.DailyMenuService;
import com.softserveinc.orphanagemenu.service.SubmenuService;

@Controller
public class SubmenuController {
	
	@Autowired
	private SubmenuService submenuService;
	
	@Autowired
	private DailyMenuDao dailyMenuDao;
	
	@Autowired
	private DailyMenuService dailyMenuService;
	
	@Autowired
	private AgeCategoryService  ageCategoryService;
	
	
	@RequestMapping({ "/e" })
	public String editFactComponentsQuantity(Map<String, Object> model) throws ParseException {
		String dailyMenuId = "1";
		String consumptionTypeId = "2";
		String dishId = "3";
		FactProductsQuantityForm factProductsQuantityForm = submenuService.getFactProductsQuantityForm();
		
		model.put("factProductsQuantityForm", factProductsQuantityForm);
		model.put("pageTitle", "efpq.pageTitle");
		return "editFactProductsQuantity";
	}

}
