package com.softserveinc.orphanagemenu.service;

import java.util.ArrayList;
import java.util.List;

import com.softserveinc.orphanagemenu.model.Dish;

public interface DishService {

	void addDish(Dish dish);
	
	List<Dish> getAllDish();
	
	Dish getDishById(Long id);
	
	Dish getDishByName(String name);
	
	void updateDish(Dish dish);
	
	Dish getDishById(Dish dishByName);
	
	Boolean checkIfDishExist(Dish dish);
	
	Boolean checkIfDishExist(String name);
	
}
