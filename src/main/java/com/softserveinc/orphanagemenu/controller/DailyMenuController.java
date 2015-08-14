package com.softserveinc.orphanagemenu.controller;

import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.softserveinc.orphanagemenu.dto.DailyMenuDto;
import com.softserveinc.orphanagemenu.dto.DailyMenusPageElements;
import com.softserveinc.orphanagemenu.model.AgeCategory;
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

	@Autowired
	ApplicationContext context;

	@RequestMapping({ "/", "/dailyMenus" })
	public String showDailyMenus(@RequestParam Map<String, String> requestParams,
			Map<String, Object> model) {
		
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
		return "dailyMenus";
	}
	
	@RequestMapping({ "/e" })
	public String editFactComponentsQuantity(Map<String, Object> model) {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.set(2015, GregorianCalendar.OCTOBER, 10, 0, 0, 0);
		DailyMenuDto dailyMenu = dailyMenuService.getDailyMenuDtoForDay(calendar.getTime());
		System.out.println(dailyMenu.toString());
		List<AgeCategory> ageCategory = ageCategoryService.getAllAgeCategory();
		model.put("dailyMenuDto", dailyMenu);
		model.put("ageCategory", ageCategory);
		model.put("pageTitle", "efpq.pageTitle");
		return "editFactComponentsQuantity";
	}
}
