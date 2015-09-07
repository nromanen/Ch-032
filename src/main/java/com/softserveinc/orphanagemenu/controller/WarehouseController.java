package com.softserveinc.orphanagemenu.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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

import static com.softserveinc.orphanagemenu.dto.AppProperties.PAGECOUNT;

@Controller
public class WarehouseController {

	@Autowired
	@Qualifier("warehouseService")
	private WarehouseService warehouseService;

	@Autowired
	private WarehouseItemValidator warehouseItemValidator;

	@Autowired
	ApplicationContext context;
	private static final Log LOGER = LogFactory
			.getLog(WarehouseController.class);

	@RequestMapping("/warehouse")
	public ModelAndView showWarehouse(
			@RequestParam(value = "page", defaultValue = "1") Integer currentPage) {
		Integer offset = (Math.abs(currentPage) - 1) * PAGECOUNT;
		Integer numberOfPages = (int) Math.ceil((float) warehouseService
				.getCount() / PAGECOUNT);
		ModelAndView modelAndView = new ModelAndView("warehouse");
		List<WarehouseItem> warehouseItems = new ArrayList<WarehouseItem>();
		warehouseItems = warehouseService.getPage(offset, PAGECOUNT);
		if (warehouseItems.isEmpty()) {
			modelAndView.addObject("infoMessage", "messageWarehouseEmpty");
		}
		modelAndView.addObject("warehouseProducts", warehouseItems);
		modelAndView.addObject("pageTitle", "warehouse");
		modelAndView.addObject("currentPage", currentPage);
		modelAndView.addObject("numberOfPages", numberOfPages);
		LOGER.debug("warehouse page");
		return modelAndView;
	}

	@RequestMapping("/warehouseSearch")
	public ModelAndView showWarehouseByNames(
			@RequestParam("name") String keyWord,
			@RequestParam(value = "page", defaultValue = "1") Integer currentPage) {
		ModelAndView modelAndView = new ModelAndView("warehouse");
		List<WarehouseItem> warehouseItems = new ArrayList<WarehouseItem>();
		Integer offset = (Math.abs(currentPage) - 1) * PAGECOUNT;
		warehouseItems = warehouseService.getPage(keyWord, offset, PAGECOUNT);
		Integer numberOfPages = (int) Math.ceil((float) warehouseService
				.getCount(keyWord) / PAGECOUNT);

		if (warehouseItems.isEmpty()) {
			modelAndView.addObject("infoMessage", "notFind");
		}
		modelAndView.addObject("keyWord", keyWord);
		modelAndView.addObject("warehouseProducts", warehouseItems);
		modelAndView.addObject("pageTitle", "warehouse");
		modelAndView.addObject("numberOfPages", numberOfPages);
		modelAndView.addObject("currentPage", currentPage);
		LOGER.debug("warehouseSearch:" + keyWord);
		return modelAndView;
	}

	@RequestMapping("/warehouseEdit")
	public ModelAndView editItem(@RequestParam("id") Long id) {
		WarehouseItemForm form;
		List<Product> productList;
		ModelAndView modelAndView = new ModelAndView("warehouseEdit");
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
		LOGER.debug("warehouseEdit:" + id);
		return modelAndView;
	}

	@RequestMapping(value = "/warehouseSave", method = RequestMethod.POST)
	public ModelAndView saveItem(final RedirectAttributes redirectAttributes,
			WarehouseItemForm warehouseItemForm, BindingResult result) {
		ModelAndView modelAndView;
		warehouseItemForm.setQuantity(warehouseItemForm.getQuantity().replace(
				",", "."));
		warehouseItemValidator.validate(warehouseItemForm, result);
		if (result.hasErrors()) {
			modelAndView = new ModelAndView("warehouseEdit");
			modelAndView.addObject("id", warehouseItemForm.getId());
			modelAndView.addObject("validationMessages",
					getAllValidationMessagesAsMap());
			return modelAndView;
		}
		warehouseService.saveItem(warehouseItemForm.getItemName(),
				Double.parseDouble(warehouseItemForm.getQuantity()));
		modelAndView = new ModelAndView("redirect:warehouse");
		redirectAttributes.addFlashAttribute("infoMessage", "messageSaved");
		LOGER.debug("warehouseSave:" + warehouseItemForm.getItemName());
		return modelAndView;
	}

	@RequestMapping(value = "/warehouseSaveAndAdd", method = RequestMethod.POST)
	public ModelAndView saveItemAndAdd(
			final RedirectAttributes redirectAttributes,
			WarehouseItemForm warehouseItemForm, BindingResult result) {
		ModelAndView modelAndView;
		warehouseItemForm.setQuantity(warehouseItemForm.getQuantity().replace(
				",", "."));
		warehouseItemValidator.validate(warehouseItemForm, result);
		if (result.hasErrors()) {
			modelAndView = new ModelAndView("warehouseEdit");
			modelAndView.addObject("id", warehouseItemForm.getId());
			modelAndView.addObject("validationMessages",
					getAllValidationMessagesAsMap());
			return modelAndView;
		}

		warehouseService.saveItem(warehouseItemForm.getDimension(),
				Double.parseDouble(warehouseItemForm.getQuantity()));
		modelAndView = new ModelAndView("redirect:warehouseEdit");
		redirectAttributes.addFlashAttribute("infoMessage", "messageSaved");
		modelAndView.addObject("id", 0);
		LOGER.debug("warehouseSaveAndAdd:" + warehouseItemForm.getItemName());
		return modelAndView;
	}

	@RequestMapping("/warehouse/*")
	public ModelAndView showWarehouse() {
		ModelAndView modelAndView = new ModelAndView("redirect:/warehouse");
		LOGER.debug("warehouse: redirect");
		return modelAndView;
	}

	private Set<String> getAllValidationMessagesAsMap() {
		Set<String> messages = new HashSet<>();
		messages.add("warehouseQuantityRequired");
		messages.add("warehouseQuantityMinLength");
		messages.add("warehouseQuantityMaxLength");
		messages.add("warehouseQuantityMustBeNumber");
		messages.add("fieldEmpty");
		messages.add("submitChanges");
		messages.add("yes");
		messages.add("no");
		messages.add("exitConfirmation");
		return messages;
	}
}
