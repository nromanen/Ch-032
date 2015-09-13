
package com.softserveinc.orphanagemenu.dao;

import java.util.List;

import com.softserveinc.orphanagemenu.model.Component;
import com.softserveinc.orphanagemenu.model.Dish;
import com.softserveinc.orphanagemenu.model.Product;


public interface ComponentDao {

	List<Component> getAllComponents();
	
	void saveComponent(Component comp);
	
	Long getProductFromComponent(Product product);
	
	void updateComponent(Component component);
	
	Component getComponentById(Long id);
	
	List<Component> getAllComponentsByDishId(Dish dish);

	void deleteComponent(Component component);
	
	Long getCount();
}
