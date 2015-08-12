package com.softserveinc.orphanagemenu.controller;

import java.io.IOException;

import java.util.HashMap;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.softserveinc.orphanagemenu.json.DishForm;
import com.softserveinc.orphanagemenu.json.DishResponseBody;
import com.softserveinc.orphanagemenu.model.AgeCategory;
import com.softserveinc.orphanagemenu.model.Component;
import com.softserveinc.orphanagemenu.model.ComponentWeight;
import com.softserveinc.orphanagemenu.model.Dish;
import com.softserveinc.orphanagemenu.model.Product;
import com.softserveinc.orphanagemenu.service.AgeCategoryService;
import com.softserveinc.orphanagemenu.service.ComponentService;
import com.softserveinc.orphanagemenu.service.DishService;
import com.softserveinc.orphanagemenu.service.ProductService;

import com.softserveinc.orphanagemenu.validators.DishValidator;



@Controller
public class DishController {
	
	@Autowired
	@Qualifier("dishService")
	private DishService dishService;
	
	@Autowired
	private AgeCategoryService ageCategoryService;
	
	@Autowired 
	private ComponentService componentService;

	@Autowired
	private ProductService productService; 
	
	@Autowired
	private DishValidator dishValidator;
	
	@Autowired
	ApplicationContext context;
	
	ModelAndView mav;
	
	@RequestMapping({ "/dishlist" })
	public String getList(Model model, Map<String,Object> mdl) {

		List<Dish> list = dishService.getAllDish();
		model.addAttribute("dishes", list);
		mdl.put("pageTitle", "Список наявних страв");
		mdl.put("action", "add");
		mdl.put("canceled", "cancel");
		mdl.put("operation", "operations");
		mdl.put("meal", "all.meals");
		mdl.put("available", "availability");
		mdl.put("edited", "edit");
		mdl.put("dishEmpt", "dishEmpty");
		return "dishlist";
	}
	
	@RequestMapping({"/addDish"})
	public String addDish(Map<String,Object> mdl){
		
		DishForm dishForm = new DishForm();
		mdl.put("validationMessages", getAllValidationMessagesAsMap());
		mdl.put("pageTitle","Додавання нової страви");
		mdl.put("dishForm", dishForm);
		mdl.put("action", "next");
		mdl.put("canceled", "cancel");
		mdl.put("newdish", "newDish");
		mdl.put("added", "addedDish");
		return "addDish";
	}
	
