package com.softserveinc.orphanagemenu.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	private AgeCategoryService ageCategoryService;

	@RequestMapping({ "/e" })
	public String editFactComponentsQuantity(Map<String, Object> model) {
		String dailyMenuId = "1";
		String consumptionTypeId = "2";
		String dishId = "3";
		FactProductsQuantityForm factProductsQuantityForm = submenuService
				.getFactProductsQuantityForm(dailyMenuId, dishId,
						consumptionTypeId);
		model.put("factProductsQuantityForm", factProductsQuantityForm);
		model.put("pageTitle", "efpq.pageTitle");
		model.put("dailyMenuId", dailyMenuId);
		model.put("consumptionTypeId", consumptionTypeId);
		model.put("dishId", dishId);
		return "editFactProductsQuantity";
	}

	@RequestMapping({ "/getStandartComponentQuantity" })
	public String returnStandartComponentQuantity(Map<String, Object> model,
			@RequestParam Map<String, String> requestParams,
			FactProductsQuantityForm factProductsQuantityForm) {
		System.out.println(factProductsQuantityForm.getDishName());
		factProductsQuantityForm = submenuService
				.getStandartComponentQuantityForm(factProductsQuantityForm);
		model.put("factProductsQuantityForm", factProductsQuantityForm);
		model.put("pageTitle", "efpq.pageTitle");
		return "editFactProductsQuantity";
	}

	@RequestMapping({ "/saveFactProductQuantity" })
	public String saveFactProductQuantity(Map<String, Object> model,
			@RequestParam Map<String, String> requestParams,
			FactProductsQuantityForm factProductsQuantityForm) {
		System.out.println(factProductsQuantityForm.getDishName());
		submenuService.saveFactProductQuantity(factProductsQuantityForm);
		model.put("factProductsQuantityForm", factProductsQuantityForm);
		model.put("pageTitle", "efpq.pageTitle");
		return "editFactProductsQuantity";
	}
}

