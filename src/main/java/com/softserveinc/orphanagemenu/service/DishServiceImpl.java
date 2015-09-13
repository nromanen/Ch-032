
package com.softserveinc.orphanagemenu.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softserveinc.orphanagemenu.dao.DishDao;
import com.softserveinc.orphanagemenu.json.DishResponseBody;
import com.softserveinc.orphanagemenu.json.updateComponentJson;
import com.softserveinc.orphanagemenu.model.Component;
import com.softserveinc.orphanagemenu.model.Dish;
import com.softserveinc.orphanagemenu.model.Product;
import com.softserveinc.orphanagemenu.model.ProductWeight;
import com.softserveinc.orphanagemenu.forms.DishForm;

@Service("dishService")
@Transactional
public class DishServiceImpl implements DishService {
	
	@Autowired
	private DishDao dishDao;
	
	@Autowired
	private ProductService productService;
	
	@Override
	@Transactional
	public void addDish(Dish dish){
		this.dishDao.addDish(dish);
	}
	
	@Override
	@Transactional
	public List<Dish> getAllDishes(){
    	return this.dishDao.getAllDish();
	}
	
	@Override
	@Transactional
	public Dish getDishById(Long id){
		return this.dishDao.getDishById(id);
	}

	@Override
	@Transactional
	public Dish getDish(String name) {
		return this.dishDao.getDish(name);
	}
	
	@Override
	@Transactional
	public void updateDish(Dish dish){
		this.dishDao.updateDish(dish);
	}
	
	@Override
	@Transactional
	public Dish getDishByDishForm(DishForm dishForm) {
		
		Dish dish = getDish(dishForm.getDishName());

		Set<ProductWeight> productWeightList = new HashSet<ProductWeight>();
		for (Entry<Long, String> formWeight : dishForm.getCategory().entrySet()) {
			ProductWeight weight = new ProductWeight();
			weight.setStandartProductQuantity(Double.parseDouble(formWeight.getValue()));
			productWeightList.add(weight);
		}
		
		return dish;
	}
	
	@Override
	@Transactional
	public Boolean getAvailable(Long id) {
		return this.dishDao.getAvailable(id);
	}

	@Override
	@Transactional
	public Map<Long, Double> parseJsonValue(DishResponseBody dishResponse) {
		String[] categoryId = dishResponse.getAgeCategoryId().trim().split(" ");
		String[] categoryQuantity = dishResponse.getAgeCategoryQuantity().trim().split(" ");
		Map<Long, Double> categoryIdQuantityMap = new HashMap<Long, Double>();
		if(categoryId.length == categoryQuantity.length){ 
		for(int i = 0; i < categoryId.length; i++){
			categoryIdQuantityMap.put(Long.parseLong(categoryId[i]), Double.parseDouble(categoryQuantity[i]));
			}
		}
		
		return categoryIdQuantityMap;
	}
	
	@Override
	@Transactional
	public Map<Long, Double> parseJsonValue(updateComponentJson dishResponse) {
		String[] categoryId = dishResponse.getAgeCategoryId().trim().split(" ");
		String[] categoryQuantity = dishResponse.getAgeCategoryQuantity().trim().split(" ");
		Map<Long, Double> categoryIdQuantityMap = new HashMap<Long, Double>();
		if(categoryId.length == categoryQuantity.length){ 
		for(int i = 0; i < categoryId.length; i++){
			categoryIdQuantityMap.put(Long.parseLong(categoryId[i]), Double.parseDouble(categoryQuantity[i]));
			}
		}
		
		return categoryIdQuantityMap;
	}

	@Override
	@Transactional
	public void deleteUsedComponentsFromComponentsList(List<Product> productList, List<Component> componentList) {
		for (Component comp : componentList) {
			productList.remove(comp.getProduct());
		}
	}
	
	
}
