package com.softserveinc.orphanagemenu.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;

import com.softserveinc.orphanagemenu.model.Dimension;
import com.softserveinc.orphanagemenu.model.Product;
import com.softserveinc.orphanagemenu.service.ProductService;

@Controller
public class ProductController {

	@Autowired
	private ProductService productService;

	@RequestMapping({ "/products" })
	public String getList(Model model) {
		ArrayList<Product> prod = productService.getAllProduct();
		model.addAttribute("products", prod);
		return "products";

	}

	@RequestMapping({ "/saveProduct" })
	public String save(@RequestParam("productName") String name,
			@RequestParam("dimensionId") String dimensionId) {

		Dimension dimension = productService.getDimensionById(Long
				.parseLong(dimensionId));
		Product prod = new Product();
		prod.setName(name);
		prod.setDimension(dimension);
		productService.saveProduct(prod);
		return "redirect:/products";
	}

	@RequestMapping({ "/addProduct" })
	public String addProduct(Model model) {
		ArrayList<Dimension> dimension = productService.getAllDimension();
		model.addAttribute("dimension", dimension);
		return "addProduct";
	}

	@RequestMapping({ "/editProduct" })
	public String editProduct(
			@RequestParam(value = "id", required = true) Long id, Model model) {
		model.addAttribute("product", productService.getProductById(id));
		ArrayList<Dimension> dimension = productService.getAllDimension();
		model.addAttribute("dimension", dimension);
		return "editProduct";
	}

	@RequestMapping({ "/updateProduct" })
	public String updateProduct(@RequestParam("productName") String name,
			@RequestParam("dimensionId") String dimensionId,
			@RequestParam("productId") String productId) {

		Dimension dimension = productService.getDimensionById(Long
				.parseLong(dimensionId));
		Product prod = new Product();
		prod.setId(Long.parseLong(productId));
		prod.setName(name);
		prod.setDimension(dimension);
		productService.updateProduct(prod);
		return "redirect:/products";
	}
	
	@RequestMapping({ "/saveAndAddProduct" })
	public String saveAndAdd(@RequestParam("productName") String name,
			@RequestParam("dimensionId") String dimensionId) {
		Dimension dimension = productService.getDimensionById(Long
				.parseLong(dimensionId));
		Product prod = new Product();
		prod.setName(name);
		prod.setDimension(dimension);
		productService.saveProduct(prod);
		return "redirect:/addProduct";
	}

}