	@RequestMapping( value="/addcomponent", method = RequestMethod.POST)
	public ModelAndView save(Map<String, Object> mdl, DishForm dishForm, BindingResult result) throws IOException{
		
		dishForm.setDishName(dishForm.getDishName().trim());
		dishForm.setDishName(dishForm.getDishName().replaceAll("\\s+", " "));
		
		dishValidator.validate(dishForm, result);
		if(result.hasErrors()){


			mdl.put("validationMessages", getAllValidationMessagesAsMap());
			mdl.put("pageTitle","Додавання нової страви");
			mdl.put("dishForm", dishForm);
			mdl.put("action", "next");
			mdl.put("canceled", "cancel");
			mdl.put("newdish", "newDish");
			mdl.put("added", "addedDish");
			mav = new ModelAndView("addDish");
			return mav;
		}
		
		Dish dish;
		if ((dish = dishService.getDishByName(dishForm.getDishName()))==null) {
			dish = new Dish(dishForm.getDishName(), true);
			dishService.addDish(dish); 
		}
		List<AgeCategory> plist = ageCategoryService.getAllAgeCategory();
		List<Component> componentList = componentService.getAllComponentByDishId(dishService.getDishByName(dishForm.getDishName()));

		List<Product> productList = productService.getAllProductDtoSorted();
		ModelAndView mav = new ModelAndView("addcomponent");
		mav.addObject("pageTitle", "Додавання інгредієнтів");
		mav.addObject("components", componentList);
		mav.addObject("cat", plist);
		mav.addObject("dish1", dish);
		mav.addObject("products", productList);
		mdl.put("dishForm", dishForm);
		mdl.put("action", "dishList");
		mdl.put("canceled", "cancel");
		mdl.put("addComp", "addComponent");
		mdl.put("compo", "component");
		mdl.put("operation", "operations");
		mdl.put("edited", "edit");
		mdl.put("plist", "productList");
		mdl.put("compEmpty", "componentEmpty");
		mdl.put("added", "addedDish");
		return mav;
	}
	
	
	@RequestMapping( value="/addcomponents", method=RequestMethod.POST, consumes="application/json")
	public @ResponseBody ModelAndView addComp(@RequestBody DishResponseBody dishResponse,
												Map<String, Object> model, DishForm dishForm){
		
		dishForm.setDishName(dishResponse.getDishName());

		Component component = new Component();
		component.setDish(dishService.getDishByName(dishResponse.getDishName()));
		component.setProduct(productService.getProductById(dishResponse.getProductId()));
		
		Set<ComponentWeight> componentSet = new HashSet<ComponentWeight>();
		ComponentWeight componentWeight = null;
		List<AgeCategory> catList = ageCategoryService.getAllAgeCategory();
		int count = 1;
		for(AgeCategory ageCategory : catList) {
		componentWeight = new ComponentWeight();
		componentWeight.setAgeCategory(ageCategory);
		componentWeight.setComponent(component);
		
			if(count==1){
			componentWeight.setStandartWeight(dishResponse.getCategory0());
			
			}
			if(count==2){
				componentWeight.setStandartWeight(dishResponse.getCategory1());
				
			}
			if(count==3){
				componentWeight.setStandartWeight(dishResponse.getCategory2());
			
			}
			if(count==4){
				componentWeight.setStandartWeight(dishResponse.getCategory3());
			}
			count++;
		componentSet.add(componentWeight);
		}
		
		component.setComponents(componentSet);
		componentService.saveComponent(component);

		model.put("validationMessages", getAllValidationMessagesAsMap());
		mav = new ModelAndView("addcomponent");
		return mav;
	}
	
	private Map<String, String> getAllValidationMessagesAsMap() {
		Map<String, String> messages = new HashMap<>();
		messages.put(
				"fieldEmpty",
				context.getMessage("fieldEmpty", null,
						LocaleContextHolder.getLocale()));
		messages.put("productNameTooShort", context.getMessage(
				"dishNameTooShort", null, LocaleContextHolder.getLocale()));
		messages.put("productNameTooLong", context.getMessage(
				"dishNameTooLong", null, LocaleContextHolder.getLocale()));
		messages.put("productNameIllegalCharacters", context.getMessage(
				"dishNameIllegalCharacters", null,
				LocaleContextHolder.getLocale()));
		messages.put("dishNormEmpty", context.getMessage("dishNormEmpty",
				null, LocaleContextHolder.getLocale()));
		messages.put("dishNormTooShort", context.getMessage(
				"dishNormTooShort", null, LocaleContextHolder.getLocale()));
		messages.put("productNormTooLong", context.getMessage(
				"dishNormTooLong", null, LocaleContextHolder.getLocale()));
		messages.put("weightIllegalCharacters", context.getMessage(
				"weightIllegalCharacters", null,
				LocaleContextHolder.getLocale()));
		messages.put(
				"submitChanges",
				context.getMessage("submitChanges", null,
						LocaleContextHolder.getLocale()));
		messages.put("yes", context.getMessage("yes", null,
				LocaleContextHolder.getLocale()));
		messages.put("no",
				context.getMessage("no", null, LocaleContextHolder.getLocale()));
		messages.put("exitConfirmation", context.getMessage("exitConfirmation",
				null, LocaleContextHolder.getLocale()));
		return messages;
	}
	
}
