package com.softserveinc.orphanagemenu.controller;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.softserveinc.orphanagemenu.forms.DishForm;
import com.softserveinc.orphanagemenu.json.DishNameJson;
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
@SessionAttributes(types = DishForm.class)
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

	@RequestMapping({ "/dishlist", "/dishAvailable" })
	public String getList(Model model, Map<String, Object> mdl,
			DishForm dishForm) {

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
		return "dishlist";
	}

	@RequestMapping(value = "/addcomponent", method = RequestMethod.GET)
	public String saveDish(DishForm dishForm, Map<String, Object> mdl) {

		Dish dish;
		if ((dish = dishService.getDish(dishForm.getDishName())) == null) {
			dish = new Dish(dishForm.getDishName(), true);
			dishService.addDish(dish);
		}



		List<Component> componentList = componentService.getAllComponentByDishId(dishService.getDish(dishForm.getDishName()));


		List<AgeCategory> categoryList = ageCategoryService.getAllAgeCategory();


		// delete used component from product list
		List<Product> productList = productService.getAllProductDtoSorted();
		for (Component comp : componentList) {
			productList.remove(comp.getProduct());
		}

		mdl.put("pageTitle", "addComponent");
		mdl.put("components", componentList);
		mdl.put("category", categoryList);
		mdl.put("products", productList);
		mdl.put("dishForm", dishForm);
		return "addcomponent";
	}

	@RequestMapping(value = "/addcomponent", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody String createDishNameFromJson(
			@RequestBody DishNameJson dishNameJson, Map<String, Object> mdl) {

		DishForm dishForm = new DishForm();
		dishForm.setDishName(dishNameJson.getDishName());
		mdl.put("dishForm", dishForm);
		return "addcomponent";
	}

	@RequestMapping(value = "/addcomponents", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody ModelAndView addComp(
			@RequestBody DishResponseBody dishResponse,
			Map<String, Object> model, DishForm dishForm) {

		dishForm.setDishName(dishResponse.getDishName());
		Component component = new Component();
		component.setDish(dishService.getDish(dishResponse.getDishName()));
		component.setProduct(productService.getProductById(dishResponse
				.getProductId()));

		Set<ComponentWeight> componentSet = new HashSet<ComponentWeight>();
		ComponentWeight componentWeight = null;
		List<AgeCategory> catList = ageCategoryService.getAllAgeCategory();
		int count = 1;
		for (AgeCategory ageCategory : catList) {
			componentWeight = new ComponentWeight();
			componentWeight.setAgeCategory(ageCategory);
			componentWeight.setComponent(component);

			switch (count) {
			case 1:
				componentWeight.setStandartWeight(dishResponse.getCategory0());
				break;
			case 2:
				componentWeight.setStandartWeight(dishResponse.getCategory1());
				break;
			case 3:
				componentWeight.setStandartWeight(dishResponse.getCategory2());
				break;
			case 4:
				componentWeight.setStandartWeight(dishResponse.getCategory3());
				break;
			}
			count++;
			componentSet.add(componentWeight);
		}
		
		component.setComponents(componentSet);
		componentService.saveComponent(component);

		model.put("validationMessages", getAllValidationMessagesAsMap());
		return new ModelAndView("addcomponent");
	}

	
	
	
	//VLAD PART
	
	
	@RequestMapping(value = "/editDish", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam Map<String, String> requestParams,
			Map<String, Object> mdl, DishForm dishForm)
			throws IOException {

		Dish dish = new Dish(requestParams.get("dishName"), true);
		dishForm.setId(dishService.getDish(requestParams.get("dishName"))
				.getId());
		
		ArrayList<Component> componentList = (ArrayList<Component>) componentService
				.getAllComponentByDishId(dishService.getDish(dish.getName()));
		List<Product> productList = productService.getAllProductDtoSorted();
		for (Component comp : componentList) {
			productList.remove(comp.getProduct());
		}
		List<AgeCategory> plist = ageCategoryService
				.getAllAgeCategorySortById();

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

	
	
	
	@RequestMapping(value = "/editModal", method = RequestMethod.GET)
	public ModelAndView editModal(final RedirectAttributes redirectAttributes,
			@RequestParam Map<String, String> requestParams, DishForm dishForm,
			Map<String, Object> model, Map<String, Object> mdl)
			throws IOException {
		
		ArrayList<Component> componentList = (ArrayList<Component>) componentService
				.getAllComponentByDishId(dishService.getDish(requestParams
						.get("dishName")));
		
			Component comp1 = componentService.getComponentById((Long
					.parseLong(requestParams.get("compId"))));
			List<ComponentWeight> list = new ArrayList<ComponentWeight>(comp1.getComponents());

		
		List<Product> productList = productService.getAllProductDtoSorted();
		for (Component comp : componentList) {
			productList.remove(comp.getProduct());
		}
		List<AgeCategory> plist = ageCategoryService
				.getAllAgeCategorySortById();
		ModelAndView mav = new ModelAndView("editModal");
		mdl.put("dishForm", dishForm);
		mav.addObject("pageTitle", "Редагування інгредієнтів");
		mav.addObject("comp", comp1);
		mav.addObject("weigList", list);
		mav.addObject("cat", plist);
		model.put("validationMessages", getAllValidationMessagesAsMap());
		mav.addObject("products", productList);
		return mav;
	}

	@RequestMapping(value = "/updateDish")
	public String updateDish(final RedirectAttributes redirectAttributes,
			Map<String, Object> model,
			@RequestParam Map<String, String> requestParams, DishForm dishForm,
			BindingResult result) {
		model.put("validationMessages", getAllValidationMessagesAsMap());
		
		if(requestParams.get("dishId")!=null){
			Long compId=Long.parseLong(requestParams.get("compId"));
//			Dish dish=dishService.getDishById(Long.parseLong(requestParams.get("dishId")));
//			List<Component> compList=componentService.getAllComponentByDishId(dish);
//			for(Component compTemp:compList){
//				if(compTemp.getId().equals(compId)){
//					componentService.deleteComponent(compTemp);
//					System.out.println("delete"+compTemp.getProduct().getName());
//				}
//				
//			}
			
			System.out.println("delete " + compId);
			componentService.deleteComponent(compId);
			redirectAttributes.addFlashAttribute("infoMessage",
					"updateProductSuccessful");
			return "redirect:/dishlist";
		}
		else if (dishForm.getComp_id().equals("false") == false) {
			System.out.println(dishForm.getComp_id());
			dishForm.setDishName(dishForm.getDishName().trim());
			dishForm.setDishName(dishForm.getDishName().replaceAll("\\s+", " "));

			for (Map.Entry<Long, Double> weight : dishForm.getWeight()
					.entrySet()) {
				weight.setValue(Double.parseDouble(Double.toString(
						weight.getValue()).replace(",", ".")));
				weight.setValue(Double.parseDouble((new DecimalFormat("#.##")
						.format((weight.getValue())))));

				Component comp = componentService
						.updateComponentWeightByDishForm(dishForm);
				componentService.updateComponent(comp);
				redirectAttributes.addFlashAttribute("infoMessage",
						"updateDishSuccessful");
			}

			redirectAttributes.addAttribute("dishName", dishForm.getDishName());

			return "redirect:/editDish";
		}
	
		else
		 {
			System.out.println(requestParams.get("dishId"));
			
			dishValidator.validate(dishForm, result);
			if (result.hasErrors()) {


				Dish dish=dishService.getDishById(dishForm.getId());
				dish.setName(dishForm.getDishName());
				dishService.updateDish(dish);
				redirectAttributes.addFlashAttribute("infoMessage",
						"updateProductSuccessful");
				return "redirect:/dishlist";
				

			}

			Dish dish = dishService.getDishById(dishForm.getId());
			dish.setName(dishForm.getDishName());
			dishService.updateDish(dish);
			redirectAttributes.addFlashAttribute("infoMessage",
					"updateProductSuccessful");
			return "redirect:/dishlist";
		}

	}


	private Map<String, String> getAllValidationMessagesAsMap() {
		Map<String, String> messages = new HashMap<>();
		messages.put(
				"fieldEmpty",
				context.getMessage("fieldEmpty", null,
						LocaleContextHolder.getLocale()));
		messages.put("productNameTooShort", context.getMessage(
				"dishNameTooShort", null, LocaleContextHolder.getLocale()));
		messages.put(
				"productNameTooLong",
				context.getMessage("dishNameTooLong", null,
						LocaleContextHolder.getLocale()));
		messages.put("productNameIllegalCharacters", context.getMessage(
				"dishNameIllegalCharacters", null,
				LocaleContextHolder.getLocale()));
		messages.put(
				"dishNormEmpty",
				context.getMessage("dishNormEmpty", null,
						LocaleContextHolder.getLocale()));
		messages.put("dishNormTooShort", context.getMessage("dishNormTooShort",
				null, LocaleContextHolder.getLocale()));
		messages.put(
				"productNormTooLong",
				context.getMessage("dishNormTooLong", null,
						LocaleContextHolder.getLocale()));
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