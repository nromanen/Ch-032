package com.javagroup.restaurantmenu;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import com.javagroup.restaurantmenu.dao.memory.DishDAOMemory;
import com.javagroup.restaurantmenu.model.Complex;
import com.javagroup.restaurantmenu.model.Dish;
import com.javagroup.restaurantmenu.model.Group;
import com.javagroup.restaurantmenu.model.Ingredient;
import com.javagroup.restaurantmenu.model.Menu;
import com.javagroup.restaurantmenu.model.Product;

public class MenuTest {

  @Test
  public void getAllComplexes() {

	List<Dish> firstList = getSampleFirstDishes();
	List<Dish> secondList = getSampleSecondDishes();
	List<Dish> drinkList = getSampleDrinkDishes();

	List<Complex> expectedComplexList = new ArrayList<Complex>();
	expectedComplexList.add(new Complex("complex 1", 
			firstList.get(0), secondList.get(0), drinkList.get(0)));
	expectedComplexList.add(new Complex("complex 2", 
			firstList.get(1), secondList.get(0), drinkList.get(0)));
	
	DishDAOMemory mockedDishDAO = mock(DishDAOMemory.class);
	when(mockedDishDAO.getAllDishesByGroup(Group.FIRST))
						.thenReturn(firstList);
	when(mockedDishDAO.getAllDishesByGroup(Group.SECOND))
						.thenReturn(secondList);
	when(mockedDishDAO.getAllDishesByGroup(Group.DRINK))
						.thenReturn(drinkList);
	List<Complex> actualComplexList = new Menu()
										.getAllComplexes(mockedDishDAO);
	
	AssertJUnit.assertEquals(expectedComplexList, actualComplexList);
  }

  @Test
  public void getComplexListByPriceLimit() {
    throw new RuntimeException("Test not implemented");
  }
  
  private List<Dish> getSampleFirstDishes(){
	  List<Dish> dishList = new ArrayList<>();
	  
	  List<Ingredient> ingredientList1 = new ArrayList<>();
      ingredientList1.add(new Ingredient(
      		new Product("Potato", 3, true), 200));
      ingredientList1.add(new Ingredient(
      		new Product("Water", 2, true), 50));
      dishList.add(new Dish("Borsch", Group.FIRST, ingredientList1));
      
      List<Ingredient> ingredientList2 = new ArrayList<>();
      ingredientList2.add(new Ingredient(
      		new Product("Potato", 3, true), 300));
      ingredientList2.add(new Ingredient(new Product("Water", 2, true), 500));
      dishList.add(new Dish("Soup", Group.FIRST, ingredientList2));
      
      return dishList;
  }
  
  public List<Dish> getSampleSecondDishes(){
	  List<Dish> dishList = new ArrayList<>();
	  
	  List<Ingredient> ingredientList = new ArrayList<>();
      ingredientList.add(new Ingredient(
      		new Product("Potato", 3, true), 400));
      ingredientList.add(new Ingredient(new Product("Water", 2, true), 500));
      dishList.add(new Dish("Bliny", Group.SECOND, ingredientList));
      
      return dishList;
  }
  
  public List<Dish> getSampleDrinkDishes(){
	  List<Dish> dishList = new ArrayList<>();
	  
      List<Ingredient> ingredientList = new ArrayList<>();
      ingredientList.add(new Ingredient(
      		new Product("Potato", 3, true), 200));
      ingredientList.add(new Ingredient(new Product("Water", 2, true), 500));
      dishList.add(new Dish("Tea", Group.DRINK, ingredientList));
      
      return dishList;
  }
  
  
  
  
}
