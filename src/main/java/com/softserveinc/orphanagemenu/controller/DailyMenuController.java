package com.softserveinc.orphanagemenu.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
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
import com.softserveinc.orphanagemenu.model.ConsumptionType;
import com.softserveinc.orphanagemenu.service.DailyMenuService;

@Controller
public class DailyMenuController {


	@Autowired
	@Qualifier("dailyMenuService")
	private DailyMenuService dailyMenuService;
	
	@Autowired
	ApplicationContext context;

	@RequestMapping({ "/test2" })
	public String showTestConsole(Map<String, Object> model) {

		DateTime dateTime = new DateTime();
		DateTime dateTime2 = new DateTime(
				dateTime.getYear(),
				dateTime.getMonthOfYear(),
				dateTime.getDayOfMonth(),
				0,0,0);
		
		DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("dd.MM.yy");
		String str = dateTimeFormatter.print(dateTime);
		dateTimeFormatter = DateTimeFormat.forPattern("EEEE").withLocale(new Locale("uk"));
		str = dateTimeFormatter.print(dateTime);
		
		System.out.println(str);
		System.out.println(dateTime);
		System.out.println(dateTime2);
		
		DailyMenusPageElements dailyMenusPageElements = new DailyMenusPageElements(dateTime);
		System.out.println(dailyMenusPageElements.getCurrentDay());
		System.out.println(dailyMenusPageElements.getDayRange());
		
		System.out.println(dailyMenusPageElements);
		
		return "home";
	}
	
	
	@RequestMapping({ "/", "/dailyMenus" })
	public String showDailyMenus(@RequestParam Map<String, String> requestParams,
			Map<String, Object> model) {

		DateTime actualDateTime;
		if ("".equals(requestParams.get("actualDate"))){
			actualDateTime = new DateTime();
		} else {
			actualDateTime = new DateTime(requestParams.get("actualDate"));
		}
		DailyMenusPageElements dailyMenusPageElements = 
				new DailyMenusPageElements(actualDateTime);
		model.put("pageElements", dailyMenusPageElements);

		List<DailyMenuDto> dailyMenuDtos = dailyMenuService
				.getDailyMenuDtoForWeek(actualDateTime.toDate());
		
		model.put("dailyMenuDtos", dailyMenuDtos);
		
		List<ConsumptionType> consumptionTypes = dailyMenuService.getAllConsumptionType();
		model.put("consumptionTypes", consumptionTypes);
		
		model.put("pageTitle", "dm.pageTitle");
		return "dailyMenus";
	}
	
}
