package com.softserveinc.orphanagemenu.controller;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.softserveinc.orphanagemenu.forms.FactProductsQuantityForm;
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
		List<Submenu> submenus = submenuService.getSubmenuListByDailyMenuAndConsumptionTypeId("1", "2");
		System.out.println(submenus.toString());
//		List<AgeCategory> ageCategory = ageCategoryService.getAllAgeCategory();
		FactProductsQuantityForm factProductsQuantityForm = submenuService.getFactProductsQuantityForm(submenus);
		model.put("factProductsQuantityForm", factProductsQuantityForm);
		model.put("dailyMenuDto", submenus);
		model.put("pageTitle", "efpq.pageTitle");
		return "editFactProductsQuantity";
	}

}
