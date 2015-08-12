
package com.softserveinc.orphanagemenu.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.softserveinc.orphanagemenu.dao.DishDao;
import com.softserveinc.orphanagemenu.model.Dish;

@Service("dishService")
@Transactional
public class DishServiceImpl implements DishService {
	
	@Autowired
	@Qualifier("dishDaoImpl")
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
	public Dish getDishByName(String name) {
		return this.dishDao.getDishByName(name);
	}
	
	@Override
	@Transactional
	public void updateDish(Dish dish){
		this.dishDao.updateDish(dish);
	}
	
	@Override
	@Transactional
	public Dish getDish(String dishName) {
		return this.dishDao.getDish(dishName);
	}

}
