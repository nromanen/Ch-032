package com.softserveinc.orphanagemenu.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.softserveinc.orphanagemenu.dto.DailyMenuDto;
import com.softserveinc.orphanagemenu.dto.DailyMenusPageElements;
import com.softserveinc.orphanagemenu.forms.SelectForm;
import com.softserveinc.orphanagemenu.dto.ProductNorms;
import com.softserveinc.orphanagemenu.model.ConsumptionType;
import com.softserveinc.orphanagemenu.model.DailyMenu;
import com.softserveinc.orphanagemenu.service.AgeCategoryService;
import com.softserveinc.orphanagemenu.service.DailyMenuService;
import com.softserveinc.orphanagemenu.service.ProductService;

@Controller
public class DailyMenuController {

	@Autowired
	@Qualifier("dailyMenuService")
	private DailyMenuService dailyMenuService;

	@Autowired
	private AgeCategoryService ageCategoryService;

	@Autowired
	private ProductService productService;
	
	
	@RequestMapping({ "/", "/dailyMenus", "/dailyMenuDelete" })
	public String showDailyMenus(
			@RequestParam Map<String, String> requestParams,
			Map<String, Object> model, SelectForm selectForm, BindingResult result) {

		if (requestParams.containsKey("id")) {
			// TODO implement invocation of delete operation
			System.out.println("-------delete daily menu with id: "
					+ requestParams.get("id"));
		}

		DateTime actualDateTime;

		if (requestParams.get("actualDate") == null
				|| "".equals(requestParams.get("actualDate"))) {

			actualDateTime = new DateTime();
		} else {
			DateTimeFormatter formatter = DateTimeFormat.forPattern("dd.MM.yy");
			actualDateTime = formatter.parseDateTime(requestParams
					.get("actualDate"));
		}
		DailyMenusPageElements dailyMenusPageElements = new DailyMenusPageElements(
				actualDateTime);
		model.put("pageElements", dailyMenusPageElements);

		List<DailyMenuDto> dailyMenuDtos = dailyMenuService
				.getDailyMenuDtoForWeek(actualDateTime.toDate());

		model.put("dailyMenuDtos", dailyMenuDtos);

		List<ConsumptionType> consumptionTypes = dailyMenuService
				.getAllConsumptionType();
		model.put("consumptionTypes", consumptionTypes);
		model.put("pageTitle", "dm.pageTitle");
		model.put("validationMessages", getInterfaceMessages());

		return "dailyMenus";
	}

	@RequestMapping(value = "/redirect")
	   public String redirect(SelectForm selectForm, BindingResult result) {
		
		Long dailyMenuIde = Long.parseLong(selectForm.getId());
		DailyMenu daily = dailyMenuService.getById(dailyMenuIde);
		Boolean accept = Boolean.parseBoolean(selectForm.getAccepted());
		daily.setAccepted(accept);
		dailyMenuService.updateDailyMenu(daily);
		String redirectDate = selectForm.getDate();
	    return "redirect:dailyMenus?actualDate="+redirectDate;
	}
	   
	@RequestMapping (value="editMenu")
	public String editMenu (Map<String, Object> model)
	{
		return "editMenu";
	}
	
	@RequestMapping(value = "/dailyMenuUpdate")
	public String editDailyMenu(Map<String, Object> model,
			@RequestParam("id") String id , Model mdl, SelectForm selectForm, BindingResult result) {
		
		// DIMA PART 
		
		Long menuId = Long.parseLong(id);
		Date date = dailyMenuService.getDateById(menuId);
		DailyMenuDto dailyMenuDto = dailyMenuService.getDailyMenuDtoForDay(date);
		List<DailyMenuDto> dailyMenu = new ArrayList<DailyMenuDto>();
		Boolean acceptMenu = dailyMenuService.getDailyMenuAccepted(menuId);
		
		dailyMenu.add(dailyMenuDto);
		model.put("acceptMenu", acceptMenu);
		model.put("selectForm", selectForm);
		model.put("dailyMenu", dailyMenu);
		model.put("pageTitle", "dm.edit");
		model.put("action", "save");
		model.put("canceled", "cancel");

		// ANDRE PART
		
		List<ConsumptionType> consumptionTypes = dailyMenuService
				.getAllConsumptionType();
		
		Long i_dd = Long.parseLong(id);
		
		
		model.put("ageCategoryList", ageCategoryService.getAllAgeCategory());
		List<ProductNorms> prodNormList = dailyMenuService
				.getProductWithStandartAndFactQuantityList(i_dd);

		model.put("norms", prodNormList);
		model.put("percent", 10);
		

		model.put("consumptionTypes", consumptionTypes);
		model.put("pageTitle", "dm.edit");
		model.put("action", "save");
		model.put("canceled", "cancel");


//		dailyMenuService.getAllProductsWithQuantitiesForDailyMenu(Long.parseLong(requestParams.get("id")));

		return "dailyMenuUpdate";
	}

	public Set<String> getInterfaceMessages() {
		Set<String> messages = new HashSet<>();

		messages.add("yes");
		messages.add("no");
		messages.add("goNextConfirmation");
		return messages;
	}

}
