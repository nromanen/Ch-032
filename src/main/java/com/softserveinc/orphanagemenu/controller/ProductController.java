package com.softserveinc.orphanagemenu.controller;

import java.util.ArrayList;

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
		model.addAttribute("dimension", dimension);
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
			@RequestParam("1") Double first,
			@RequestParam("2") Double second,
			@RequestParam("3") Double third,
			@RequestParam("4") Double fourth) {
		ArrayList <Double> list = new ArrayList <Double>();
		list.add(first);
		list.add(second);
		list.add(third);
		list.add(fourth);
		saveProduct(name, dimensionId, list);
		return "redirect:/products";
	}

	@RequestMapping({ "/saveAndAddProduct" })
	public String saveAndAdd(@RequestParam("productName") String name,
			@RequestParam("dimensionId") String dimensionId) {
//		saveProduct(name, dimensionId);
		return "redirect:/addProduct";
	}

	private void saveProduct(String name, String dimensionId, ArrayList <Double> listOfValues) {
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
		int i=0;
		for (AgeCategory currentCat : ageCategories) {
			ProductWeight productWeight = new ProductWeight ();
			productWeight.setAgeCategory(currentCat);
			productWeight.setProduct(productService.getProductById(id));
			productWeight.setStandartProductQuantity(listOfValues.get(i));
			i++;
			productService.saveProductWeight(productWeight);
		}
	}
}