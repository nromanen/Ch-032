package com.softserveinc.orphanagemenu.dao;

import java.util.ArrayList;

import com.softserveinc.orphanagemenu.model.Component;
import com.softserveinc.orphanagemenu.model.Dish;
import com.softserveinc.orphanagemenu.model.Product;


public interface ComponentDao {

	ArrayList<Component> getAllComponent();
	
	void saveComponent(Component comp);
	
	Long getProductFromComponent(Product product);
	
	void updateComponent(Component component);
	
	Component getComponentById(Long id);
	
	ArrayList<Component> getAllComponentByDishId(Dish dish);
}
