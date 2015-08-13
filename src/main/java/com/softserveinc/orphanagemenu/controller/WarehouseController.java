package com.softserveinc.orphanagemenu.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.softserveinc.orphanagemenu.forms.WarehouseItemForm;
import com.softserveinc.orphanagemenu.model.*;
import com.softserveinc.orphanagemenu.service.WarehouseService;
import com.softserveinc.orphanagemenu.validators.WarehouseItemValidator;

@Controller
public class WarehouseController {

	@Autowired
	@Qualifier("warehouseService")
	private WarehouseService warehouseService;

	@Autowired
	private WarehouseItemValidator warehouseItemValidator;
	@Autowired
	ApplicationContext context;

	@RequestMapping("/warehouse")
	public ModelAndView showWarehouse(
			@RequestParam(value = "page", defaultValue = "1") Integer currentPage) {
		Integer count = 5;
		Integer offset = (Math.abs(currentPage) - 1) * count;
		Integer numberOfPages = (int) Math.ceil((float) warehouseService
				.getCount() / count);

		ModelAndView modelAndView = new ModelAndView("warehouse");
		List<WarehouseItem> warehouseItems = new ArrayList<WarehouseItem>();
		warehouseItems = warehouseService.getPage(offset, count);
		if (warehouseItems.isEmpty()) {
			modelAndView.addObject("message", "messageWarehouseEmpty");
		}

		modelAndView.addObject("warehouseProducts", warehouseItems);
		modelAndView.addObject("pageTitle", "warehouse");
		modelAndView.addObject("currentPage", currentPage);
		modelAndView.addObject("numberOfPages", numberOfPages);
		return modelAndView;
	}

	@RequestMapping("/warehouseSearch")
	public ModelAndView showWarehouseByNames(
			@RequestParam("name") String keyWord,
			@RequestParam(value = "page", defaultValue = "1") Integer currentPage) {
		ModelAndView modelAndView = new ModelAndView("warehouse");
		List<WarehouseItem> warehouseItems = new ArrayList<WarehouseItem>();
		Integer count = 5;
		Integer offset = (Math.abs(currentPage)-1) * count;
		warehouseItems = warehouseService.getPage(keyWord, offset, count);
		Integer numberOfPages = (int) Math.ceil((float) warehouseService
				.getCount(keyWord) / count);

		if (warehouseItems.isEmpty()) {
			modelAndView.addObject("message", "notFind");
		}

		modelAndView.addObject("keyWord", keyWord);
		modelAndView.addObject("warehouseProducts", warehouseItems);
		modelAndView.addObject("pageTitle", "warehouse");
		modelAndView.addObject("numberOfPages", numberOfPages);
		modelAndView.addObject("currentPage", currentPage);

		return modelAndView;
	}

	@RequestMapping("/warehouseEdit")
	public ModelAndView editItem(@RequestParam("id") Long id) {
		WarehouseItemForm form;
		List<Product> productList;
		ModelAndView modelAndView = new ModelAndView("editForm");

		if (id != 0) {
			form = warehouseService.getForm(id);
			productList = new ArrayList<Product>();
			modelAndView.addObject("pageTitle", "warehouseEdit");

		} else {
			form = new WarehouseItemForm();
			productList = warehouseService.getNewProducts();
			modelAndView.addObject("pageTitle", "warehouseAdd");

		}

		modelAndView.addObject("productList", productList);
		modelAndView.addObject("productID", id);
		modelAndView.addObject("warehouseItemForm", form);
		modelAndView.addObject("validationMessages",
				getAllValidationMessagesAsMap());
		return modelAndView;
	}

	@RequestMapping(value = "/warehouseSave", method = RequestMethod.POST)
	public ModelAndView saveItem(final RedirectAttributes redirectAttributes,
			WarehouseItemForm warehouseItemForm, BindingResult result)
			throws Exception {
		ModelAndView modelAndView;
		warehouseItemForm.setQuantity(warehouseItemForm.getQuantity().replace(",", "."));
		
		warehouseItemValidator.validate(warehouseItemForm, result);
		if (result.hasErrors()) {
			modelAndView = new ModelAndView("editForm");
			modelAndView.addObject("id", warehouseItemForm.getId());
			modelAndView.addObject("validationMessages",
					getAllValidationMessagesAsMap());
			return modelAndView;
		}
		warehouseService.saveForm(warehouseItemForm);
		modelAndView = new ModelAndView("redirect:warehouse");
		redirectAttributes.addFlashAttribute("message", "messageSaved");
		return modelAndView;
	}

	@RequestMapping(value = "/warehouseSaveAndAdd", method = RequestMethod.POST)
	public ModelAndView saveItemAndAdd(
			final RedirectAttributes redirectAttributes,
			WarehouseItemForm warehouseItemForm, BindingResult result) {
		ModelAndView modelAndView;
		warehouseItemValidator.validate(warehouseItemForm, result);
		if (result.hasErrors()) {
			modelAndView = new ModelAndView("editForm");
			modelAndView.addObject("id", warehouseItemForm.getId());
			modelAndView.addObject("validationMessages",
					getAllValidationMessagesAsMap());
			return modelAndView;
		}

		warehouseService.saveForm(warehouseItemForm);
		modelAndView = new ModelAndView("redirect:warehouseEdit");
		redirectAttributes.addFlashAttribute("message", "messageSaved");

		modelAndView.addObject("id", 0);
		return modelAndView;
	}

	@RequestMapping("/warehouse/*")
	public ModelAndView showWarehouse() {

		ModelAndView modelAndView = new ModelAndView("redirect:/warehouse");

		return modelAndView;
	}

	private Map<String, String> getAllValidationMessagesAsMap() {
		Map<String, String> messages = new HashMap<>();
		messages.put("warehouseQuantityRequired", context.getMessage(
				"warehouseQuantityRequired", null,
				LocaleContextHolder.getLocale()));
		messages.put("warehouseQuantityMinLength", context.getMessage(
				"warehouseQuantityMinLength", null,
				LocaleContextHolder.getLocale()));
		messages.put("warehouseQuantityMaxLength", context.getMessage(
				"warehouseQuantityMaxLength", null,
				LocaleContextHolder.getLocale()));
		messages.put("warehouseQuantityMustBeNumber", context.getMessage(
				"warehouseQuantityMustBeNumber", null,
				LocaleContextHolder.getLocale()));
		messages.put(
				"fieldEmpty",
				context.getMessage("fieldEmpty", null,
						LocaleContextHolder.getLocale()));
		return messages;
	}

}
