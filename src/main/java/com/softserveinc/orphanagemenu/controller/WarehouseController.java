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

import com.softserveinc.orphanagemenu.forms.WarehouseItemForm;
import com.softserveinc.orphanagemenu.model.*;
import com.softserveinc.orphanagemenu.service.WarehouseService;
import com.softserveinc.orphanagemenu.validators.WarehouseItemValidator;
@Controller
public class WarehouseController {

	@Autowired
	private WarehouseService warehouseService;
	@Autowired
	private WarehouseItemValidator warehouseItemValidator;

	@RequestMapping("/warehouse")
	public ModelAndView showWarehouse(
			@RequestParam(value = "page", defaultValue = "1") Integer currentPage)
			throws Exception {
		Integer count = 2;
		Integer offset = (currentPage -1) * count;
		Integer numberOfPages = (int) Math.ceil((float) warehouseService
				.getWarehouseItemsQuantity() / count);

		ModelAndView modelAndView = new ModelAndView("warehouse");
		List<WarehouseItem> warehouseItems = new ArrayList<WarehouseItem>();
		warehouseItems = warehouseService.getPieceOfAllProductsAndQuantity(
				offset, count);
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
	public ModelAndView showWarehouseByNames(@RequestParam("name") String keyWord,
			@RequestParam(value = "page", defaultValue = "1") Integer currentPage)
			throws Exception {
		ModelAndView modelAndView = new ModelAndView("warehouse");
		List<WarehouseItem> warehouseItems = new ArrayList<WarehouseItem>();
		
		Integer count = 1;
		Integer offset = (currentPage -1) * count;
		
		warehouseItems = warehouseService.searchNames(keyWord,offset, count);
		System.out.println(warehouseItems);
		
		Integer numberOfPages = (int) Math.ceil((float) warehouseService.searchNamesQuantity(keyWord) / count);

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
	public ModelAndView editItem(@RequestParam("id") Long id) throws Exception {
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
			WarehouseItemForm warehouseItemForm, BindingResult result)
			throws Exception {
		ModelAndView modelAndView;
		warehouseItemValidator.validate(warehouseItemForm, result);
		if (result.hasErrors()) {
			modelAndView = new ModelAndView("editForm");
			modelAndView.addObject("id", warehouseItemForm.getId());
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
			WarehouseItemForm warehouseItemForm, BindingResult result)
			throws Exception {
		ModelAndView modelAndView;
		warehouseItemValidator.validate(warehouseItemForm, result);
		if (result.hasErrors()) {
			modelAndView = new ModelAndView("editForm");
			modelAndView.addObject("id", warehouseItemForm.getId());
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

}
