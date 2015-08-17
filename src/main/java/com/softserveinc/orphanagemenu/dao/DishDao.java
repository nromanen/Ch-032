
package com.softserveinc.orphanagemenu.dao;

import java.util.List;

import com.softserveinc.orphanagemenu.model.Dish;

public interface DishDao {

	void addDish(Dish dish);
	
	List<Dish> getAllDish();
	
	public Dish getDishById(Long id);

	void updateDish(Dish dish);
	
	Dish getDishByName(String name);
	
	public Dish getDish(String dishName);
}
