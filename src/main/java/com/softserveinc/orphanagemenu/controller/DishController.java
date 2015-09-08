package com.softserveinc.orphanagemenu.controller;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.softserveinc.orphanagemenu.forms.DishForm;
import com.softserveinc.orphanagemenu.json.DishNameJson;
import com.softserveinc.orphanagemenu.json.DishResponseBody;
import com.softserveinc.orphanagemenu.model.AgeCategory;
import com.softserveinc.orphanagemenu.model.Component;
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
	public String getAllDishes(Map<String, Object> mdl, DishForm dishForm) {
		List<Dish> dishList = dishService.getAllDishes();
		mdl.put("dishes", dishList);
		mdl.put("pageTitle", "availableDishes");
		return "dishlist";
	}

	@RequestMapping(value = "/saveDish", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody String saveDish(
			@RequestBody DishNameJson dishNameJson, DishForm dishForm,
			BindingResult result, Map<String, Object> mdl) {

		dishForm.setDishName(dishNameJson.getDishName());
		Dish dish = new Dish(dishForm.getDishName(), true);

		// request to ajax 'parseDishNameJson' (
		// ~/resources/javacsript/dish/parseDishNameJson.js )
		dishValidator.validate(dishForm, result);
		if (result.hasErrors()) {
			return "validationError";
		}

		mdl.put("dishForm", dishForm);
		dishService.addDish(dish);

		// redirect to next page from ajax query
		return null;
	}

	@RequestMapping(value = "/addcomponent", method = RequestMethod.GET)
	public String addDishComponents(DishForm dishForm, Map<String, Object> mdl) {

		List<AgeCategory> categoryList = ageCategoryService.getAllAgeCategory();
		List<Component> componentList = componentService
				.getAllComponentsByDishId(dishService.getDish(dishForm
						.getDishName()));
		List<Product> productList = productService.getAllProductDtoSorted();
		dishService.deleteUsedComponentsFromComponentsList(productList,
				componentList);

		mdl.put("pageTitle", "addComponent");
		mdl.put("dishName", dishForm.getDishName());
		mdl.put("components", componentList);
		mdl.put("category", categoryList);
		mdl.put("products", productList);
		mdl.put("dishForm", dishForm);

		return "addcomponent";
	}

	@RequestMapping(value = "/addcomponents", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody ModelAndView addComponentQuantityToAgeCategory(
			@RequestBody DishResponseBody dishResponse,
			Map<String, Object> model) {

		Map<Long, Double> categoryIdQuantity = dishService
				.parseJsonValue(dishResponse);
		Component component = componentService.setAllComponentValue(
				dishResponse, categoryIdQuantity);
		componentService.saveComponent(component);
		
		// redirect to next page from ajax query(
		// ~/resources/javacsript/dish/parseJSON.js )
		return null;
	}

	@RequestMapping(value = "/saveChanges", method = RequestMethod.GET)
	public String clearSession(SessionStatus status) {

		// clear session
		status.isComplete();
		return "redirect:dishlist";
	}

	// Vlad part

	@RequestMapping(value = "/editDish", method = RequestMethod.GET)
	public ModelAndView edit(final RedirectAttributes redirectAttributes,
			@RequestParam Map<String, String> requestParams,
			Map<String, Object> mdl, DishForm dishForm, BindingResult result)
			throws IOException {

		Dish dish = new Dish(requestParams.get("dishName"), true);
		dishForm.setId(dishService.getDish(requestParams.get("dishName"))
				.getId());
		ArrayList<Component> componentList = (ArrayList<Component>) componentService
				.getAllComponentsByDishId(dishService.getDish(dish.getName()));
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
		Component comp1 = componentService.getComponentById(1L);
		ArrayList<Component> componentList = (ArrayList<Component>) componentService
				.getAllComponentsByDishId(dishService.getDish(requestParams
						.get("dishName")));
		if (requestParams.get("compId") != null) {
			comp1 = componentService.getComponentById((Long
					.parseLong(requestParams.get("compId"))));
		}

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
		System.out.println(dishForm.getComp_id());
		if (dishForm.getComp_id().equals("false") == false) {
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

		else {
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