package com.javagroup.restaurantmenu;

import java.util.List;

import com.javagroup.restaurantmenu.dao.file.DishDAOFile;
import com.javagroup.restaurantmenu.model.Dish;
import com.javagroup.restaurantmenu.util.DishCreator;

public class LauncherFile {

    /**
     * @param args the command line arguments
     */
	public static void main(String[] args) {
		final String DISH_FILE_NAME = "dishes.txt";

		DishCreator dishCreator = new DishCreator();
		List<Dish> dishes = dishCreator.createDishes();

		DishDAOFile dishDAOFile = new DishDAOFile(DISH_FILE_NAME);
		dishDAOFile.persistAllDishes(dishes);

		dishes.clear();
		dishes = dishDAOFile.findAll();
		for (Dish dish : dishes) {
			System.out.println(dish);
		}
	}
}