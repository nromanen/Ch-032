package com.softserveinc.orphanagemenu.controller;


import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.softserveinc.orphanagemenu.dao.DailyMenuDao;
import com.softserveinc.orphanagemenu.dao.SubmenuDao;
import com.softserveinc.orphanagemenu.model.AgeCategory;
import com.softserveinc.orphanagemenu.model.DailyMenu;
import com.softserveinc.orphanagemenu.model.Dish;
import com.softserveinc.orphanagemenu.model.Submenu;

@Controller
public class HomeController {

	@Autowired
	@Qualifier("dailyMenuDao")
	private DailyMenuDao dailyMenuDao;
	
	@Autowired
	@Qualifier("submenuDao")
	private SubmenuDao submenuDao;
	
	@RequestMapping({ "/" })
	public String showMainPage() {
		return "home";
	}

	@RequestMapping({"/home" })
	public String showHomePage() {
		return "home";
	}

	@RequestMapping({ "/login" })
	public String showLoginPage(@RequestParam Map<String, String> requestParams, Map<String, Object> model) {
		if (requestParams.get("error") != null){
			model.put("error", "error");
		}
		return "login";
	}
	
	@RequestMapping({"/dm" })
	public String showDM() {
		
		AgeCategory ac1 = new AgeCategory();
		ac1.setId(1L);
		AgeCategory ac2 = new AgeCategory();
		ac2.setId(2L);
		Dish d1 = new Dish();
		d1.setDishId(1L);
		Dish d2 = new Dish();
		d2.setDishId(2L);
		
		
		Submenu s1 = new Submenu();
		s1.setAgeCategory(ac1);
		s1.setChildQuantity(2);
		Submenu s2 = new Submenu();
		s2.setAgeCategory(ac2);
		s2.setChildQuantity(4);
		DailyMenu dm = new DailyMenu();
		dm.setIsAccepted(true);
		dm.setDate(new GregorianCalendar().getTime());
		s1.setDailyMenu(dm);
		s2.setDailyMenu(dm);
//		submenuDao.save(s1);
		
		
		Set<Dish> dishes = new HashSet<>();
//		dishes.add(d1);
		dishes.add(d2);
		s1.setDishes(dishes);
		s2.setDishes(dishes);
		
		Set<Submenu> submenus = new HashSet<>();
		submenus.add(s1);
		submenus.add(s2);
		dm.setSubmenus(submenus);
		dailyMenuDao.save(dm);
		
		return "home";
	}
	
}