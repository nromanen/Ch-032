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
	public Boolean checkDishById(Dish dish, Long id){
		return this.dishDao.checkDishById(dish, id);
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

}
