package com.softserveinc.orphanagemenu.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.softserveinc.orphanagemenu.dao.WarehouseDao;
import com.softserveinc.orphanagemenu.model.*;
import com.softserveinc.orphanagemenu.service.WarehouseService;

@Controller
public class WarehouseController {

	@Autowired
	private WarehouseService service;
	@Autowired
	private WarehouseDao warehouseDAO;
 
	@RequestMapping("warehouse")
	public ModelAndView showWarehouse() {
		ModelAndView modelAndview = new ModelAndView("warehouse");
		modelAndview.addObject("warehouseProducts", service.getAllItems());
		return modelAndview;
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
		List<Product> products = warehouseDAO.getEmptyProducts();
		ModelAndView modelAndView = new ModelAndView("warehouseAdd");
		modelAndView.addObject("products", products);
		return modelAndView;
	}

	@RequestMapping(value = "saveItemToWarehouse", method = RequestMethod.GET)
	public ModelAndView saveWarehouse(@RequestParam("productName") String name,
			@RequestParam("quantity") Double quantity) {
		service.addProduct(name, quantity);
		System.out.println("*****"+name+quantity);

		return new ModelAndView("redirect:warehouse");
	}

}
