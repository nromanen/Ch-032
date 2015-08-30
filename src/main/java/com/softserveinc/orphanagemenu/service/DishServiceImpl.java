
package com.softserveinc.orphanagemenu.service;

import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softserveinc.orphanagemenu.dao.DishDao;
import com.softserveinc.orphanagemenu.model.Dish;
import com.softserveinc.orphanagemenu.model.ProductWeight;
import com.softserveinc.orphanagemenu.forms.DishForm;

@Service("dishService")
@Transactional
public class DishServiceImpl implements DishService {
	
	@Autowired
	private DishDao dishDao;
	
	@Override
	@Transactional
	public void addDish(Dish dish){
		this.dishDao.addDish(dish);
	}
	
	@Override
	@Transactional
	public List<Dish> getAllDish(){
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
	
	public Dish getDishtByDishForm(DishForm dishForm) {
		
		Dish dish = getDish(dishForm.getDishName());

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
	@Override
	public Boolean getAvailable(Long id) {
		return this.dishDao.getAvailable(id);
	}

	@Override
	public Boolean checkIfDishExist(Dish dish) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean checkIfDishExist(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Dish updateDishtByDishtForm(DishForm dishForm) {
		// TODO Auto-generated method stub
		return null;
	}
}
