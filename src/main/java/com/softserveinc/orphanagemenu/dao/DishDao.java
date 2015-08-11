package com.softserveinc.orphanagemenu.dao;

import java.util.List;

import com.softserveinc.orphanagemenu.model.Dish;

public interface DishDao {

	void addDish(Dish dish);
	
	List<Dish> getAllDish();
	
	public Dish getDishById(Long id);

	Dish getDishByName(String name);
	
	void updateDish(Dish dish);
	
	public Dish getDishById(Dish dishByName);
	
	public Boolean checkIfDishExist(Dish dish);
	
	public Boolean checkIfDishExist(String name);
}
