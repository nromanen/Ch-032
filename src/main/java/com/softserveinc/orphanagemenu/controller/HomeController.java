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
import com.softserveinc.orphanagemenu.model.ComponentWeight;
import com.softserveinc.orphanagemenu.model.ConsumptionType;
import com.softserveinc.orphanagemenu.model.DailyMenu;
import com.softserveinc.orphanagemenu.model.Dish;
import com.softserveinc.orphanagemenu.model.FactProductQuantity;
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
		d1.setDishName("borsch");
		Dish d2 = new Dish();
		d2.setDishId(2L);
		d2.setDishName("tea");
		ConsumptionType ct1 = new ConsumptionType();
		ct1.setId(1L);
		ConsumptionType ct2 = new ConsumptionType();
		ct2.setId(2L);

		
		Submenu s1 = new Submenu();
		s1.setAgeCategory(ac1);
		s1.setChildQuantity(2);
		s1.setConsumptionType(ct1);
		Submenu s2 = new Submenu();
		s2.setAgeCategory(ac2);
		s2.setChildQuantity(4);
		s2.setConsumptionType(ct2);
		DailyMenu dm = new DailyMenu();
		dm.setIsAccepted(true);
		dm.setDate(new GregorianCalendar().getTime());
		s1.setDailyMenu(dm);
		s2.setDailyMenu(dm);
		
		
		Set<Dish> dishes = new HashSet<Dish>();
		dishes.add(d1);
		dishes.add(d2);
		s1.setDishes(dishes);
		s2.setDishes(dishes);

		FactProductQuantity fpq1 = new FactProductQuantity();
		fpq1.setFactProductQuantity(200D);
		fpq1.setSubmenu(s1);
		ComponentWeight componentWeight1 = new ComponentWeight();
		componentWeight1.setId(1L);
		fpq1.setComponentWeight(componentWeight1);
		
		FactProductQuantity fpq2 = new FactProductQuantity();
		fpq2.setFactProductQuantity(300D);
		fpq2.setSubmenu(s1);
		ComponentWeight componentWeight2 = new ComponentWeight();
		componentWeight2.setId(2L);
		fpq2.setComponentWeight(componentWeight2);

		
		FactProductQuantity fpq3 = new FactProductQuantity();
		fpq3.setFactProductQuantity(20D);
		fpq3.setSubmenu(s2);
		ComponentWeight componentWeight3 = new ComponentWeight();
		componentWeight3.setId(1L);
		fpq3.setComponentWeight(componentWeight3);
		
		
		FactProductQuantity fpq4 = new FactProductQuantity();
		fpq4.setFactProductQuantity(30D);
		fpq4.setSubmenu(s2);
		ComponentWeight componentWeight4 = new ComponentWeight();
		componentWeight4.setId(2L);
		fpq4.setComponentWeight(componentWeight4);
		
		Set<FactProductQuantity> fpqSet = new HashSet<>();
		fpqSet.add(fpq1);
		fpqSet.add(fpq2);

		
		Set<FactProductQuantity> fpqSet2 = new HashSet<>();
		fpqSet2.add(fpq3);
		fpqSet2.add(fpq4);
		
		s1.setFactProductQuantities(fpqSet);
		s2.setFactProductQuantities(fpqSet2);
		

		
		
		System.out.println("--------------------------------------"+s2.getFactProductQuantities());
		
		Set<Submenu> submenus = new HashSet<>();
		submenus.add(s1);
		submenus.add(s2);
		dm.setSubmenus(submenus);
		System.out.println("++++++++++++++++++++++++++++++++++++"+dm.getSubmenus());
		dailyMenuDao.save(dm);
		
		return "home";
	}
	
	@RequestMapping({ "/dm2" })
	public String dm2(@RequestParam Map<String, String> requestParams, Map<String, Object> model) {
		dailyMenuDao.print();
		return "home";
	}
	
}