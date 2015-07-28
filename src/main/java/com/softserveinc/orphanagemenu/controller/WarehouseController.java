package com.softserveinc.orphanagemenu.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.softserveinc.orphanagemenu.model.*;
import com.softserveinc.orphanagemenu.service.WarehouseService;

@Controller
public class WarehouseController {

	@Autowired
	private WarehouseService service;
	
	@RequestMapping("warehouse")
	public ModelAndView showWarehouse() {
		ModelAndView modelAndview = new ModelAndView("warehouse");
		List<WarehouseItem> warehouseItems = new ArrayList<WarehouseItem>();

		warehouseItems = service.getAllItems();
		if (warehouseItems.isEmpty())
			modelAndview.addObject("infoMessage",
					"Продукти відсутні на складі. Додайте будь ласка!");
		
		modelAndview.addObject("warehouseProducts", warehouseItems);
		modelAndview.addObject("pageTitle", "warehouse");

		return modelAndview;
	}
//work
	@RequestMapping("warehouseEdit")
	public ModelAndView showWarehouseEdit(Map<String, Object> model,
			@RequestParam("name") String name,
			@RequestParam("quantity") Double quantity,
			@RequestParam("dimension") String dimension) {
		ModelAndView modelAndView = new ModelAndView("warehouseEdit");
		modelAndView.addObject("name", name);
		modelAndView.addObject("quantity", quantity);
		modelAndView.addObject("dimension", dimension);
		modelAndView.addObject("pageTitle", "Редагування продукту на складі");
		return modelAndView;
	}

	@RequestMapping(value = "editItemInWarehouse", method = RequestMethod.GET)
	public ModelAndView editWarehouse(@RequestParam("productName") String name,
			@RequestParam("quantity") Double quantity) {
		service.addProduct(name, quantity);
		return new ModelAndView("redirect:warehouse");
	}

	@RequestMapping("warehouseAdd")
	public ModelAndView showWarehouseAdd() {
		List<Product> products = service.getAllEmptyItems();
		ModelAndView modelAndView = new ModelAndView("warehouseAdd");
		modelAndView.addObject("products", products);
		modelAndView.addObject("pageTitle", "Додавання продукту на склад");
		return modelAndView;
	}

	@RequestMapping(value = "saveItemToWarehouse", method = RequestMethod.GET)
	public ModelAndView saveWarehouse(@RequestParam("productName") String name,
			@RequestParam("quantity") Double quantity) {

		ModelAndView modelAndView = new ModelAndView("redirect:warehouse");
		modelAndView.addObject("infoMessage", name + " збережено");

		service.addProduct(name, quantity);

		return modelAndView;
	}

	@RequestMapping(value = "addAndSaveItemToWarehouse", method = RequestMethod.GET)
	public ModelAndView addAndSaveWarehouse(
			@RequestParam("productName") String name,
			@RequestParam("quantity") Double quantity) {

		service.addProduct(name, quantity);

		ModelAndView modelAndView = new ModelAndView("warehouseAdd");
		modelAndView.addObject("infoMessage", name + " збережено");
		List<Product> products = service.getAllEmptyItems();
		modelAndView.addObject("products", products);
		modelAndView.addObject("product", name);

		return modelAndView;
	}

}
