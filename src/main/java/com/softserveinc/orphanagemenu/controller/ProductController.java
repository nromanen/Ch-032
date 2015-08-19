package com.softserveinc.orphanagemenu.controller;

import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.context.ApplicationContext;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.softserveinc.orphanagemenu.forms.ProductForm;
import com.softserveinc.orphanagemenu.model.AgeCategory;
import com.softserveinc.orphanagemenu.model.Dimension;
import com.softserveinc.orphanagemenu.model.Product;
import com.softserveinc.orphanagemenu.service.DimensionService;
import com.softserveinc.orphanagemenu.service.ProductService;
import com.softserveinc.orphanagemenu.service.AgeCategoryService;
import com.softserveinc.orphanagemenu.validators.ProductValidator;

@Controller
public class ProductController {

	@Autowired
	private ProductService productService;

	@Autowired
	private AgeCategoryService ageCategoryService;

	@Autowired
	private DimensionService dimensionService;

	@Autowired
	private ProductValidator productValidator;

	@Autowired
	ApplicationContext context;

	int count = 0;

	@RequestMapping({ "/products" })
	public String getList(Model model) {
		List<Product> products = productService.getAllProductDtoSorted();
		List<AgeCategory> ageCategory = ageCategoryService.getAllAgeCategory();
		model.addAttribute("ageCategory", ageCategory);
		model.addAttribute("products", products);
		model.addAttribute("pageTitle", "productList");
		return "products";
	}

	@RequestMapping({ "/editProduct" })
	public String product(@RequestParam Map<String, String> requestParams,
			Map<String, Object> model) {
		List<Dimension> dimensionList = dimensionService.getAllDimension();
		List<AgeCategory> ageCategoryList = ageCategoryService
				.getAllAgeCategory();
		ProductForm productForm = productService.getProductFormByProductId(Long
				.parseLong(requestParams.get("id")));
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
	public String addProduct(Map<String, Object> model) {
		List<Dimension> dimensionList = dimensionService.getAllDimension();
		List<AgeCategory> ageCategoryList = ageCategoryService
				.getAllAgeCategory();
		ProductForm productForm = new ProductForm();
		model.put("action", "save");
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
		productForm.setName(productForm.getName().trim());
		productForm.setName(productForm.getName().replaceAll("\\s+", " "));
		if (count > 0) {
		} else {
			productValidator.validate(productForm, result);
			if (result.hasErrors()) {

				List<Dimension> dimensionList = dimensionService
						.getAllDimension();
				List<AgeCategory> ageCategoryList = ageCategoryService
						.getAllAgeCategory();

				model.put("action", "save");
				model.put("actionTwo", "addAndSave");
				model.put("pageTitle", "addProduct");
				model.put("dimensionList", dimensionList);
				model.put("ageCategoryList", ageCategoryList);
				model.put("productForm", productForm);
				model.put("validationMessages", getAllValidationMessagesAsMap());
				return "product";
			}
		}
		count++;
		Product product;
		for (Map.Entry<Long, String> weight : productForm.getWeightList()
				.entrySet()) {
			weight.setValue(weight.getValue().replace(",", "."));
			weight.setValue(Double.toString(Double.valueOf(new DecimalFormat(
					"#.##").format(Double.parseDouble(weight.getValue())))));
		}
		if (("").equals(productForm.getId())) {
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
		if ("true".equals(requestParams.get("addNewProduct"))) {
			redirectAttributes.addFlashAttribute("infoMessage",
					"saveProductSuccessful");
			return "redirect:/addProduct";
		}
		return "redirect:/products";
	}

	private Set<String> getAllValidationMessagesAsMap() {
		Set<String> messages = new HashSet<>();
		messages.add("fieldEmpty");
		messages.add("productNameTooShort");
		messages.add("productNameTooLong");
		messages.add("productNameIllegalCharacters");
		messages.add("productNormEmpty");
		messages.add("productNormTooShort");
		messages.add("productNormTooLong");
		messages.add("weightIllegalCharacters");
		messages.add("submitChanges");
		messages.add("yes");
		messages.add("no");
		messages.add("exitConfirmation");
		return messages;
	}
}
