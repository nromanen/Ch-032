package com.softserveinc.orphanagemenu.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import com.softserveinc.orphanagemenu.model.ProductWeight;
import com.softserveinc.orphanagemenu.service.ProductService;

@Controller
public class ProductController {

	@Autowired
	private ProductService productService;

	@RequestMapping({ "/products" })
	public String getList(@CookieValue(value = "sort", defaultValue = "asc") String sortValue, Model model) {
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
		ArrayList <Dimension> dimensionList = productService.getAllDimension();
		ArrayList <AgeCategory> ageCategoryList = productService.getAllAgeCategory();
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
	public String addProduct(@RequestParam Map<String, String> requestParams, Map<String, Object> model) {
		ArrayList <Dimension> dimensionList = productService.getAllDimension();
		ArrayList <AgeCategory> ageCategoryList = productService.getAllAgeCategory();
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
									Map<String, Object> model, 
									ProductForm productForm, 
									BindingResult result) {
		System.out.println(productForm.getIdWeight());
		Product product = productService.getProductByProductForm(productForm);
		
		productService.saveProduct(product);
		return "redirect:/products";
	}
	
	@RequestMapping({ "/testForm" })
	public String testForm(final RedirectAttributes redirectAttributes,
			@RequestParam Map<String, String> requestParams,
			Map<String, Object> model, 
			ProductForm productForm, 
			BindingResult result) {
		
//		Product product = productService.getProductById(3L);
		
		Product product = new Product();
		
		product.setName("soooooooooooooooooooo");
		
//		product.setId(1L);
		
		product.setDimension(productService.getDimensionById(1L));
		
		AgeCategory ageCategory = productService.getAllAgeCategory().get(0);

		
		
		ProductWeight productWeight = new ProductWeight();
		//productWeight.setId(1L);
		
		productWeight.setProduct(product);
		productWeight.setAgeCategory(ageCategory);
		productWeight.setStandartProductQuantity(302D);
		Set<ProductWeight> setProductWeight = new HashSet<ProductWeight>();
		setProductWeight.add(productWeight);
		product.setProductWeight(setProductWeight);
		
//		for (ProductWeight productWeight : product.getProductWeight()){
//			
//			productWeight.setStandartProductQuantity(301D);
//			
//		}
		System.out.println(product.getProductWeight().iterator().next().getStandartProductQuantity().toString());
//		productService.updateProduct(product);// updateProductWeight(productWeight);
		productService.saveProduct(product);
		return "/redirect:products";
	}

}