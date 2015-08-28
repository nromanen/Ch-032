package com.softserveinc.orphanagemenu.controller;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.i18n.LocaleContextHolder;
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

import com.softserveinc.orphanagemenu.forms.ProductForm;
import com.softserveinc.orphanagemenu.forms.DishForm;
import com.softserveinc.orphanagemenu.json.DishResponseBody;
import com.softserveinc.orphanagemenu.model.AgeCategory;
import com.softserveinc.orphanagemenu.model.Component;
import com.softserveinc.orphanagemenu.model.ComponentWeight;
import com.softserveinc.orphanagemenu.model.Dimension;
import com.softserveinc.orphanagemenu.model.Dish;
import com.softserveinc.orphanagemenu.model.Product;
import com.softserveinc.orphanagemenu.service.AgeCategoryService;
import com.softserveinc.orphanagemenu.service.ComponentService;
import com.softserveinc.orphanagemenu.service.DishService;
import com.softserveinc.orphanagemenu.service.ProductService;
import com.softserveinc.orphanagemenu.validators.DishValidator;
import com.softserveinc.orphanagemenu.validators.ProductValidator;


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
	private DishValidator dishValidator;

	@Autowired
	ApplicationContext context; 
	
	int count = 0;
	
	@RequestMapping({"/addDish"})
	public String addDish(Map<String,Object> mdl){
		
		DishForm dishForm = new DishForm();
		mdl.put("validationMessages", getAllValidationMessagesAsMap());
		mdl.put("pageTitle","addNewDish");
		mdl.put("dishForm", dishForm);
		mdl.put("action", "next");
		mdl.put("canceled", "cancel");
		mdl.put("newdish", "newDish");
		mdl.put("added", "addedDish");
		return "addDish";
	}
	
	@RequestMapping({ "/dishlist", "/dishAvailable" })
	public String getList(Model model, Map<String,Object> mdl) {

		
		List<Dish> list = dishService.getAllDish();
		model.addAttribute("dishes", list);
		mdl.put("pageTitle", "dishList2");
		mdl.put("action", "add");
		mdl.put("canceled", "cancel");
		mdl.put("operation", "operations");
		mdl.put("meal", "all.meals");
		mdl.put("available", "availability");
		mdl.put("edited", "edit");
		mdl.put("dishEmpt", "dishEmpty");
		count=0;
		return "dishlist";
	}
	
	@RequestMapping( value="/addcomponent", method = RequestMethod.POST)
	public ModelAndView save(Map<String, Object> mdl, DishForm dishForm, BindingResult result) {
		
		dishForm.setDishName(dishForm.getDishName().trim());
		dishForm.setDishName(dishForm.getDishName().replaceAll("\\s+", " "));
		
		if(count>0){}
		else{
		dishValidator.validate(dishForm, result);
		if(result.hasErrors()){

			mdl.put("validationMessages", getAllValidationMessagesAsMap());
			mdl.put("pageTitle","addNewDish");
			mdl.put("dishForm", dishForm);
			mdl.put("action", "next");
			mdl.put("canceled", "cancel");
			mdl.put("newdish", "newDish");
			mdl.put("added", "addedDish");
			
			return new ModelAndView("addDish");
			
		}
		}
		count++;
		Dish dish;
		if ((dish = dishService.getDish(dishForm.getDishName()))==null) {
			dish = new Dish(dishForm.getDishName(), true);
			dishService.addDish(dish); 
		}
		List<AgeCategory> plist = ageCategoryService.getAllAgeCategory();
//<<<<<<< HEAD
//		List<Component> componentList = componentService.getAllComponentByDishId(dishService.getDishByName(dishForm.getDishName()));
//		List<Product> productList = productService.getAllProduct();
//				for(Component comp:componentList){
//			productList.remove(comp.getProduct());			
//		}
//=======
		List<Component> componentList = componentService.getAllComponentByDishId(dishService.getDish(dishForm.getDishName()));

		List<Product> productList = productService.getAllProductDtoSorted();
			for(Component comp:componentList){
				productList.remove(comp.getProduct());
			}
		ModelAndView mav = new ModelAndView("addcomponent");
		mav.addObject("pageTitle", "addIngradients");
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
		component.setDish(dishService.getDish(dishResponse.getDishName()));
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
		return new ModelAndView("addcomponent");
	}
	
	@RequestMapping( value="/editDish", method = RequestMethod.GET)
	public ModelAndView edit(final RedirectAttributes redirectAttributes, @RequestParam Map<String, String> requestParams, 
							Map<String, Object> mdl, DishForm dishForm, BindingResult result) throws IOException{

		Dish dish = new Dish(requestParams.get("dishName"), true);
		dishForm.setId(dishService.getDish(requestParams.get("dishName")).getId());
		ArrayList<Component> componentList = (ArrayList<Component>) componentService.getAllComponentByDishId(dishService.getDish(dish.getName()));
		List<Product> productList = productService.getAllProductDtoSorted();
		for(Component comp:componentList){
			productList.remove(comp.getProduct());			
		}
		List<AgeCategory> plist = ageCategoryService.getAllAgeCategorySortById();
		
		ModelAndView mav = new ModelAndView("editDish");
		mav.addObject("pageTitle", "Редагування інгредієнтів");
		mav.addObject("components", componentList);
		mav.addObject("cat", plist);
		mav.addObject("dish", dish);
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
	@RequestMapping( value="/editModal", method = RequestMethod.GET)
	public ModelAndView editModal(final RedirectAttributes redirectAttributes, @RequestParam Map<String, String> requestParams,  DishForm dishForm,Map<String, Object> model,
							Map<String, Object> mdl ) throws IOException{
		Component comp1=componentService.getComponentById(1L);
		ArrayList<Component> componentList = (ArrayList<Component>) componentService.getAllComponentByDishId(dishService.getDish(requestParams.get("dishName")));
		if (requestParams.get("compId")!=null){
			 comp1 =  componentService.getComponentById((Long.parseLong(requestParams.get("compId"))));
			}
		
		List<Product> productList = productService.getAllProductDtoSorted();
		for(Component comp:componentList){
			productList.remove(comp.getProduct());			
		}
		List<AgeCategory> plist = ageCategoryService.getAllAgeCategorySortById();
		ModelAndView mav = new ModelAndView("editModal");
		mdl.put("dishForm", dishForm);
		mav.addObject("pageTitle", "Редагування інгредієнтів");
		mav.addObject("comp", comp1);
		mav.addObject("cat", plist);
		model.put("validationMessages", getAllValidationMessagesAsMap());
		mav.addObject("products", productList);
		return mav;
		}
	
	
		@RequestMapping(value = "/updateDish")
		public String updateDish(final RedirectAttributes redirectAttributes,Map<String, Object> model,
				@RequestParam Map<String, String> requestParams,
				 DishForm dishForm,
				BindingResult result) {
			model.put("validationMessages", getAllValidationMessagesAsMap());
			System.out.println(dishForm.getComp_id());
			if (dishForm.getComp_id().equals("false")==false){
				System.out.println(dishForm.getComp_id());
			dishForm.setDishName(dishForm.getDishName().trim());
			dishForm.setDishName(dishForm.getDishName().replaceAll("\\s+", " "));
			
			
			for (Map.Entry<Long, Double> weight : dishForm.getWeight()
					.entrySet()) {
				weight.setValue(Double.parseDouble(Double.toString(weight.getValue()).replace(",", ".")));
				weight.setValue(Double.parseDouble((new DecimalFormat(
						"#.##").format((weight.getValue())))));
			

					
				Component comp = componentService.updateComponentWeightByDishForm(dishForm);
				componentService.updateComponent(comp);
				redirectAttributes.addFlashAttribute("infoMessage",
						"updateDishSuccessful");
				}

			redirectAttributes.addAttribute("dishName", dishForm.getDishName());

			return "redirect:/editDish";
			}
			
			else{
				System.out.println(dishForm.getDishName());
				dishValidator.validate(dishForm, result);
				if (result.hasErrors()) {

					model.put("pageTitle", "Список наявних страв");
					model.put("action", "add");
					model.put("canceled", "cancel");
					model.put("operation", "operations");
					model.put("meal", "all.meals");
					model.put("available", "availability");
					model.put("edited", "edit");
					model.put("dishEmpt", "dishEmpty");
					model.put("validationMessages", getAllValidationMessagesAsMap());

					return "dishlist";
				}
				System.out.println("good");
				Dish dish=dishService.getDishById(dishForm.getId());
				dish.setName(dishForm.getDishName());
				dishService.updateDish(dish);
				redirectAttributes.addFlashAttribute("infoMessage",
						"updateProductSuccessful");
				return "redirect:/dishlist";
				
				
			}
			
			
}
//		private Map<String, String> getAllValidationMessagesAsMap() {
//			Map<String, String> messages = new HashMap<>();
//			messages.put(
//					"fieldEmpty",
//					context.getMessage("fieldEmpty", null,
//							LocaleContextHolder.getLocale()));
//			messages.put("productNameTooShort", context.getMessage(
//					"productNameTooShort", null, LocaleContextHolder.getLocale()));
//			messages.put("productNameTooLong", context.getMessage(
//					"productNameTooLong", null, LocaleContextHolder.getLocale()));
//			messages.put("productNameIllegalCharacters", context.getMessage(
//					"productNameIllegalCharacters", null,
//					LocaleContextHolder.getLocale()));
//			messages.put("productNormEmpty", context.getMessage("productNormEmpty",
//					null, LocaleContextHolder.getLocale()));
//			messages.put("productNormTooShort", context.getMessage(
//					"productNormTooShort", null, LocaleContextHolder.getLocale()));
//			messages.put("productNormTooLong", context.getMessage(
//					"productNormTooLong", null, LocaleContextHolder.getLocale()));
//			messages.put("weightIllegalCharacters", context.getMessage(
//					"weightIllegalCharacters", null,
//					LocaleContextHolder.getLocale()));
//			messages.put(
//					"submitChanges",
//					context.getMessage("submitChanges", null,
//							LocaleContextHolder.getLocale()));
//			messages.put("yes", context.getMessage("yes", null,
//					LocaleContextHolder.getLocale()));
//			messages.put("no",
//					context.getMessage("no", null, LocaleContextHolder.getLocale()));
//			messages.put("exitConfirmation", context.getMessage("exitConfirmation",
//					null, LocaleContextHolder.getLocale()));
//			return messages;
//		}
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