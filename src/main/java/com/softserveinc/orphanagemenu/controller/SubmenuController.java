package com.softserveinc.orphanagemenu.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.softserveinc.orphanagemenu.dao.DailyMenuDao;
import com.softserveinc.orphanagemenu.forms.FactProductsQuantityForm;
import com.softserveinc.orphanagemenu.model.AgeCategory;
import com.softserveinc.orphanagemenu.model.Dimension;
import com.softserveinc.orphanagemenu.service.AgeCategoryService;
import com.softserveinc.orphanagemenu.service.DailyMenuService;
import com.softserveinc.orphanagemenu.service.SubmenuService;
import com.softserveinc.orphanagemenu.validators.FactProductQuantityValidator;

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
		model.put("consumptionTypeId", consumptionTypeId);
		model.put("dishId", dishId);
		model.put("validationMessages", getAllValidationMessagesAsMap());
		return "editFactProductsQuantity";
	}

	@RequestMapping({ "/getStandartComponentQuantity" })
	public String returnStandartComponentQuantity(Map<String, Object> model,
			@RequestParam Map<String, String> requestParams,
			FactProductsQuantityForm factProductsQuantityForm) {
		factProductsQuantityForm = submenuService
				.getStandartComponentQuantityForm(factProductsQuantityForm);
		model.put("factProductsQuantityForm", factProductsQuantityForm);
		model.put("pageTitle", "efpq.pageTitle");
		model.put("validationMessages", getAllValidationMessagesAsMap());
		return "editFactProductsQuantity";
	}

	@RequestMapping({ "/saveFactProductQuantity" })
	public String saveFactProductQuantity(Map<String, Object> model,
			@RequestParam Map<String, String> requestParams,
			FactProductsQuantityForm factProductsQuantityForm, BindingResult result) {
		FactProductQuantityValidator.validate(factProductsQuantityForm, result);
		if (result.hasErrors()) {
			model.put("factProductsQuantityForm", factProductsQuantityForm);
			model.put("pageTitle", "efpq.pageTitle");
			model.put("validationMessages", getAllValidationMessagesAsMap());
			return "editFactProductsQuantity";
		}
		submenuService.saveFactProductQuantity(factProductsQuantityForm);
		model.put("factProductsQuantityForm", factProductsQuantityForm);
		model.put("pageTitle", "efpq.pageTitle");
		return "editFactProductsQuantity";
	}
	
	private Set<String> getAllValidationMessagesAsMap() {
		Set<String> messages = new HashSet<>();
		messages.add("fieldEmpty");
		messages.add("productNameTooShort");
		messages.add("productNameTooLong");
		messages.add("productNameIllegalCharacters");
		messages.add("productNormEmpty");
		messages.add("productNormTooShort");
		messages.add("productNormTooLong");
		messages.add("weightIllegalCharacters");
		messages.add("submitChanges");
		messages.add("yes");
		messages.add("no");
		messages.add("exitConfirmation");
		messages.add("standartComponentConfirmation");
		return messages;
	}
}
