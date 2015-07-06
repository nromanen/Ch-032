package com.javagroup.restaurantmenu;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.testng.AssertJUnit;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.javagroup.restaurantmenu.dao.file.DishDAOFile;
import com.javagroup.restaurantmenu.model.Dish;
import com.javagroup.restaurantmenu.model.Group;
import com.javagroup.restaurantmenu.model.Ingredient;
import com.javagroup.restaurantmenu.model.Product;

public class DishDAOFileTest {

	private ArrayList<Dish> dishes;
	public static final String FILE_NAME = "DishDAOFileTest.dat";   

	@BeforeClass
	public void initiate() {
		createSampleDishList();
	}
	
	@AfterTest
	public void releaseResources() throws IOException {
		Files.deleteIfExists(Paths.get(FILE_NAME));
	}

	@Test
	public void getAllDishes() {
		DishDAOFile dishDAOFile = new DishDAOFile(FILE_NAME);
		dishDAOFile.persistAllDishes(dishes);
		List<Dish> actualDishes = dishDAOFile.findAll(); 
		AssertJUnit.assertEquals(dishes, actualDishes);
	}

	@Test
	public void persistAllDishes() {
		DishDAOFile dishDAOFile = new DishDAOFile(FILE_NAME);
		dishDAOFile.persistAllDishes(dishes);
		List<Dish> actualDishes = dishDAOFile.findAll(); 
		AssertJUnit.assertEquals(dishes, actualDishes);
	}
	
	public void createSampleDishList() {
		dishes = new ArrayList<>();

		List<Ingredient> ingredientList1 = new ArrayList<>();
		ingredientList1
				.add(new Ingredient(new Product("Potato", 3, true), 200));
		ingredientList1.add(new Ingredient(new Product("Water", 2, true), 50));
		dishes.add(new Dish("Borsch", Group.FIRST, ingredientList1));

		List<Ingredient> ingredientList2 = new ArrayList<>();
		ingredientList2
				.add(new Ingredient(new Product("Potato", 3, true), 300));
		ingredientList2.add(new Ingredient(new Product("Water", 2, true), 500));
		dishes.add(new Dish("Soup", Group.FIRST, ingredientList2));
	}
}
