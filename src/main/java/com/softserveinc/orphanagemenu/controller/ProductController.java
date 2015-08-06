package com.softserveinc.orphanagemenu.controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.context.ApplicationContext;
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
import com.softserveinc.orphanagemenu.validators.ProductValidator;

@Controller
public class ProductController {

	@Autowired
	private ProductService productService;

	@Autowired
	private ProductValidator productValidator;

	@Autowired
	ApplicationContext context;

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

	@RequestMapping({ "/editProduct" })
	public String product(@RequestParam Map<String, String> requestParams,
			Map<String, Object> model) {
		ProductForm productForm = null;
		ArrayList<Dimension> dimensionList = productService.getAllDimension();
		ArrayList<AgeCategory> ageCategoryList = productService
				.getAllAgeCategory();
		Long id = Long.parseLong(requestParams.get("id"));
		productForm = productService.getProductFormByProductId(id);
		model.put("buttonDisplay", "display: none;");
		model.put("action", "save");
		model.put("pageTitle", "editProduct");
		model.put("productForm", productForm);
		model.put("dimensionList", dimensionList);
		model.put("ageCategoryList", ageCategoryList);
		model.put("validationMessages", getAllValidationMessagesAsMap());
		return "product";
	}

	@RequestMapping({ "/addProduct" })
	public String addProduct(@RequestParam Map<String, String> requestParams,
			Map<String, Object> model) {
		ArrayList<Dimension> dimensionList = productService.getAllDimension();
		ArrayList<AgeCategory> ageCategoryList = productService
				.getAllAgeCategory();
		ProductForm productForm = new ProductForm();
		model.put("action", "add");
		model.put("actionTwo", "addAndSave");
		model.put("pageTitle", "addProduct");
		model.put("dimensionList", dimensionList);
		model.put("ageCategoryList", ageCategoryList);
		model.put("productForm", productForm);
		model.put("validationMessages", getAllValidationMessagesAsMap());
		return "product";
	}

	@RequestMapping(value = "/saveProduct", method = RequestMethod.POST)
	public String saveProduct(final RedirectAttributes redirectAttributes,
			@RequestParam Map<String, String> requestParams,
			Map<String, Object> model, ProductForm productForm,
			BindingResult result) {
		productValidator.validate(productForm, result);
		if (result.hasErrors()) {
			ArrayList<Dimension> dimensionList = productService
					.getAllDimension();
			ArrayList<AgeCategory> ageCategoryList = productService
					.getAllAgeCategory();
			model.put("action", "add");
			model.put("actionTwo", "addAndSave");
			model.put("pageTitle", "addProduct");
			model.put("dimensionList", dimensionList);
			model.put("ageCategoryList", ageCategoryList);
			model.put("productForm", productForm);
			return "product";
		}
		Product product;
		productForm.setName(productForm.getName().trim());
		productForm.setName(productForm.getName().replaceAll("\\s+", " "));
		for (Map.Entry<Long, String> weight : productForm.getWeightList()
				.entrySet()) {
			weight.setValue(weight.getValue().replace(",", "."));
			weight.setValue(Double.toString(Double.valueOf(new DecimalFormat(
					"#.##").format(((Double.parseDouble(weight.getValue())))))));
			System.out.println(weight.getValue());
		}
		if ((productForm.getId()).equals("")) {
			product = productService.getNewProductFromProductForm(productForm);
			productService.updateProduct(product);
			redirectAttributes.addFlashAttribute("infoMessage",
					"saveProductSuccessful");
		} else {
			product = productService.updateProductByProductForm(productForm);
			productService.updateProduct(product);
			redirectAttributes.addFlashAttribute("infoMessage",
					"updateProductSuccessful");
		}
		if (requestParams.get("addNewProduct").equals("true")) {
			redirectAttributes.addFlashAttribute("infoMessage",
					"saveProductSuccessful");
			return "redirect:/addProduct";
		}
		return "redirect:/products";
	}

	private Map<String, String> getAllValidationMessagesAsMap() {
		Map<String, String> messages = new HashMap<>();
		messages.put(
				"fieldEmpty",
				context.getMessage("fieldEmpty", null,
						LocaleContextHolder.getLocale()));
		messages.put("productNameTooShort", context.getMessage(
				"productNameTooShort", null, LocaleContextHolder.getLocale()));
		messages.put("productNameTooLong", context.getMessage(
				"productNameTooLong", null, LocaleContextHolder.getLocale()));
		messages.put("productNameIllegalCharacters", context.getMessage(
				"productNameIllegalCharacters", null,
				LocaleContextHolder.getLocale()));
		messages.put("productNormsMustContainNumbers", context.getMessage(
				"productNormsMustContainNumbers", null,
				LocaleContextHolder.getLocale()));
		messages.put("productNormEmpty", context.getMessage("productNormEmpty",
				null, LocaleContextHolder.getLocale()));
		messages.put("productNormTooShort", context.getMessage(
				"productNormTooShort", null, LocaleContextHolder.getLocale()));
		messages.put("productNormTooLong", context.getMessage(
				"productNormTooLong", null, LocaleContextHolder.getLocale()));
		return messages;
	}
}