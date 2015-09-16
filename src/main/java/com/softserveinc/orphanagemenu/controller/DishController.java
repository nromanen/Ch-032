package com.softserveinc.orphanagemenu.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

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

import com.google.gson.Gson;
import com.softserveinc.orphanagemenu.forms.DishForm;
import com.softserveinc.orphanagemenu.json.DishNameJson;
import com.softserveinc.orphanagemenu.json.DishResponseBody;
import com.softserveinc.orphanagemenu.json.updateComponentJson;
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
	public String getAllDishes(Map<String, Object> mdl, DishForm dishForm) {
		List<Dish> dishList = dishService.getAllDishes();
		mdl.put("dishes", dishList);
		mdl.put("pageTitle", "availableDishes");
		return "dishlist";
	}

	@RequestMapping(value = "/saveDish", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody String saveDish(@RequestBody DishNameJson dishNameJson, DishForm dishForm,
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
				.getAllComponentsByDishId(dishService.getDish(dishForm.getDishName()));
		List<Product> productList = productService.getAllProductDtoSorted();
		dishService.deleteUsedComponentsFromComponentsList(productList, componentList);

		mdl.put("pageTitle", "addComponent");
		mdl.put("dishName", dishForm.getDishName());
		mdl.put("components", componentList);
		mdl.put("category", categoryList);
		mdl.put("products", productList);
		mdl.put("dishForm", dishForm);

		return "addcomponent";
	}

	@RequestMapping(value = "/addcomponents", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody ModelAndView addComponentQuantityToAgeCategory(@RequestBody DishResponseBody dishResponse,
			Map<String, Object> model) {

		Map<Long, Double> categoryIdQuantity = dishService.parseJsonValue(dishResponse);
		Component component = componentService.setAllComponentValue(dishResponse, categoryIdQuantity);

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
	public String edit(@RequestParam("id") String parseDishId, Map<String, Object> mdl) throws IOException {

		Long dishId = Long.parseLong(parseDishId);
		Dish dish = dishService.getDishById(dishId);
		DishForm dishForm = new DishForm();
		dishForm.setDishName(dish.getName());
		dishForm.setId(dish.getId());

		List<AgeCategory> categoryList = ageCategoryService.getAllAgeCategory();
		List<Component> componentList = componentService
				.getAllComponentsByDishId(dishService.getDish(dishForm.getDishName()));

		List<Product> productList = productService.getAllProductDtoSorted();

		for (Component comp : componentList) {
			productList.remove(comp.getProduct());
		}
		List<AgeCategory> plist = ageCategoryService.getAllAgeCategory();

		dishService.deleteUsedComponentsFromComponentsList(productList, componentList);

		mdl.put("pageTitle", "addComponent");
		mdl.put("dishName", dishForm.getDishName());
		mdl.put("components", componentList);
		mdl.put("category", categoryList);
		mdl.put("products", productList);
		mdl.put("pageTitle", "Редагування інгредієнтів");
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

		return "editDish";
	}

	@RequestMapping(value = "/editDishName")
	public String editDishName(Map<String, Object> model, DishForm dishForm) {

		Dish newDish = dishService.getDishById(dishForm.getId());
		newDish.setName(dishForm.getDishName());
		dishService.updateDish(newDish);
		return "redirect:dishlist";
	}

	@RequestMapping(value = "/deleteComp")
	public String deleteComp(final RedirectAttributes redirectAttributes,
			@RequestParam Map<String, String> requestParams) throws Exception {

		Long dishId = Long.parseLong(requestParams.get("dishId"));
		Long compId = Long.parseLong(requestParams.get("compId"));
		try {
			componentService.deleteComponent(compId);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("infoMessage", "dishNameIllegalCharacters");
		}
		return "redirect:/editDish?id=" + dishId;
	}

	@RequestMapping(value = "/editcomponents", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody Long addNewComponentToEditDish(@RequestBody DishResponseBody dishResponse,
			Map<String, Object> model, DishForm dishForm) {

		Map<Long, Double> categoryIdQuantity = dishService.parseJsonValue(dishResponse);
		Component component = componentService.setAllComponentValue(dishResponse, categoryIdQuantity);
		componentService.saveComponent(component);

		return dishForm.getId();
	}

	@RequestMapping(value = "/getComponentWeightQuantity", method = RequestMethod.POST)
	public @ResponseBody String editDishComponentQuantity(@RequestParam("compId") String compId,
			Map<String, Object> model, DishForm dishForm) {

		Long componentId = Long.parseLong(compId);
		Component component = componentService.getComponentById(componentId);
		dishForm.setComp_id(compId);
		ArrayList<Double> ageCategoryQuantity = new ArrayList<Double>(4);
		for (int i = 0; i < 4; i++) {
			ageCategoryQuantity.add((double) 0);
		}
		String componentName = component.getProduct().getName();

		for (ComponentWeight cw : component.getComponents()) {
			for (int i = 1; i < 5; i++) {
				if (cw.getAgeCategory().getId().equals((long) i)) {
					ageCategoryQuantity.set(i - 1, cw.getStandartWeight());

				}
			}
		}

		String json = new Gson().toJson(ageCategoryQuantity);

		model.put("componentName", componentName);

		model.put("dishForm", dishForm);
		return json;

	}

	@RequestMapping(value = "/updateComponentWeightQuantity", method = RequestMethod.POST)
	public @ResponseBody String updateDishComponentQuantity(@RequestBody updateComponentJson dishResponse,
			Map<String, Object> model, DishForm dishForm) {

		Component component = componentService.getComponentById(Long.parseLong(dishForm.getComp_id()));
		Map<Long, Double> categoryIdQuantity = dishService.parseJsonValue(dishResponse);
		component = componentService.updateDishComponentWeight(component, categoryIdQuantity);
		componentService.updateComponent(component);

		return null;
	}

	@SuppressWarnings("unused")
	private Map<String, String> getAllValidationMessagesAsMap() {
		Map<String, String> messages = new HashMap<>();
		messages.put("fieldEmpty", context.getMessage("fieldEmpty", null, LocaleContextHolder.getLocale()));
		messages.put("productNameTooShort",
				context.getMessage("dishNameTooShort", null, LocaleContextHolder.getLocale()));
		messages.put("productNameTooLong",
				context.getMessage("dishNameTooLong", null, LocaleContextHolder.getLocale()));
		messages.put("productNameIllegalCharacters",
				context.getMessage("dishNameIllegalCharacters", null, LocaleContextHolder.getLocale()));
		messages.put("dishNormEmpty", context.getMessage("dishNormEmpty", null, LocaleContextHolder.getLocale()));
		messages.put("dishNormTooShort", context.getMessage("dishNormTooShort", null, LocaleContextHolder.getLocale()));
		messages.put("productNormTooLong",
				context.getMessage("dishNormTooLong", null, LocaleContextHolder.getLocale()));
		messages.put("weightIllegalCharacters",
				context.getMessage("weightIllegalCharacters", null, LocaleContextHolder.getLocale()));
		messages.put("submitChanges", context.getMessage("submitChanges", null, LocaleContextHolder.getLocale()));
		messages.put("yes", context.getMessage("yes", null, LocaleContextHolder.getLocale()));
		messages.put("no", context.getMessage("no", null, LocaleContextHolder.getLocale()));
		messages.put("exitConfirmation", context.getMessage("exitConfirmation", null, LocaleContextHolder.getLocale()));
		return messages;
	}

}
