package com.softserveinc.orphanagemenu.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.softserveinc.orphanagemenu.model.AgeCategory;
import com.softserveinc.orphanagemenu.model.Dimension;
import com.softserveinc.orphanagemenu.model.Product;
import com.softserveinc.orphanagemenu.model.ProductWeight;
import com.softserveinc.orphanagemenu.service.ProductService;

@Controller
public class ProductController {

	@Autowired
	private ProductService productService;

	@RequestMapping({ "/products" })
	public String getList(Model model) {

		ArrayList<Product> prod = productService.getAllProduct();
		ArrayList<AgeCategory> ageCategory = productService.getAllCategory();
		model.addAttribute("products", prod);
		model.addAttribute("ageCategory", ageCategory);
		model.addAttribute("titlePage", "Список продуктів");
		return "products";
	}

	@RequestMapping({ "/addProduct" })
	public String addProduct(Model model) {
		ArrayList<Dimension> dimension = productService.getAllDimension();
		ArrayList<AgeCategory> ageCategory = productService.getAllAgeCategory();
		model.addAttribute("dimension", dimension);
		model.addAttribute("ageCategory", ageCategory);
		model.addAttribute("titlePage", "Додати продукт");
		return "addProduct";
	}

	@RequestMapping({ "/editProduct" })
	public String editProduct(
			@RequestParam(value = "id", required = true) Long id, Model model) {
		model.addAttribute("product", productService.getProductById(id));
		ArrayList<Dimension> dimension = productService.getAllDimension();
		ArrayList<AgeCategory> ageCategory = productService.getAllAgeCategory();
		model.addAttribute("dimension", dimension);
		model.addAttribute("ageCategory", ageCategory);
		model.addAttribute("titlePage", "Редагування продукту");
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

	@RequestMapping({ "/saveProduct" })
	public String save(@RequestParam("productName") String name,
			@RequestParam("dimensionId") String dimensionId,
			@RequestParam("weight") List<String> weightList) {
		ArrayList<Double> weights = new ArrayList<Double>();
		for (String weight : weightList) {
			weights.add(Double.parseDouble(weight));
		}
		saveProduct(name, dimensionId, weights);
		return "redirect:/products";
	}

	@RequestMapping({ "/saveAndAddProduct" })
	public String saveAndAdd(@RequestParam("productName") String name,
			@RequestParam("dimensionId") String dimensionId,
			@RequestParam("weight") List<String> weightList) {
		ArrayList<Double> weights = new ArrayList<Double>();
		for (String weight : weightList) {
			weights.add(Double.parseDouble(weight));
		}
		saveProduct(name, dimensionId, weights);
		return "redirect:/addProduct";
	}

	private void saveProduct(String name, String dimensionId,
			ArrayList<Double> weights) {
		Dimension dimension = productService.getDimensionById(Long
				.parseLong(dimensionId));
		Product prod = new Product();
		prod.setName(name);
		prod.setDimension(dimension);
		productService.saveProduct(prod);
		Product takenProduct = productService.getProduct(name);
		Long id = takenProduct.getId();
		ArrayList<AgeCategory> ageCategories = productService
				.getAllAgeCategory();
		for (int i = 0; i < ageCategories.size(); i++) {
			ProductWeight productWeight = new ProductWeight();
			productWeight.setAgeCategory(ageCategories.get(i));
			productWeight.setProduct(productService.getProductById(id));
			productWeight.setStandartProductQuantity(weights.get(i));
			productService.saveProductWeight(productWeight);
		}
	}
}