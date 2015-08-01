package com.softserveinc.orphanagemenu.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.softserveinc.orphanagemenu.forms.ProductForm;
import com.softserveinc.orphanagemenu.model.AgeCategory;
import com.softserveinc.orphanagemenu.model.Dimension;
import com.softserveinc.orphanagemenu.model.Product;
import com.softserveinc.orphanagemenu.service.ProductService;

@Controller
public class ProductController {

	@Autowired
	private ProductService productService;

	@RequestMapping({ "/products" })
	public String getList(
			@CookieValue(value = "sort", defaultValue = "asc") String sortValue,
			Model model) {
		if (sortValue.equals("asc")) {
			List<Product> prod = productService.getAllProductDtoSorted("asc");
			model.addAttribute("alt", "");
			model.addAttribute("sort", "desc");
			model.addAttribute("products", prod);
		} else {
			List<Product> prod = productService.getAllProductDtoSorted("desc");
			model.addAttribute("alt", "-alt");
			model.addAttribute("sort", "asc");
			model.addAttribute("products", prod);
		}
		List<AgeCategory> ageCategory = productService.getAllAgeCategory();
		model.addAttribute("ageCategory", ageCategory);
		model.addAttribute("pageTitle", "productList");
		return "products";
	}

	@RequestMapping({ "/editProducts" })
	public String product(@RequestParam Map<String, String> requestParams,
			Map<String, Object> model) {
		ProductForm productForm = null;
		ArrayList<Dimension> dimensionList = productService.getAllDimension();
		ArrayList<AgeCategory> ageCategoryList = productService
				.getAllAgeCategory();
		Long id = Long.parseLong(requestParams.get("id"));
		productForm = productService.getProductFormByProductId(id);
		model.put("action", "save");
		model.put("pageTitle", "editProduct");
		model.put("productForm", productForm);
		model.put("dimensionList", dimensionList);
		model.put("ageCategoryList", ageCategoryList);
		return "product";
	}

	@RequestMapping({ "/addProducts" })
	public String addProduct(@RequestParam Map<String, String> requestParams,
			Map<String, Object> model) {
		ArrayList<Dimension> dimensionList = productService.getAllDimension();
		ArrayList<AgeCategory> ageCategoryList = productService
				.getAllAgeCategory();
		ProductForm productForm = new ProductForm();
		model.put("action", "add");
		model.put("pageTitle", "addProduct");
		model.put("dimensionList", dimensionList);
		model.put("ageCategoryList", ageCategoryList);
		model.put("productForm", productForm);
		return "product";
	}

	@RequestMapping(value = "/productSave", method = RequestMethod.POST)
	public String saveProduct(final RedirectAttributes redirectAttributes,
			@RequestParam Map<String, String> requestParams,
			Map<String, Object> model, ProductForm productForm,
			BindingResult result) {
		if ((productForm.getId()).equals("")) {
			Product product = productService
					.getNewProductByProductForm(productForm);
			productService.updateProduct(product);
		} else {
			Product product = productService
					.updateProductByProductForm(productForm);
			productService.updateProduct(product);
		}
		return "redirect:/products";
	}
}