package com.javagroup.restaurantmenu;

import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

import com.javagroup.restaurantmenu.model.Dish;
import com.javagroup.restaurantmenu.model.Group;
import com.javagroup.restaurantmenu.model.Ingredient;
import com.javagroup.restaurantmenu.model.Product;

public class DishTest {
	private Dish dish;
	private Dish dish2;
	private Dish dish3;
	private Dish dish4;
	private Dish dish5;
	private Dish dish6;
	private Dish dish7;

	@BeforeTest
	public void beforeTest() {
		dish = new Dish();
		List<Ingredient> ingredients = new ArrayList<Ingredient>();
		ingredients.add(new Ingredient(new Product("Potato", 15, true), 200));
		ingredients.add(new Ingredient(new Product("Water", 5, true), 20));
		ingredients.add(new Ingredient(new Product("Salt", 1, true), 100));
		dish = new Dish("Borsch", Group.FIRST, ingredients);

		dish2 = new Dish();
		dish2.setName(dish.getName());
		dish2.setGroup(dish.getGroup());
		dish2.setIngredients(dish.getIngredients());

		dish3 = new Dish();
		List<Ingredient> ingredients2 = new ArrayList<Ingredient>();
		ingredients2.add(new Ingredient(new Product("Potato", 15, true), 200));
		ingredients2.add(new Ingredient(new Product("Water", 5, true), 20));
		ingredients2.add(new Ingredient(new Product("Salt", 1, false), 100));
		dish3 = new Dish("Soup", Group.FIRST, ingredients2);

		dish4 = new Dish();
		List<Ingredient> ingredients3 = new ArrayList<Ingredient>();
		ingredients3.add(new Ingredient(new Product("Potato", 15, true), 200));
		ingredients3.add(new Ingredient(new Product("Water", 5, true), 20));
		ingredients3.add(new Ingredient(new Product("Salt", 1, false), 100));
		ingredients3.add(new Ingredient(new Product("Grain", 1, false), 100));
		dish4 = new Dish("Borsch", Group.FIRST, ingredients3);

		dish5 = new Dish();
		List<Ingredient> ingredients4 = new ArrayList<Ingredient>();
		ingredients4.add(new Ingredient(new Product("Potato", 15, true), 300));
		ingredients4.add(new Ingredient(new Product("Water", 5, true), 20));
		ingredients4.add(new Ingredient(new Product("Salt", 1, false), 100));
		ingredients4.add(new Ingredient(new Product("Grain", 1, false), 100));
		dish5 = new Dish("Borsch", Group.FIRST, ingredients4);

		dish6 = new Dish();
		List<Ingredient> ingredients5 = new ArrayList<Ingredient>();
		ingredients5.add(new Ingredient(new Product("Water", 5, true), 20));
		ingredients5.add(new Ingredient(new Product("Salt", 1, false), 100));
		ingredients5.add(new Ingredient(new Product("Grain", 1, false), 100));
		dish6 = new Dish("Borsch", Group.FIRST, ingredients5);

		dish7 = new Dish();
		List<Ingredient> ingredients7 = new ArrayList<Ingredient>();
		ingredients7.add(new Ingredient(new Product("Potato", 15, true), 200));
		ingredients7.add(new Ingredient(new Product("Water", 5, true), 20));
		ingredients7.add(new Ingredient(new Product("Salt", 1, true), 100));
		dish7 = new Dish("Borsch", Group.FIRST, ingredients7);
	}

	@AfterTest
	public void afterTest() {
		dish = null;
		dish2 = null;
		dish3 = null;
		dish4 = null;
		dish5 = null;
		dish6 = null;
	}

	@Test
	public void testGetPrice() {
		double expectedPrice = 3200;
		double resultPrice = dish.getPrice();
		assertTrue(expectedPrice == resultPrice);
	}

	@Test
	public void testToString() {
		String expectedString = "Borsch (3200.0) usd";
		String resultString = dish2.toString();
		assertEquals(expectedString, resultString);
	}

	@Test
	public void testGetAvailable() {
		assertTrue(dish2.getAvailable());
		assertFalse(dish3.getAvailable());
	}

	@Test
	public void testAddNewIngredient() {
		Dish expected = dish4;
		Dish result = dish7;
		Ingredient ingredient = new Ingredient(new Product("Grain", 1, false),
				100);
		result.addIngredient(ingredient);
		assertEquals(expected, result);
	}

	@Test
	public void testAddIngredient() {
		Dish expected = dish5;
		Dish result = dish4;
		Ingredient ingredient = new Ingredient(new Product("Potato", 15, true),
				100);
		result.addIngredient(ingredient);
		assertEquals(expected, result);
	}

	@Test
	public void testRemoveIngredient() {
		Dish expected = dish6;
		Dish result = dish4;
		Ingredient ingredient = new Ingredient(new Product("Potato", 15, true),
				200);
		result.removeIngredient(ingredient);
		assertEquals(expected, result);
	}

	@Test
	public void testRemoveIngredientByName() {
		Dish expected = dish6;
		Dish result = dish4;
		result.removeIngredient("Potato");
		assertEquals(expected, result);
	}

}
