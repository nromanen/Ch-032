package com.softserveinc.orphanagemenu.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.softserveinc.orphanagemenu.dao.DailyMenuDao;
import com.softserveinc.orphanagemenu.dto.DailyMenuDto;
import com.softserveinc.orphanagemenu.dto.DailyMenusPageElements;
import com.softserveinc.orphanagemenu.forms.FactProductsQuantityForm;
import com.softserveinc.orphanagemenu.forms.SelectForm;
import com.softserveinc.orphanagemenu.model.ConsumptionType;
import com.softserveinc.orphanagemenu.model.Submenu;
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
	
	@RequestMapping(value="/dailyMenuUpdate")
	public String editDailyMenu(Map<String,Object> model, @RequestParam Map<String, String> requestParams, Model mdl, SelectForm selectForm, BindingResult result) {
		
		DateTime actualDateTime;
		DateTimeFormatter formatter = DateTimeFormat.forPattern("dd.MM.yy");
		actualDateTime = formatter.parseDateTime(requestParams.get("actualDate"));
		DailyMenuDto dailyMenuDto = dailyMenuService.getDailyMenuDtoForDay(actualDateTime.toDate());
		List<DailyMenuDto> dailyMenu = new ArrayList<DailyMenuDto>();
		List<String> acceptedList = new ArrayList<String>();
		acceptedList.add("True");
		acceptedList.add("false");
		dailyMenu.add(dailyMenuDto);
		
		model.put("selectForm", selectForm);
		model.put("acceptedList", acceptedList);
		model.put("date", actualDateTime.toDate());
		model.put("dailyMenu", dailyMenu);
		model.put("pageTitle", "dm.edit");
		model.put("action", "save");
		model.put("canceled", "cancel");
		System.out.println(selectForm.getAccepted());
		return "dailyMenuUpdate";
	}

}
