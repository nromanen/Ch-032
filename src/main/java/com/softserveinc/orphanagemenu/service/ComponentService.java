
package com.softserveinc.orphanagemenu.service;

import java.util.List;

import com.softserveinc.orphanagemenu.json.DishForm;
import com.softserveinc.orphanagemenu.model.Component;
import com.softserveinc.orphanagemenu.model.Dish;
import com.softserveinc.orphanagemenu.model.Product;

public interface ComponentService {

	List<Component> getAllComponent();
	
	void saveComponent(Component comp);
	
	Long getProductFromComponent(Product product);
	
	void updateComponent(Component component);
	
	Component getNewComponentByDishForm(DishForm dishForm);
	
	Component updateNewComponentByDishForm(DishForm dishForm);
	
	List<Component> getAllComponentByDishId(Dish dish);
	
	Component getComponentById(Long id);
	
}

