package com.softserveinc.orphanagemenu.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.softserveinc.orphanagemenu.model.*;
import com.softserveinc.orphanagemenu.service.WarehouseService;
import com.softserveinc.orphanagemenu.validator.warehouse.WarehouseItemForm;
import com.softserveinc.orphanagemenu.validator.warehouse.WarehouseItemValidator;

@Controller
public class WarehouseController {

	@Autowired
	private WarehouseService warehouseService;
	@Autowired
	private WarehouseItemValidator warehouseItemValidator;

	@RequestMapping("/warehouse")
	public ModelAndView showWarehouse(
			@RequestParam(value = "page", defaultValue = "0") Integer page) {
		Integer count = 10;
		Integer offset=page*count;
		Integer countOfPages = (int) Math.ceil((float)warehouseService.getWarehouseItemsQuantity()/count);
		
		ModelAndView modelAndView = new ModelAndView("warehouse");
		List<WarehouseItem> warehouseItems = new ArrayList<WarehouseItem>();
		warehouseItems = warehouseService.getPieceOfAllProductsAndQuantity(
				offset, count);
		if (warehouseItems.isEmpty()) {
			modelAndView.addObject("infoMessage", "messageWarehouseEmpty");
		}

		modelAndView.addObject("warehouseProducts", warehouseItems);
		modelAndView.addObject("pageTitle", "warehouse");
		modelAndView.addObject("currentPage", page);
		modelAndView.addObject("numberOfPages", countOfPages);
		return modelAndView;
	}

	@RequestMapping("/warehouseSearch")
	public ModelAndView showWarehouseByNames(@RequestParam("name") String name) {
		ModelAndView modelAndView = new ModelAndView("warehouse");
		List<WarehouseItem> warehouseItems = new ArrayList<WarehouseItem>();

		warehouseItems = warehouseService.searchNames(name);

		if (warehouseItems.isEmpty()) {
			modelAndView.addObject("infoMessage", "notFind");
		}

		modelAndView.addObject("warehouseProducts", warehouseItems);
		modelAndView.addObject("pageTitle", "warehouse");

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
			productList = warehouseService.getAllEmptyItems();
			modelAndView.addObject("pageTitle", "warehouseAdd");

		}
		modelAndView.addObject("productList", productList);
		modelAndView.addObject("productID", id);
		modelAndView.addObject("warehouseItemForm", form);
		return modelAndView;
	}

	@RequestMapping(value = "/warehouseSave", method = RequestMethod.POST)
	public ModelAndView saveItem(final RedirectAttributes redirectAttributes,
			WarehouseItemForm warehouseItemForm, BindingResult result) {
		ModelAndView modelAndView;
		warehouseItemValidator.validate(warehouseItemForm, result);
		if (result.hasErrors()) {
			modelAndView = new ModelAndView("editForm");
			modelAndView.addObject("id", warehouseItemForm.getId());
			return modelAndView;
		}
		warehouseService.saveForm(warehouseItemForm);
		modelAndView = new ModelAndView("redirect:warehouse");
		redirectAttributes.addFlashAttribute("infoMessage", "messageSaved");
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
			return modelAndView;
		}

		warehouseService.saveForm(warehouseItemForm);
		modelAndView = new ModelAndView("redirect:warehouseEdit");
		redirectAttributes.addFlashAttribute("infoMessage", "messageSaved");
		modelAndView.addObject("id", 0);
		return modelAndView;
	}

}
