package com.softserveinc.orphanagemenu.dao;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.softserveinc.orphanagemenu.model.Dish;


@Repository("dishDaoImpl")
@Transactional
public class DishDaoImpl implements DishDao {

	@PersistenceContext
    private EntityManager em;
	
	public void addDish(Dish dish) {
		em.persist(dish);
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Dish> getAllDish() {
		return (ArrayList<Dish>)em.createQuery("SELECT d FROM Dish d").getResultList();
	}

	public Dish getDishById(Long id) {
		Dish dish = (Dish) em.createQuery("SELECT d FROM Dish d WHERE d.id="+id).getSingleResult();
		return dish;
	}

	public Dish getDishByName(String name) {
		try{
		return  (Dish) em.createQuery("SELECT d FROM Dish d WHERE d.name='"+name+"'").getSingleResult();
		}
		catch(Exception e){
			
		return null;
		}
	}
		
	public void updateDish(Dish dish){
		em.merge(dish);
	}
	
	public Boolean checkDishById(Dish dish, Long id){
		 Dish dishh = (Dish) em.createQuery("SELECT d FROM Dish d WHERE d.id="+id).getSingleResult();
		 if(dish.getId()==dishh.getId()){
			 return true;
		 }
		 
		 return false;
	}

	
	public Dish getDishById(Dish dishByName) {
		Dish dish = (Dish)em.createQuery("SELECT d FROM Dish d WHERE d.id=" + dishByName.getId()).getSingleResult();
		return dish;
	}

	
	public Boolean checkIfDishExist(Dish dish) {
		try{
		dish = (Dish) em.createQuery("SELECT d FROM Dish d WHERE d.id="+dish.getId()).getSingleResult();
		}catch(IllegalArgumentException e){
			return false;
		}
		return true;
	}
	
	@SuppressWarnings("unused")
	public Boolean checkIfDishExist(String name) {
		try{
		Dish dish = (Dish) em.createQuery("SELECT d FROM Dish d WHERE d.name='"+name+"'").getSingleResult();
		}catch(Exception e){
			return false;
		}
		return true;
	}
}


