package com.javagroup.restaurantmenu;

import java.util.*;

import com.javagroup.restaurantmenu.dao.memory.DishDAOMemory;
import com.javagroup.restaurantmenu.model.Complex;
import com.javagroup.restaurantmenu.model.Dish;
import com.javagroup.restaurantmenu.model.Group;
import com.javagroup.restaurantmenu.model.Ingredient;
import com.javagroup.restaurantmenu.model.Menu;
import com.javagroup.restaurantmenu.model.Product;
import com.javagroup.restaurantmenu.util.DishCreator;

/**
 *
 * @author Oleksii Riabokon
 * @author Vova Perepelyuk
 */
public class LauncherMemory {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        final double COMPLEX_PRICE_LIMIT = 5000.0;
        
        DishCreator dishCreator = new DishCreator();
        List<Dish> dishes = dishCreator.createDishes();
        
        DishDAOMemory dishDAO = new DishDAOMemory(dishes);
        Menu menu = new Menu();
        List<Complex> complexList = menu.
                getComplexListByPriceLimit(COMPLEX_PRICE_LIMIT, dishDAO);
        System.out.printf("List of complexes less then %.2f usd:\n", 
                COMPLEX_PRICE_LIMIT);
        System.out.println(complexList);
        System.out.println("---------------------------");
 
        complexList = menu.getAllComplexes(dishDAO);
        System.out.println("List of all complexes:");
        System.out.println(complexList);
        System.out.println("---------------------------");

        System.out.println("List of all dishes:");
        System.out.println(dishDAO.findAll());
        System.out.println("---------------------------");
        
        System.out.println("List of all dishes:");
        System.out.println(dishDAO.findAll());
        System.out.println("---------------------------");
        
        List<Ingredient> ingredientList1 = new ArrayList<>();
        ingredientList1.add(
        		new Ingredient(new Product("Potato", 3, true), 200));
        ingredientList1.add(
        		new Ingredient(new Product("Water", 2, true), 50));
        Dish sampleDish = new Dish("Borsch", Group.FIRST, ingredientList1);
        System.out.println("Dish before adding ingredient: " + sampleDish);
        sampleDish.addIngredient(
        		new Ingredient(new Product("Water", 2, true), 50));
        System.out.println("Dish after adding ingredient: " + sampleDish);
        sampleDish.addIngredient(
        		new Ingredient(new Product("Water", 2, true), 50));
        sampleDish.removeIngredient("Water");
        System.out.println("Dish after removing water ingredient: " 
        					+ sampleDish);
        
    }
}
