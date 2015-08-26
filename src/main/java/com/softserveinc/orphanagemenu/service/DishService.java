
package com.softserveinc.orphanagemenu.service;

import java.util.List;

import com.softserveinc.orphanagemenu.model.Dish;

public interface DishService {

	public void addDish(Dish dish);
	
	public List<Dish> getAllDish();
	
	public Dish getDishById(Long id);
	
	public Dish getDish(String name);
	
	public void updateDish(Dish dish);
	
	public Boolean getAvailable(Long id);
	
}
