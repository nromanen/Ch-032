package com.softserveinc.orphanagemenu.dao;

import java.util.ArrayList;

import com.softserveinc.orphanagemenu.model.Dish;

public interface DishDao {

	void addDish(Dish dish);
	
	ArrayList<Dish> getAllDish();
	
	public Dish getDishById(Long id);

	Dish getDishByName(String name);
	
	void updateDish(Dish dish);
	
	public Boolean checkDishById(Dish dish, Long id);
	
	public Dish getDishById(Dish dishByName);
	
	public Boolean checkIfDishExist(Dish dish);
	
	public Boolean checkIfDishExist(String name);
}
