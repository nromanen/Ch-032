package com.softserveinc.orphanagemenu.service;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.softserveinc.orphanagemenu.dao.DishDao;
import com.softserveinc.orphanagemenu.forms.ProductForm;
import com.softserveinc.orphanagemenu.json.DishForm;
import com.softserveinc.orphanagemenu.model.AgeCategory;
import com.softserveinc.orphanagemenu.model.Dish;
import com.softserveinc.orphanagemenu.model.Product;
import com.softserveinc.orphanagemenu.model.ProductWeight;

@Service("dishService")
@Transactional
public class DishServiceImpl implements DishService {
	
	@Autowired
	@Qualifier("dishDao")
	private DishDao dishDao;
	
	@Transactional
	public void addDish(Dish dish){
		this.dishDao.addDish(dish);
	}
	
	@Transactional
	public List<Dish> getAllDish(){
    	return this.dishDao.getAllDish();
	}
	
	@Transactional
	public Dish getDishById(Long id){
		return this.dishDao.getDishById(id);
	}
	
	@Transactional
	public Dish getDishByName(String name) {
		return this.dishDao.getDishByName(name);
	}
	
	@Transactional
	public void updateDish(Dish dish){
		this.dishDao.updateDish(dish);
	}
	
	
	@Transactional
	public Dish getDishById(Dish dishByName) {
		
		return this.dishDao.getDishById(dishByName);
	}
	
	@Transactional
	public Boolean checkIfDishExist(Dish dish) {
		return this.dishDao.checkIfDishExist(dish);
	}
	
	@Transactional
	public Boolean checkIfDishExist(String name) {
		return this.dishDao.checkIfDishExist(name);
	}
	public Dish updateDishtByDishtForm(DishForm dishForm) {
		Dish dish = getDishByName(dishForm.getDishName());
		dish.setName(dishForm.getDishName());



		return dish;
	}
	public Dish getDishtByDishForm(DishForm dishForm) {
		
		Dish dish = getDishByName(dishForm.getDishName());

		Set<ProductWeight> productWeightList = new HashSet<ProductWeight>();
		int i=0;
		for (Entry<Long, String> formWeight : dishForm.getCategory().entrySet()) {
			ProductWeight weight = new ProductWeight();
			weight.setStandartProductQuantity(Double.parseDouble(formWeight.getValue()));

			productWeightList.add(weight);
			i++;
		}
		
		return dish;
	}
}
