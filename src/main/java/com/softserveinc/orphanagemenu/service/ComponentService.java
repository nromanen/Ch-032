package com.softserveinc.orphanagemenu.service;

import java.util.ArrayList;

import com.softserveinc.orphanagemenu.json.DishForm;
import com.softserveinc.orphanagemenu.model.Component;
import com.softserveinc.orphanagemenu.model.Dish;
import com.softserveinc.orphanagemenu.model.Product;

public interface ComponentService {

	public ArrayList<Component> getAllComponent();
	
	public void saveComponent(Component comp);
	
	public Long getProductFromComponent(Product product);
	
	public void updateComponent(Component component);
	
	public Component getComponentById(Component component_id);
	
	public Component getNewComponentByDishForm(DishForm dishForm);
	
	public Component updateNewComponentByDishForm(DishForm dishForm);
	
	public ArrayList<Component> getAllComponentByDishId(Dish dish);
	
}
