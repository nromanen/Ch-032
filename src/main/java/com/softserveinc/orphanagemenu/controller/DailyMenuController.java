package com.softserveinc.orphanagemenu.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.softserveinc.orphanagemenu.dto.DailyMenuDto;
import com.softserveinc.orphanagemenu.model.ConsumptionType;
import com.softserveinc.orphanagemenu.service.DailyMenuService;

@Controller
public class DailyMenuController {


	@Autowired
	@Qualifier("dailyMenuService")
	private DailyMenuService dailyMenuService;
	
	@Autowired
	ApplicationContext context;

	@RequestMapping({ "/dailyMenus" })
	public String showDailyMenus(Map<String, Object> model) {
		
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.set(2015, GregorianCalendar.OCTOBER, 9, 0, 0, 0);
		System.out.println(calendar.getTime());
		
		List<DailyMenuDto> dailyMenuDtos = new ArrayList<>();
		dailyMenuDtos.add(dailyMenuService.gDailyMenuDto(calendar.getTime()));
		calendar.set(2015, GregorianCalendar.OCTOBER, 10, 0, 0, 0);
		dailyMenuDtos.add(dailyMenuService.gDailyMenuDto(calendar.getTime()));
		
		model.put("dailyMenuDtos", dailyMenuDtos);
		
		List<ConsumptionType> consumptionTypes = dailyMenuService.getAllConsumptionType();
		model.put("consumptionTypes", consumptionTypes);
		
		model.put("pageTitle", "dm.pageTitle");
		return "dailyMenus";
	}

	
}
