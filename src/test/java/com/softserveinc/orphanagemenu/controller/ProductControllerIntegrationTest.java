package com.softserveinc.orphanagemenu.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.View;

import com.softserveinc.orphanagemenu.forms.ProductForm;
import com.softserveinc.orphanagemenu.model.AgeCategory;
import com.softserveinc.orphanagemenu.model.Product;
import com.softserveinc.orphanagemenu.service.AgeCategoryService;
import com.softserveinc.orphanagemenu.service.ProductService;
import com.softserveinc.orphanagemenu.validators.ProductValidator;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:unit-test-context/test-context.xml",
		"classpath:unit-test-context/dispatcher-servlet.xml" })
@WebAppConfiguration
public class ProductControllerIntegrationTest {

	MockMvc mockMvc;

	@InjectMocks
	ProductController controller;

	@Mock
	View mockView;

	@Autowired
	ProductService productService;

	@Autowired
	AgeCategoryService ageCategoryService;

	@Autowired
	ProductValidator productValidator;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(controller)
				.setSingleView(mockView).build();
		Mockito.reset(productService);
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
				.build();
	}

	@Test
	public void showProductsTest() throws Exception {
		List<Product> expectedProduct = Arrays.asList(new Product());
		when(productService.getPage(0, 15)).thenReturn(expectedProduct);
		mockMvc.perform(get("/products")).andExpect(status().isOk())
				.andExpect(view().name("products"))
				.andExpect(model().attributeExists("products"))
				.andExpect(model().attribute("products", expectedProduct));
	}

	@Test
	public void searchProductsNoOneFindTest() throws Exception {
		List<Product> expectedProduct = Arrays.asList();
		when(productService.getPage("hello", 0, 15))
				.thenReturn(expectedProduct);
		mockMvc.perform(get("/productsSearch").param("name", "hello"))
				.andExpect(status().isOk()).andExpect(view().name("products"))
				.andExpect(model().attributeExists("products"))
				.andExpect(model().attribute("products", expectedProduct))
				.andExpect(model().attribute("infoMessage", "notFind"));
	}

	@Test
	public void searchProductsHaveFoundedTest() throws Exception {
		List<Product> expectedProducts = Arrays.asList(new Product());
		when(productService.getPage("бу", 0, 15)).thenReturn(expectedProducts);
		mockMvc.perform(get("/productsSearch").param("name", "бу"))
				.andExpect(status().isOk()).andExpect(view().name("products"))
				.andExpect(model().attributeExists("products"))
				.andExpect(model().attribute("products", expectedProducts))
				.andExpect(model().attributeDoesNotExist("infoMessage"));
	}

	@Test
	public void editProductTest() throws Exception {
		ProductForm productForm = new ProductForm();
		when(productService.getProductFormByProductId(1L)).thenReturn(
				productForm);
		mockMvc.perform(get("/editProduct").param("id", "1"))
				.andExpect(status().isOk()).andExpect(view().name("product"))
				.andExpect(model().attributeExists("productForm"))
				.andExpect(model().attribute("productForm", productForm));
	}

	@Test
	public void addProductTest() throws Exception {
		List<AgeCategory> ageCategoryList = Arrays.asList();
		when(ageCategoryService.getAllAgeCategory())
				.thenReturn(ageCategoryList);
		mockMvc.perform(get("/addProduct"))
				.andExpect(status().isOk())
				.andExpect(view().name("product"))
				.andExpect(model().attributeExists("productForm"))
				.andExpect(
						model().attribute("ageCategoryList", ageCategoryList));
	}
}
