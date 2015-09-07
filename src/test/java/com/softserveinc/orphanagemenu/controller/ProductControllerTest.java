package com.softserveinc.orphanagemenu.controller;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.softserveinc.orphanagemenu.forms.ProductForm;
import com.softserveinc.orphanagemenu.model.Product;
import com.softserveinc.orphanagemenu.service.AgeCategoryService;
import com.softserveinc.orphanagemenu.service.DimensionService;
import com.softserveinc.orphanagemenu.service.ProductService;
import com.softserveinc.orphanagemenu.validators.ProductValidator;


public class ProductControllerTest {
	ProductForm productForm = new ProductForm();
	RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
	ProductValidator productValidator = mock(ProductValidator.class);
	BindingResult bindingResult = mock(BindingResult.class);
	ProductService productService = mock(ProductService.class);
	AgeCategoryService ageCategoryService = mock(AgeCategoryService.class);
	DimensionService dimensionService = mock(DimensionService.class);
	Map<String, String> requestParams = new HashMap<>();
	@SuppressWarnings("unchecked")
	Map<String, Object> model = mock(Map.class);
	ProductController productController = new ProductController();
	
	@Before
	public void setUp() throws Exception {
		requestParams.put("addNewProduct", "true");
		productForm = new ProductForm();
		productForm.setName("hello");
		productForm.setId("1");
		Map<Long, String> weight = new HashMap<>();
		weight.put(1L, "150,2");
		productForm.setWeightList(weight);
		productController = new ProductController();
		ReflectionTestUtils.setField(productController, "productService",
				productService);
		ReflectionTestUtils.setField(productController, "productValidator",
				productValidator);
		ReflectionTestUtils.setField(productController, "ageCategoryService",
				ageCategoryService);
		ReflectionTestUtils.setField(productController, "dimensionService",
				dimensionService);
		when(bindingResult.hasErrors()).thenReturn(false);
	}
	
	@Test
	public void saveProductValidationTrueTest() throws Exception {
		when(bindingResult.hasErrors()).thenReturn(true);
		productController.saveProduct(redirectAttributes, requestParams, model, productForm,
				bindingResult);
		verify(productValidator).validate(productForm, bindingResult);
		verify(productService, never()).updateProduct(any(Product.class));
	}

	@Test
	public void saveProductValidationFalseTest() throws Exception {
		productController.saveProduct(redirectAttributes, requestParams, model,
				productForm, bindingResult);
		verify(productValidator).validate(productForm, bindingResult);
	}
	
	@Test
	public void saveProductValidationIdFormNullTest() throws Exception {
		productForm.setId("");
		requestParams.put("addNewProduct", "false");
		productController.saveProduct(redirectAttributes, requestParams, model,
				productForm, bindingResult);
		verify(productValidator).validate(productForm, bindingResult);
	}
}