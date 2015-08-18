package com.softserveinc.orphanagemenu.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.softserveinc.orphanagemenu.dto.DailyMenuDto;
import com.softserveinc.orphanagemenu.dto.DailyMenusPageElements;
import com.softserveinc.orphanagemenu.model.ConsumptionType;
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
	public String showDailyMenus(@RequestParam Map<String, String> requestParams,
			Map<String, Object> model) {
		
		if (requestParams.containsKey("id")){
			// TODO implement invocation of delete operation
			System.out.println("-------delete daily menu with id: " + requestParams.get("id"));
		}
		
		DateTime actualDateTime;

		if (requestParams.get("actualDate") == null || "".equals(requestParams.get("actualDate"))){
			actualDateTime = new DateTime();
		} else {
			DateTimeFormatter formatter = DateTimeFormat.forPattern("dd.MM.yy");
			actualDateTime = formatter.parseDateTime(requestParams.get("actualDate"));
		}
		DailyMenusPageElements dailyMenusPageElements = 
				new DailyMenusPageElements(actualDateTime);
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
	

	@RequestMapping (value="editMenu")
	public String editMenu (Map<String, Object> model)
	{
		return "editMenu";
	}
	
	public Set<String> getInterfaceMessages(){
		Set<String> messages = new HashSet<>();

		messages.add("yes");
		messages.add("no");
		messages.add("goNextConfirmation");
		return messages;
	}
	
}