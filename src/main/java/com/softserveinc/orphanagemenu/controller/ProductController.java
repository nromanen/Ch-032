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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.softserveinc.orphanagemenu.forms.ProductForm;
import com.softserveinc.orphanagemenu.model.AgeCategory;
import com.softserveinc.orphanagemenu.model.Dimension;
import com.softserveinc.orphanagemenu.model.Product;
import com.softserveinc.orphanagemenu.model.ProductWeight;
import com.softserveinc.orphanagemenu.service.ProductService;
import com.softserveinc.orphanagemenu.validator.user.UserAccountForm;

@Controller
public class ProductController {

	@Autowired
	private ProductService productService;

	@RequestMapping({ "/products" })
	public String getList(@CookieValue(value = "sort", defaultValue = "asc") String sortValue, Model model) {
		if (sortValue.equals("asc")) {
			ArrayList<Product> prod = productService.getAllProduct("asc");
			model.addAttribute("alt", "");
			model.addAttribute("sort", "desc");
			model.addAttribute("products", prod);
		} else {
			ArrayList<Product> prod = productService.getAllProduct("desc");
			model.addAttribute("alt", "-alt");
			model.addAttribute("sort", "asc");
			model.addAttribute("products", prod);
		}
		ArrayList<AgeCategory> ageCategory = productService.getAllCategory();
		model.addAttribute("ageCategory", ageCategory);
		model.addAttribute("pageTitle", "productList");
		return "products";
	}

	@RequestMapping({ "/addProduct" })
	public String addProduct(Model model) {
		ArrayList<Dimension> dimension = productService.getAllDimension();
		ArrayList<AgeCategory> ageCategory = productService.getAllAgeCategory();
		model.addAttribute("dimension", dimension);
		model.addAttribute("ageCategory", ageCategory);
		model.addAttribute("pageTitle", "addProduct");
		return "addProduct";
	}

	@RequestMapping({ "/editProduct" })
	public String editProduct(@RequestParam(value = "id", required = true) Long id, Model model) {
		model.addAttribute("product", productService.getProductById(id));
		ArrayList<Dimension> dimension = productService.getAllDimension();
		ArrayList<AgeCategory> ageCategory = productService.getAllAgeCategory();
		model.addAttribute("dimension", dimension);
		model.addAttribute("ageCategory", ageCategory);
		model.addAttribute("pageTitle", "editProduct");
		return "editProduct";
	}

	@RequestMapping({ "/saveProduct" })
	public String save(@RequestParam("productName") String name, @RequestParam("dimensionId") String dimensionId,
			@RequestParam("weight") List<String> weightList, @RequestParam("productId") String productId,
			@RequestParam("productWeightId") List<String> productWeightId) {
		ArrayList<Double> weights = new ArrayList<Double>();
		for (String weight : weightList) {
			weights.add(Double.parseDouble(weight));
		}
		saveProduct(name, dimensionId, weights, productId, productWeightId);
		return "redirect:/products";
	}

	@RequestMapping({ "/saveAndAddProduct" })
	public String saveAndAdd(@RequestParam("productName") String name, @RequestParam("dimensionId") String dimensionId,
			@RequestParam("weight") List<String> weightList, @RequestParam("productId") String productId,
			@RequestParam("productId") List<String> productWeightId) {
		ArrayList<Double> weights = new ArrayList<Double>();
		for (String weight : weightList) {
			weights.add(Double.parseDouble(weight));
		}
		saveProduct(name, dimensionId, weights, productId, productWeightId);
		return "redirect:/addProduct";
	}

	private void saveProduct(String name, String dimensionId, ArrayList<Double> weights, String productId,
			List<String> productWeightId) {
		Dimension dimension = productService.getDimensionById(Long.parseLong(dimensionId));
		Product prod = new Product();
		prod.setName(name);
		prod.setDimension(dimension);
		if (!(productId.equals("null"))) {
			prod.setId(Long.parseLong(productId));
			productService.updateProduct(prod);
			ArrayList<AgeCategory> ageCategories = productService.getAllAgeCategory();
			for (int i = 0; i < ageCategories.size(); i++) {
				ProductWeight productWeight = new ProductWeight();
				productWeight.setId(Long.parseLong(productWeightId.get(i)));
				productWeight.setAgeCategory(ageCategories.get(i));
				productWeight.setProduct(prod);
				productWeight.setStandartProductQuantity(weights.get(i));
				productService.updateProductWeight(productWeight);
			}
		} else {
			productService.saveProduct(prod);
			Product takenProduct = productService.getProduct(name);
			Long id = takenProduct.getId();
			ArrayList<AgeCategory> ageCategories = productService.getAllAgeCategory();
			for (int i = 0; i < ageCategories.size(); i++) {
				ProductWeight productWeight = new ProductWeight();
				productWeight.setAgeCategory(ageCategories.get(i));
				productWeight.setProduct(productService.getProductById(id));
				productWeight.setStandartProductQuantity(weights.get(i));
				productService.saveProductWeight(productWeight);
			}
		}
	}

	@RequestMapping({ "/editProducts" })
	public String product(@RequestParam Map<String, String> requestParams, Map<String, Object> model) {
		ProductForm productForm = null;
		Long id = Long.parseLong(requestParams.get("id"));
		productForm = productService.getProductFormByProductId(id);
		model.put("action", "save");
		model.put("pageTitle", "editProduct");
		model.put("productForm", productForm);
		return "product";
	}

	@RequestMapping({ "/addProducts" })
	public String addProduct(@RequestParam Map<String, String> requestParams, Map<String, Object> model) {
		ProductForm productForm = null;
		model.put("action", "add");
		model.put("pageTitle", "addProduct");
		model.put("productForm", productForm);
		return "product";
	}
	
	@RequestMapping({ "/testForm" })
	public String testForm(final RedirectAttributes redirectAttributes,
			@RequestParam Map<String, String> requestParams,
			Map<String, Object> model, 
			ProductForm productForm, 
			BindingResult result) {
		System.out.println("------------------------------"+productForm.getWeight());
		return "product";
	}

}