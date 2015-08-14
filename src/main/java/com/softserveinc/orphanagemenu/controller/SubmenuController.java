package com.softserveinc.orphanagemenu.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.softserveinc.orphanagemenu.model.Submenu;
import com.softserveinc.orphanagemenu.service.AgeCategoryService;
import com.softserveinc.orphanagemenu.service.SubmenuService;

@Controller
public class SubmenuController {
	
	@Autowired
	private SubmenuService submenuService;
	
	@Autowired
	private AgeCategoryService  ageCategoryService;
	
	@RequestMapping({ "/e" })
	public String editFactComponentsQuantity(Map<String, Object> model) throws ParseException {
		List<Submenu> submenu = submenuService.getSubmenuListByDailyMenuAndConsumptionTypeId("1", "2");
//		List<AgeCategory> ageCategory = ageCategoryService.getAllAgeCategory();
//		FactProductsQuantityForm factProductsQuantityForm = dailyMenuService.getFactProductsQuantityForm(Submenu, ageCategory);
//		model.put("factProductsQuantityForm", factProductsQuantityForm);
//		model.put("dailyMenuDto", Submenu);
//		model.put("ageCategory", ageCategory);
//		model.put("pageTitle", "efpq.pageTitle");
		return "editFactProductsQuantity";
	}

}
