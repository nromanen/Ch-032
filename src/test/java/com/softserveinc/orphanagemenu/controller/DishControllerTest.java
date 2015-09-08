
package com.softserveinc.orphanagemenu.controller;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {	"classpath:unit-test-context/test-context.xml", 
									"classpath:unit-test-context/dispatcher-servlet.xml"})
@WebAppConfiguration
public class DishControllerTest {
	
	private MockMvc mockMvc;

	@Autowired
	@Qualifier("dishService")
	private DishService dishService;
	
	@Autowired
	@Qualifier("ageCategoryService")
	private AgeCategoryService ageCategoryService;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Before
	public void setUp() throws Exception {
		
		Mockito.reset(ageCategoryService);
		Mockito.reset(dishService);
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	@Test
	public void getAllDishTest() throws Exception {
		
		when(dishService.getAllDishes()).thenReturn(Arrays.asList(new Dish(), new Dish()));
		
		mockMvc.perform(get("/dishlist"))
			.andExpect(status().isOk())
			.andExpect(view().name("dishlist"))
			.andExpect(model().attributeExists("dishes"))
			.andExpect(model().attribute("dishes", hasSize(2)));
	}
	
	@Test
	public void addDishComponentsTest() throws Exception {
		
		List<AgeCategory> expectedAgeCategoryList = Arrays.asList(new AgeCategory(), new AgeCategory());
		when(ageCategoryService.getAllAgeCategory()).thenReturn(expectedAgeCategoryList);
		
		mockMvc.perform(get("/addcomponent"))
				.andExpect(status().isOk())
				.andExpect(view().name("addcomponent"))
				.andExpect(model().attributeExists("category"))
				.andExpect(model().attribute("category", expectedAgeCategoryList));
				
		
	}

	
	
	
}

