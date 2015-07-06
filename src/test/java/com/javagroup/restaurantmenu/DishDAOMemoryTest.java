package com.javagroup.restaurantmenu;

import static org.testng.AssertJUnit.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

import com.javagroup.restaurantmenu.dao.memory.DishDAOMemory;
import com.javagroup.restaurantmenu.model.Dish;
import com.javagroup.restaurantmenu.model.Group;
import com.javagroup.restaurantmenu.model.Ingredient;
import com.javagroup.restaurantmenu.model.Product;

public class DishDAOMemoryTest {
	private List<Dish> dishList;
	private List<Dish> listAvailbleDishByFIRSTGroup;
	private List<Dish> listAvailbleDishBySECONDGroup;
	private List<Dish> listDishByDRINKGroup;

	@BeforeClass
	public void beforeClass() {
		dishList = new ArrayList<>();

		List<Ingredient> ingredientList1 = new ArrayList<>();
		ingredientList1
				.add(new Ingredient(new Product("Potato", 3, true), 200));
		ingredientList1.add(new Ingredient(new Product("Water", 2, true), 50));
		dishList.add(new Dish("Borsch", Group.FIRST, ingredientList1));

		List<Ingredient> ingredientList2 = new ArrayList<>();
		ingredientList2
				.add(new Ingredient(new Product("Potato", 3, true), 300));
		ingredientList2.add(new Ingredient(new Product("Water", 2, true), 500));
		dishList.add(new Dish("Soup", Group.FIRST, ingredientList2));

		List<Ingredient> ingredientList3 = new ArrayList<>();
		ingredientList3
				.add(new Ingredient(new Product("Potato", 3, true), 400));
		ingredientList3.add(new Ingredient(new Product("Water", 2, true), 500));
		dishList.add(new Dish("Bliny", Group.SECOND, ingredientList3));

		List<Ingredient> ingredientList4 = new ArrayList<>();
		ingredientList4
				.add(new Ingredient(new Product("Potato", 3, true), 200));
		ingredientList4.add(new Ingredient(new Product("Water", 2, true), 500));
		dishList.add(new Dish("Tea", Group.DRINK, ingredientList4));

		List<Ingredient> ingredientList5 = new ArrayList<>();
		ingredientList5.add(new Ingredient(new Product("Fruit", 3, true), 200));
		ingredientList5
				.add(new Ingredient(new Product("Water", 2, false), 500));
		dishList.add(new Dish("Compot", Group.DRINK, ingredientList5));

		List<Ingredient> ingredientList6 = new ArrayList<>();
		ingredientList6
				.add(new Ingredient(new Product("Potato", 3, true), 400));
		ingredientList6.add(new Ingredient(new Product("Water", 2, true), 500));
		dishList.add(new Dish("Kasha", Group.SECOND, ingredientList6));

		List<Ingredient> ingredientList7 = new ArrayList<>();
		ingredientList7.add(new Ingredient(new Product("Meat", 3, false), 400));
		ingredientList7.add(new Ingredient(new Product("Water", 2, true), 500));
		dishList.add(new Dish("Meat", Group.SECOND, ingredientList7));

		listAvailbleDishByFIRSTGroup = new ArrayList<>();
		List<Ingredient> ingredientList8 = new ArrayList<>();
		ingredientList8
				.add(new Ingredient(new Product("Potato", 3, true), 200));
		ingredientList8.add(new Ingredient(new Product("Water", 2, true), 50));
		listAvailbleDishByFIRSTGroup.add(new Dish("Borsch", Group.FIRST,
				ingredientList8));

		List<Ingredient> ingredientList9 = new ArrayList<>();
		ingredientList9
				.add(new Ingredient(new Product("Potato", 3, true), 300));
		ingredientList9.add(new Ingredient(new Product("Water", 2, true), 500));
		listAvailbleDishByFIRSTGroup.add(new Dish("Soup", Group.FIRST,
				ingredientList9));

		listAvailbleDishBySECONDGroup = new ArrayList<>();
		List<Ingredient> ingredientList10 = new ArrayList<>();
		ingredientList10
				.add(new Ingredient(new Product("Potato", 3, true), 400));
		ingredientList10
				.add(new Ingredient(new Product("Water", 2, true), 500));
		listAvailbleDishBySECONDGroup.add(new Dish("Bliny", Group.SECOND,
				ingredientList10));

		List<Ingredient> ingredientList11 = new ArrayList<>();
		ingredientList11
				.add(new Ingredient(new Product("Potato", 3, true), 400));
		ingredientList11
				.add(new Ingredient(new Product("Water", 2, true), 500));
		listAvailbleDishBySECONDGroup.add(new Dish("Kasha", Group.SECOND,
				ingredientList11));

		listDishByDRINKGroup = new ArrayList<>();
		List<Ingredient> ingredientList12 = new ArrayList<>();
		ingredientList12
				.add(new Ingredient(new Product("Potato", 3, true), 200));
		ingredientList12
				.add(new Ingredient(new Product("Water", 2, true), 500));
		listDishByDRINKGroup
				.add(new Dish("Tea", Group.DRINK, ingredientList12));

		List<Ingredient> ingredientList13 = new ArrayList<>();
		ingredientList13
				.add(new Ingredient(new Product("Fruit", 3, true), 200));
		ingredientList13
				.add(new Ingredient(new Product("Water", 2, false), 500));
		listDishByDRINKGroup.add(new Dish("Compot", Group.DRINK,
				ingredientList13));

	}

	@AfterClass
	public void afterClass() {
		dishList = null;
		listAvailbleDishByFIRSTGroup = null;
		listAvailbleDishBySECONDGroup = null;
		listDishByDRINKGroup = null;
	}

	@Test
	public void testGetAllAvailableDishesByGroup() {
		List<Dish> expectedFIRSTGroup = listAvailbleDishByFIRSTGroup;
		List<Dish> expectedSECONDGroup = listAvailbleDishBySECONDGroup;
		List<Dish> resultFIRSTGroup = new DishDAOMemory(dishList)
				.getAllAvailableDishesByGroup(Group.FIRST);
		List<Dish> resultSECONDGroup = new DishDAOMemory(dishList)
				.getAllAvailableDishesByGroup(Group.SECOND);
		assertEquals(expectedFIRSTGroup, resultFIRSTGroup);
		assertEquals(expectedSECONDGroup, resultSECONDGroup);
	}

	@Test
	public void testGetAllDishes() {
		List<Dish> expected = dishList;
		List<Dish> result = new DishDAOMemory(dishList).findAll();
		assertEquals(expected, result);
	}

	@Test
	public void testGetAllDishesByGroup() {
		List<Dish> expected = listDishByDRINKGroup;
		List<Dish> result = new DishDAOMemory(dishList)
				.getAllDishesByGroup(Group.DRINK);
		assertEquals(expected, result);
	}

}
