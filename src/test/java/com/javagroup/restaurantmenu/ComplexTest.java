package com.javagroup.restaurantmenu;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import com.javagroup.restaurantmenu.model.Complex;
import com.javagroup.restaurantmenu.model.Dish;
import com.javagroup.restaurantmenu.model.Group;
import com.javagroup.restaurantmenu.model.Ingredient;
import com.javagroup.restaurantmenu.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ComplexTest {
	private Complex complex;
	private Complex complex2;

	@BeforeClass
	public void beforeClass() {
		Dish firstDish = new Dish();
		List<Ingredient> ingredients1 = new ArrayList<Ingredient>();
		firstDish.setName("Borsch");
		firstDish.setGroup(Group.FIRST);
		ingredients1.add(new Ingredient(new Product("Potato", 15, true), 200));
		ingredients1.add(new Ingredient(new Product("Water", 5, true), 20));
		ingredients1.add(new Ingredient(new Product("Salt", 1, true), 100));
		firstDish.setIngredients(ingredients1);

		Dish secondDish = new Dish();
		List<Ingredient> ingredients2 = new ArrayList<Ingredient>();
		secondDish.setName("Bliny");
		secondDish.setGroup(Group.SECOND);
		ingredients2.add(new Ingredient(new Product("Milk", 15, true), 200));
		ingredients2.add(new Ingredient(new Product("Egg", 50, true), 20));
		ingredients2.add(new Ingredient(new Product("Sugar", 1, true), 100));
		secondDish.setIngredients(ingredients2);

		Dish drink = new Dish();
		List<Ingredient> ingredients3 = new ArrayList<Ingredient>();
		drink.setName("Compote");
		drink.setGroup(Group.DRINK);
		ingredients3.add(new Ingredient(new Product("Fruit", 35, true), 100));
		ingredients3.add(new Ingredient(new Product("Water", 5, true), 300));
		ingredients3.add(new Ingredient(new Product("Sugar", 1, true), 100));
		drink.setIngredients(ingredients3);

		complex = new Complex("Premium", firstDish, secondDish, drink);
		complex2 = new Complex();
		complex2.setName(complex.getName());
		complex2.setFirstDish(complex.getFirstDish());
		complex2.setSecondDish(complex.getSecondDish());
		complex2.setDrink(complex.getDrink());
	}

	@AfterClass
	public void afterClass() {
		complex = null;
		complex2 = null;
	}

	@Test
	public void testGetPrice() {
		double expectedPrice = 12400;
		double resultPrice = complex.getPrice();
		assertTrue(expectedPrice == resultPrice);
	}

	@Test
	public void testToString() {
		String expectedString = "\n\nComplex name: Premium"
				+ "\n firstDish=Borsch (3200.0) usd"
				+ "\n secondDish=Bliny (4100.0) usd"
				+ "\n drink=Compote (5100.0) usd" + "\n total=12400.0 usd";
		String resultString = complex2.toString();
		assertEquals(expectedString, resultString);
	}

}
