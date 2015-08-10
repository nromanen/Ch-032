package com.softserveinc.orphanagemenu.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;





import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.softserveinc.orphanagemenu.json.DishForm;
import com.softserveinc.orphanagemenu.json.DishResponseBody;
import com.softserveinc.orphanagemenu.model.AgeCategory;
import com.softserveinc.orphanagemenu.model.Component;
import com.softserveinc.orphanagemenu.model.ComponentWeight;
import com.softserveinc.orphanagemenu.model.Dish;
import com.softserveinc.orphanagemenu.model.Product;
import com.softserveinc.orphanagemenu.service.AgeCategoryService;
import com.softserveinc.orphanagemenu.service.ComponentService;
import com.softserveinc.orphanagemenu.service.ComponentWeightService;
import com.softserveinc.orphanagemenu.service.DishService;
import com.softserveinc.orphanagemenu.service.ProductService;


@Controller
public class DishController {
	

	@Autowired
	private DishService dishService;
	
	@Autowired
	private AgeCategoryService ageCategoryService;
	
	@Autowired 
	private ComponentService componentService;

	@Autowired
	private ProductService productService; 
	
	@Autowired
	private ComponentWeightService componentWeightService;
	
	@RequestMapping({ "/dishlist" })
	public String getList(Model model, Map<String,Object> mdl) {

		ArrayList<Dish> list = dishService.getAllDish();
		model.addAttribute("dishes", list);
		mdl.put("pageTitle", "Список наявних страв");
		mdl.put("action", "add");
		return "dishlist";
	}
	
	@RequestMapping({"/addDish"})
	public String addDish(Map<String,Object> mdl){
		
		DishForm dishForm = new DishForm();
		
		
		mdl.put("pageTitle","Додавання нової страви");
		mdl.put("dishForm", dishForm);
		mdl.put("next", "next");
		return "addDish";
	}
	
	
	
	
	@RequestMapping( value="/addcomponent", method = RequestMethod.POST)
	public ModelAndView save(final RedirectAttributes redirectAttributes, @RequestParam Map<String, String> requestParams,
							Map<String, Object> mdl, DishForm dishForm, BindingResult result) throws IOException{
		

		Dish dish;
		if (dishService.checkIfDishExist(dishForm.getDishName())) {
			dish = dishService.getDishByName(dishForm.getDishName());
		}
		else {
			dish = new Dish(dishForm.getDishName(), true);
			dishService.addDish(dish); 
		}
		ArrayList<AgeCategory> plist = ageCategoryService.getAllAgeCategory();
		ArrayList<Component> componentList = componentService.getAllComponentByDishId(dishService.getDishByName(dishForm.getDishName()));
		List<Product> productList = productService.getAllProduct();
		List<ComponentWeight> componentWeight = componentWeightService.getAllComponentWeight();
		ModelAndView mav = new ModelAndView("addcomponent");
		mav.addObject("componentWeight", componentWeight);
		mav.addObject("pageTitle", "Додавання інгредієнтів");
		mav.addObject("components", componentList);
		mav.addObject("cat", plist);
		mav.addObject("dish1", dish);
		mav.addObject("products", productList);
		mdl.put("dishForm", dishForm);
		return mav;

	}
	
	
	@RequestMapping( value="/addcomponents", method=RequestMethod.POST, consumes="application/json")
	public @ResponseBody ModelAndView addComp(@RequestBody DishResponseBody dishResponse,
												final RedirectAttributes redirectAttributes,
												@RequestParam Map<String, String> requestParams,
												Map<String, Object> model, DishForm dishForm, BindingResult result ){
		
		
		
		dishForm.setDishName(dishResponse.getDishName());
		Component component = new Component();
		component.setDish(dishService.getDishByName(dishForm.getDishName()));
		component.setProduct(productService.getProductById(dishResponse.getProductId()));
		
		Set<ComponentWeight> componentSet = new HashSet<ComponentWeight>();
		ComponentWeight componentWeight = null;
		ArrayList<AgeCategory> catList = ageCategoryService.getAllAgeCategory();
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
		
		ModelAndView mav = new ModelAndView("addcomponent");
		return mav;
	}
	
	
}
