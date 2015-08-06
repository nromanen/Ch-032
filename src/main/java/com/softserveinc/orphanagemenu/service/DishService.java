package com.softserveinc.orphanagemenu.service;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.softserveinc.orphanagemenu.dao.DishDao;
import com.softserveinc.orphanagemenu.model.Dish;

@Service
public class DishService {

	@Autowired
	@Qualifier("dishDaoImpl")
	private DishDao dishDao;
	
	@Transactional
	public void addDish(Dish dish){
		this.dishDao.addDish(dish);
	}
	
	@Transactional
	public ArrayList<Dish> getAllDish(){
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
}
