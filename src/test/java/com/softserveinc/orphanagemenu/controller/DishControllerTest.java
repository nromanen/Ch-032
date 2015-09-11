package com.softserveinc.orphanagemenu.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.softserveinc.orphanagemenu.model.AgeCategory;
import com.softserveinc.orphanagemenu.model.Dish;
import com.softserveinc.orphanagemenu.service.AgeCategoryService;
import com.softserveinc.orphanagemenu.service.DishService;

import java.util.HashMap;
import java.util.Map;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.servlet.View;

import com.softserveinc.orphanagemenu.json.DishResponseBody;
import com.softserveinc.orphanagemenu.model.Component;
import com.softserveinc.orphanagemenu.model.Product;
import com.softserveinc.orphanagemenu.service.ComponentService;
import com.softserveinc.orphanagemenu.service.ProductService;
import com.softserveinc.orphanagemenu.validators.DishValidator;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:unit-test-context/test-context.xml",
		"classpath:unit-test-context/dispatcher-servlet.xml" })
@WebAppConfiguration

public class DishControllerTest {

	MockMvc mockMvc;

	@InjectMocks
	DishController controller;

	@Mock
	View mockView;

	@Autowired
	DishService dishService;

	@Autowired
	AgeCategoryService ageCategoryService;

	@Autowired
	@Qualifier("componentService")
	ComponentService componentService;

	@Autowired
	ProductService productService;

	@Autowired
	DishValidator dishValidator;
	
	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setUp() throws Exception {

		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(controller)
				.setSingleView(mockView).build();
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
				.build();
		Mockito.reset(dishService);
	}

	@Test
	public void getAllDishTest() throws Exception {

		when(dishService.getAllDishes()).thenReturn(
				Arrays.asList(new Dish(), new Dish()));

		mockMvc.perform(get("/dishlist")).andExpect(status().isOk())
				.andExpect(view().name("dishlist"))
				.andExpect(model().attributeExists("dishes"))
				.andExpect(model().attribute("dishes", hasSize(2)));
	}

	@Test
	public void addDishComponentsTest() throws Exception {

		Dish dish = null;
		List<AgeCategory> expectedAgeCategoryList = Arrays.asList(
				new AgeCategory(), new AgeCategory());
		List<Component> excpectedComponentList = Arrays.asList(new Component(),
				new Component());
		List<Product> excpectedProductList = Arrays.asList(new Product(),
				new Product());

		when(ageCategoryService.getAllAgeCategory()).thenReturn(
				expectedAgeCategoryList);
		when(componentService.getAllComponentsByDishId(dish)).thenReturn(
				excpectedComponentList);
		when(productService.getAllProductDtoSorted()).thenReturn(
				excpectedProductList);

		mockMvc.perform(get("/addcomponent"))
				.andExpect(status().isOk())
				.andExpect(view().name("addcomponent"))
				.andExpect(model().attributeExists("category"))
				.andExpect(
						model().attribute("category", expectedAgeCategoryList))
				.andExpect(model().attributeExists("components"))
				.andExpect(
						model().attribute("components", excpectedComponentList))
				.andExpect(model().attributeExists("products"))
				.andExpect(model().attribute("products", excpectedProductList));

	}

	@Test
	public void addComponentQuantityToAgeCategoryTest() throws Exception {

		Map<Long, Double> expectedMap = new HashMap<Long, Double>();
		expectedMap.put(1L, 10.20);
		expectedMap.put(2L, 10.15);
		DishResponseBody dishResponse = new DishResponseBody();
		Component component = new Component();

		when(dishService.parseJsonValue(dishResponse)).thenReturn(expectedMap);
		when(componentService.setAllComponentValue(dishResponse, expectedMap))
				.thenReturn(component);

		this.mockMvc.perform(
				post("/addcomponents").contentType(MediaType.APPLICATION_JSON)
						.content("{\"json\":\"request to be send\"}"))
				.andExpect(status().isOk());
	}

//	@Test
//	public void saveDishTest() throws Exception {
//		
//		this.mockMvc.perform(
//				post("/saveDish").contentType(MediaType.APPLICATION_JSON)
//						.content("{\"json\":\"request to be send\"}"))
//				.andExpect(status().isOk());
//	
//	}
}
