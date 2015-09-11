
package com.softserveinc.orphanagemenu.service;

import java.util.List;
import java.util.Map;

import com.softserveinc.orphanagemenu.forms.DishForm;
import com.softserveinc.orphanagemenu.json.DishResponseBody;
import com.softserveinc.orphanagemenu.json.updateComponentJson;
import com.softserveinc.orphanagemenu.model.Component;
import com.softserveinc.orphanagemenu.model.Dish;
import com.softserveinc.orphanagemenu.model.Product;

public interface ComponentService {

	List<Component> getAllComponents();

	void saveComponent(Component comp);

	Long getProductsFromComponent(Product product);

	void updateComponent(Component component);

	Component getNewComponentByDishForm(DishForm dishForm);

	Component updateNewComponentByDishForm(DishForm dishForm);

	List<Component> getAllComponentsByDishId(Dish dish);

	public Component updateComponentWeightByDishForm(DishForm dishForm);

	Component getComponentById(Long id);

	void deleteComponent(Component component);

	void deleteComponent(Long compId);
	
	Component setAllComponentValue(DishResponseBody dishResponse, Map<Long, Double> categoryIdQuantity);

	Component updateDishComponentWeight(Component component, Map<Long, Double> categoryIdQuantity);
}
