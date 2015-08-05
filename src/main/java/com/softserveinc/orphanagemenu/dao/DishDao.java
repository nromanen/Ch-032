package com.softserveinc.orphanagemenu.dao;

import java.util.ArrayList;

import com.softserveinc.orphanagemenu.model.Dish;

public interface DishDao {

	void addDish(Dish dish);
	
	ArrayList<Dish> getAllDish();
	
	public Dish getDishById(Long id);

	Dish getDishByName(String name);
	
	void updateDish(Dish dish);
}
