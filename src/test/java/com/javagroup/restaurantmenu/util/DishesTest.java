package com.javagroup.restaurantmenu.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.testng.AssertJUnit;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.javagroup.restaurantmenu.model.Dish;
import com.javagroup.restaurantmenu.model.Group;
import com.javagroup.restaurantmenu.model.Ingredient;
import com.javagroup.restaurantmenu.model.Product;

public class DishesTest {

	private ArrayList<Dish> dishesAsList;
	private String dishesAsJson;
	
	@BeforeClass
	public void initiate() {
		createEquivalentDishListAndJson();
	}
	
	@Test
	public void asJson() {
		String actualJson = null;
		try {
			actualJson = Dishes.asJson(dishesAsList);
		} catch (JsonProcessingException ex) {
			System.out.println(ex);
		}
		AssertJUnit.assertEquals(dishesAsJson, actualJson);
	}

	@Test
	public void asList() {
		List<Dish> actualDishList = null;
		try {
			actualDishList = Dishes.asList(dishesAsJson);
		} catch (IOException ex) {
			System.out.println(ex);
		}
		AssertJUnit.assertEquals(dishesAsList, actualDishList);
	}
	
	public void createEquivalentDishListAndJson(){
		dishesAsList = new ArrayList<>();

		List<Ingredient> ingredientList1 = new ArrayList<>();
		ingredientList1
				.add(new Ingredient(new Product("Potato", 3, true), 200));
		ingredientList1.add(new Ingredient(new Product("Water", 2, true), 50));
		dishesAsList.add(new Dish("Borsch", Group.FIRST, ingredientList1));

		List<Ingredient> ingredientList2 = new ArrayList<>();
		ingredientList2
				.add(new Ingredient(new Product("Potato", 3, true), 300));
		ingredientList2.add(new Ingredient(new Product("Water", 2, true), 500));
		dishesAsList.add(new Dish("Soup", Group.FIRST, ingredientList2));
		
		dishesAsJson = "[{\"name\":\"Borsch\",\"group\":\"FIRST\","
				+ "\"ingredients\":[{\"product\":{\"name\":\"Potato\","
				+ "\"price\":3.0,\"available\":true},\"quantity\":200},"
				+ "{\"product\":{\"name\":\"Water\",\"price\":2.0,"
				+ "\"available\":true},\"quantity\":50}]},"
				+ "{\"name\":\"Soup\",\"group\":\"FIRST\","
				+ "\"ingredients\":[{\"product\":{\"name\":\"Potato\","
				+ "\"price\":3.0,\"available\":true},\"quantity\":300},"
				+ "{\"product\":{\"name\":\"Water\",\"price\":2.0,"
				+ "\"available\":true},\"quantity\":500}]}]";
				
	}
}
