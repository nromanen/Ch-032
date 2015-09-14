package com.softserveinc.orphanagemenu.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
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

	private static final String WAREHOUSE_EDIT = "warehouseEdit";

	private static final String WAREHOUSE = "warehouse";

	private static final String VALIDATION_MESSAGES = "validationMessages";

	private static final String PAGE_TITLE = "pageTitle";

	private static final String INFO_MESSAGE = "infoMessage";

	@Autowired
	@Qualifier("warehouseService")
	private WarehouseService warehouseService;

	@Autowired
	private WarehouseItemValidator warehouseItemValidator;

	@Autowired
	ApplicationContext context;
	private static final Log LOG = LogFactory.getLog(WarehouseController.class);

	@RequestMapping("/warehouse")
	public ModelAndView showWarehouse(
			@RequestParam(value = "page", defaultValue = "1") Integer currentPage) {
		Integer offset = (Math.abs(currentPage) - 1) * PAGECOUNT;
		Integer numberOfPages = (int) Math.ceil((float) warehouseService
				.getCount() / PAGECOUNT);
		ModelAndView modelAndView = new ModelAndView(WAREHOUSE);
		List<WarehouseItem> warehouseItems = new ArrayList<WarehouseItem>();
		warehouseItems = warehouseService.getPage(offset, PAGECOUNT);
		if (warehouseItems.isEmpty()) {
			modelAndView.addObject(INFO_MESSAGE, "messageWarehouseEmpty");
		}
		modelAndView.addObject("warehouseProducts", warehouseItems);
		modelAndView.addObject(PAGE_TITLE, WAREHOUSE);
		modelAndView.addObject("currentPage", currentPage);
		modelAndView.addObject("numberOfPages", numberOfPages);
		LOG.debug("warehouse page");
		return modelAndView;
	}

	@RequestMapping("/warehouseSearch")
	public ModelAndView showWarehouseByNames(
			@RequestParam("name") String keyWord,
			@RequestParam(value = "page", defaultValue = "1") Integer currentPage) {
		ModelAndView modelAndView = new ModelAndView(WAREHOUSE);
		List<WarehouseItem> warehouseItems = new ArrayList<WarehouseItem>();
		Integer offset = (Math.abs(currentPage) - 1) * PAGECOUNT;
		warehouseItems = warehouseService.getPage(keyWord, offset, PAGECOUNT);
		Integer numberOfPages = (int) Math.ceil((float) warehouseService
				.getCount(keyWord) / PAGECOUNT);

		if (warehouseItems.isEmpty()) {
			modelAndView.addObject(INFO_MESSAGE, "notFind");
		}
		modelAndView.addObject("keyWord", keyWord);
		modelAndView.addObject("warehouseProducts", warehouseItems);
		modelAndView.addObject(PAGE_TITLE, WAREHOUSE);
		modelAndView.addObject("numberOfPages", numberOfPages);
		modelAndView.addObject("currentPage", currentPage);
		LOG.debug("warehouseSearch:" + keyWord);
		return modelAndView;
	}

	@RequestMapping("/warehouseEdit")
	public ModelAndView editItem(@RequestParam("id") Long id) {
		WarehouseItemForm form;
		List<Product> productList;
		ModelAndView modelAndView = new ModelAndView(WAREHOUSE_EDIT);
		if (id != 0) {
			form = warehouseService.getForm(id);
			productList = new ArrayList<Product>();
			modelAndView.addObject(PAGE_TITLE, WAREHOUSE_EDIT);
		} else {
			form = new WarehouseItemForm();
			productList = warehouseService.getNewProducts();
			modelAndView.addObject(PAGE_TITLE, "warehouseAdd");
		}

		modelAndView.addObject("productList", productList);
		modelAndView.addObject("productID", id);
		modelAndView.addObject("warehouseItemForm", form);
		modelAndView.addObject(VALIDATION_MESSAGES,
				getAllValidationMessagesAsMap());
		LOG.debug("warehouseEdit:" + id);
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
			modelAndView = new ModelAndView(WAREHOUSE_EDIT);
			modelAndView.addObject("id", warehouseItemForm.getId());
			modelAndView.addObject(VALIDATION_MESSAGES,
					getAllValidationMessagesAsMap());
			return modelAndView;
		}
		warehouseService.saveItem(warehouseItemForm.getItemName(),
				Double.parseDouble(warehouseItemForm.getQuantity()));
		modelAndView = new ModelAndView("redirect:warehouse");
		redirectAttributes.addFlashAttribute(INFO_MESSAGE, "messageSaved");
		LOG.debug("warehouseSave:" + warehouseItemForm.getItemName());
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
			modelAndView = new ModelAndView(WAREHOUSE_EDIT);
			modelAndView.addObject("id", warehouseItemForm.getId());
			modelAndView.addObject(VALIDATION_MESSAGES,
					getAllValidationMessagesAsMap());
			return modelAndView;
		}
		
		warehouseService.saveItem(warehouseItemForm.getItemName(),
				Double.parseDouble(warehouseItemForm.getQuantity()));
		modelAndView = new ModelAndView("redirect:warehouseEdit");
		redirectAttributes.addFlashAttribute(INFO_MESSAGE, "messageSaved");
		modelAndView.addObject("id", 0);
		LOG.debug("warehouseSaveAndAdd:" + warehouseItemForm.getItemName());
		return modelAndView;
	}

	@RequestMapping("/warehouse/*")
	public ModelAndView showWarehouse() {
		ModelAndView modelAndView = new ModelAndView("redirect:/warehouse");
		LOG.debug("warehouse: redirect");
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
