package com.softserveinc.orphanagemenu.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.softserveinc.orphanagemenu.dao.WarehouseDao;
import com.softserveinc.orphanagemenu.model.*;
import com.softserveinc.orphanagemenu.service.WarehouseService;
import com.softserveinc.orphanagemenu.validator.warehouse.WarehouseItemForm;
import com.softserveinc.orphanagemenu.validator.warehouse.WarehouseItemValidator;

@Controller
public class WarehouseController {

	@Autowired
	private WarehouseService service;
	@Autowired
	private WarehouseItemValidator warehouseItemValidator;
	@Autowired
	private WarehouseDao warehouseDAO;

	@RequestMapping("warehouse")
	public ModelAndView showWarehouse() {
		ModelAndView modelAndView = new ModelAndView("warehouse");
		List<WarehouseItem> warehouseItems = new ArrayList<WarehouseItem>();

		warehouseItems = service.getAllItems();
		if (warehouseItems.isEmpty())
			modelAndView.addObject("infoMessage", "messageWarehouseEmpty");

		modelAndView.addObject("warehouseProducts", warehouseItems);
		modelAndView.addObject("pageTitle", "warehouse");

		return modelAndView;
	}

	@RequestMapping("warehouseEdit")
	public ModelAndView showWarehouseEdit(Map<String, Object> model,
			@RequestParam("name") String name,
			@RequestParam("quantity") Double quantity,
			@RequestParam("dimension") String dimension) {

		ModelAndView modelAndView = new ModelAndView("warehouseEdit");
		modelAndView.addObject("name", name);
		modelAndView.addObject("quantity", quantity);
		modelAndView.addObject("dimension", dimension);

		modelAndView.addObject("pageTitle", "warehousreEdit");

		return modelAndView;
	}

	// work
	@RequestMapping("editItemInWarehouse")
	public ModelAndView editWarehouse(
			final RedirectAttributes redirectAttributes,
			@RequestParam Map<String, String> requestParams,
			@RequestParam("productName") String name,
			@RequestParam("quantity") Double quantity) {

		ModelAndView modelAndView = new ModelAndView("redirect:warehouse");

		service.addProduct(name, quantity);
		redirectAttributes
				.addFlashAttribute("infoMessage", name + " збережено");

		return modelAndView;
	}

	@RequestMapping("warehouseAdd")
	public ModelAndView showWarehouseAdd() {

		List<Product> products = service.getAllEmptyItems();

		ModelAndView modelAndView = new ModelAndView("warehouseAdd");
		modelAndView.addObject("products", products);
		modelAndView.addObject("pageTitle", "warehouseAdd");

		return modelAndView;
	}

	@RequestMapping(value = "saveItemToWarehouse", method = RequestMethod.GET)
	public ModelAndView saveWarehouse(
			final RedirectAttributes redirectAttributes,
			@RequestParam("productName") String name,
			@RequestParam("quantity") Double quantity) {

		ModelAndView modelAndView = new ModelAndView("redirect:warehouse");

		service.addProduct(name, quantity);

		redirectAttributes
				.addFlashAttribute("infoMessage", name + " збережено");

		return modelAndView;
	}

	@RequestMapping(value = "addAndSaveItemToWarehouse", method = RequestMethod.GET)
	public ModelAndView addAndSaveWarehouse(
			@RequestParam("productName") String name,
			@RequestParam("quantity") Double quantity) {

		service.addProduct(name, quantity);

		ModelAndView modelAndView = new ModelAndView("warehouseAdd");

		List<Product> products = service.getAllEmptyItems();
		modelAndView.addObject("products", products);
		modelAndView.addObject("infoMessage", name + " збережено");

		return modelAndView;
	}

	// new methods
	@RequestMapping("/edit")
	public ModelAndView editItem(@RequestParam("id") Long id) {
		WarehouseItemForm form;
		List<Product> productList;
		ModelAndView modelAndView = new ModelAndView("editForm");

		if (id != 0) {
			form = service.getForm(id);
			productList = new ArrayList<Product>();
			modelAndView.addObject("listIsEmpty", false);
		} else {
			form = new WarehouseItemForm();
			productList = warehouseDAO.getEmptyProducts();
			if (productList.isEmpty()) {
			modelAndView.addObject("listIsEmpty", true);	
			}			
		}
		modelAndView.addObject("productList", productList);
		modelAndView.addObject("warehouseItemForm", form);
		return modelAndView;
	}

	@RequestMapping(value = "saveWarehouseItem", method = RequestMethod.POST)
	public ModelAndView saveItem(final RedirectAttributes redirectAttributes,
			@ModelAttribute("commandName") WarehouseItemForm warehouseItemForm,
			BindingResult result) {
		ModelAndView modelAndView;
		warehouseItemValidator.validate(warehouseItemForm, result);
		if(result.hasErrors()){
			modelAndView = new ModelAndView("redirect:edit");
			modelAndView.addObject("id",warehouseItemForm.getId());
			return modelAndView;
		}
			
			service.saveForm(warehouseItemForm);
			modelAndView = new ModelAndView("redirect:warehouse");
			redirectAttributes.addFlashAttribute("infoMessage", "messageSaved");
		return modelAndView;
	}

}
