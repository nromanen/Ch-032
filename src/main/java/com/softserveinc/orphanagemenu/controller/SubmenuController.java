package com.softserveinc.orphanagemenu.controller;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.softserveinc.orphanagemenu.dao.ConsumptionTypeDao;
import com.softserveinc.orphanagemenu.forms.FactProductsQuantityForm;
import com.softserveinc.orphanagemenu.model.ConsumptionType;
import com.softserveinc.orphanagemenu.service.AgeCategoryService;
import com.softserveinc.orphanagemenu.service.DailyMenuService;
import com.softserveinc.orphanagemenu.service.SubmenuService;
import com.softserveinc.orphanagemenu.validators.FactProductQuantityValidator;

@Controller
public class SubmenuController {

	@Autowired
	private SubmenuService submenuService;
	
	@Autowired
	private ConsumptionTypeDao consumptionTypeDao;

	@Autowired
	private DailyMenuService dailyMenuService;

	@Autowired
	private AgeCategoryService ageCategoryService;

	@Autowired
	private FactProductQuantityValidator factProductQuantityValidator;

//	@RequestMapping({ "/editFactProductsQuantity/{dailyMenuId}/{consumptionTypeId}/{dishId}" })
//	public String editFactComponentsQuantity(Map<String, Object> model,
//			@PathVariable String dailyMenuId,
//			@PathVariable String consumptionTypeId, @PathVariable String dishId) {

		 @RequestMapping({ "/editFactProductsQuantity" })
		 public String editFactProductssQuantity(Map<String, Object> model) {
		 String dailyMenuId = "1";
		 String consumptionTypeId = "2";
		 String dishId = "3";
		FactProductsQuantityForm factProductsQuantityForm = submenuService
				.getFactProductsQuantityForm(dailyMenuId, dishId,
						consumptionTypeId);
		model.put("factProductsQuantityForm", factProductsQuantityForm);
		model.put("pageTitle", "efpq.pageTitle");
		model.put("validationMessages", getAllValidationMessagesAsMap());
		return "editFactProductsQuantity";
	}

	@RequestMapping({ "/getStandartComponentQuantity" })
	public String returnStandartComponentQuantity(Map<String, Object> model,
			@RequestParam Map<String, String> requestParams,
			FactProductsQuantityForm factProductsQuantityForm) {
		FactProductsQuantityForm standartComponentsQuantityForm = new FactProductsQuantityForm();
		standartComponentsQuantityForm = submenuService
				.getStandartComponentQuantityForm(factProductsQuantityForm);
		model.put("factProductsQuantityForm", standartComponentsQuantityForm);
		model.put("pageTitle", "efpq.pageTitle");
		model.put("validationMessages", getAllValidationMessagesAsMap());
		return "editFactProductsQuantity";
	}

	@RequestMapping({ "/submenuEdit" })
	public ModelAndView showSubmenuEdit(
			@RequestParam(value = "id", defaultValue = "1l") Long id,
			@RequestParam(value = "consumptionType", defaultValue = "1l") Long ct) {
		ModelAndView modelAndView = new ModelAndView("submenuEdit");
//		submenuService.addDishToSubmenuList(2l, 1l, 20l);
			
		modelAndView.addObject("SubmenuDto", submenuService.getSubmenuDto(id, ct));
		modelAndView.addObject("dailyMenuId", id);
		modelAndView.addObject("consumptionTypeId", ct);
		modelAndView.addObject("sortedCats", ageCategoryService.getAllAgeCategory());
		modelAndView.addObject("pageTitle", "edit");
		modelAndView.addObject("pageTitle2", consumptionTypeDao.getById(ct).getName().toLowerCase());
		return modelAndView;
	}
	
//	@RequestMapping({ "/submenuEditAddDish" })
//	public ModelAndView addDishToSubmenu(
//			@RequestParam(value = "dailyMenuId", defaultValue = "1l") Long id,
//			@RequestParam(value = "consumptionType", defaultValue = "1l") Long ct,
//			@RequestParam(value = "dishId", defaultValue = "1l") Long dishId) {
//	
//		submenuService.addDishToSubmenuList(id, ct, dishId);
//		ModelAndView modelAndView = new ModelAndView("submenuEdit");
//		modelAndView.addObject("SubmenuDto", submenuService.getSubmenuDto(id, ct));
//		modelAndView.addObject("dailyMenuId", id);
//		modelAndView.addObject("consumptionTypeId", ct);
//		modelAndView.addObject("sortedCats", ageCategoryService.getAllAgeCategory());
//		modelAndView.addObject("pageTitle", "edit");
//		modelAndView.addObject("pageTitle2", consumptionTypeDao.getById(ct).getName().toLowerCase());
//		return modelAndView;
//	}

	@RequestMapping({ "/saveFactProductQuantity" })
	public String saveFactProductQuantity(Map<String, Object> model,
			FactProductsQuantityForm factProductsQuantityForm,
			BindingResult result) {
		factProductQuantityValidator.validate(factProductsQuantityForm, result);
		if (result.hasErrors()) {
			model.put("factProductsQuantityForm", factProductsQuantityForm);
			model.put("pageTitle", "efpq.pageTitle");
			model.put("validationMessages", getAllValidationMessagesAsMap());
			return "editFactProductsQuantity";
		}
		submenuService.saveFactProductQuantity(factProductsQuantityForm);
		return "redirect:/aa";
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
