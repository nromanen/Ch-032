
package com.softserveinc.orphanagemenu.service;

import java.util.List;
import java.util.Map;

import com.softserveinc.orphanagemenu.forms.DishForm;
import com.softserveinc.orphanagemenu.json.DishResponseBody;
import com.softserveinc.orphanagemenu.json.updateComponentJson;
import com.softserveinc.orphanagemenu.model.Component;
import com.softserveinc.orphanagemenu.model.Dish;
import com.softserveinc.orphanagemenu.model.Product;

public interface DishService {

	public void addDish(Dish dish);

	public List<Dish> getAllDishes();

	public Dish getDishById(Long id);

	public Dish getDish(String name);

	public void updateDish(Dish dish);

	public Boolean getAvailable(Long id);

	Dish getDishByDishForm(DishForm dishForm);

	Map<Long, Double> parseJsonValue(DishResponseBody dishResponse);
	
	Map<Long, Double> parseJsonValue(updateComponentJson dishResponse);
	
	void deleteUsedComponentsFromComponentsList(List<Product> productList, List<Component> componentList);

	

}
