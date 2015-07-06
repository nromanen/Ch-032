package com.javagroup.restaurantmenu.util;

import java.util.ArrayList;
import java.util.List;

import com.javagroup.restaurantmenu.model.Dish;
import com.javagroup.restaurantmenu.model.Group;
import com.javagroup.restaurantmenu.model.Ingredient;
import com.javagroup.restaurantmenu.model.Product;

/**
 * Class for creating sample list of dishes
 * 
 * @author Oleksii Riabokon
 * @author Vova Perepelyuk
 */

public class DishCreator {

    /**
     * Returns sample list of dishes for testing purposes  
     * @return a sample list of dishes
     */	
    public List<Dish> createDishes() {

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

        List<Ingredient> ingredientList3 = new ArrayList<>();
        ingredientList3.add(new Ingredient(
        		new Product("Potato", 3, true), 400));
        ingredientList3.add(new Ingredient(new Product("Water", 2, true), 500));
        dishList.add(new Dish("Bliny", Group.SECOND, ingredientList3));

        List<Ingredient> ingredientList4 = new ArrayList<>();
        ingredientList4.add(new Ingredient(
        		new Product("Potato", 3, true), 200));
        ingredientList4.add(new Ingredient(new Product("Water", 2, true), 500));
        dishList.add(new Dish("Tea", Group.DRINK, ingredientList4));
        
        return dishList;
    }   
   
}
