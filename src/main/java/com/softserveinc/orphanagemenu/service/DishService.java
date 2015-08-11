package com.softserveinc.orphanagemenu.service;

import java.util.ArrayList;

import com.softserveinc.orphanagemenu.model.Dish;

public interface DishService {

	public void addDish(Dish dish);
	
	public ArrayList<Dish> getAllDish();
	
	public Dish getDishById(Long id);
	
	public Dish getDishByName(String name);
	
	public void updateDish(Dish dish);
	
	public Boolean checkDishById(Dish dish, Long id);
	
	public Dish getDishById(Dish dishByName);
	
	public Boolean checkIfDishExist(Dish dish);
	
	public Boolean checkIfDishExist(String name);
	
	public Dish getDish(String dishName);
	
}
