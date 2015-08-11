package com.softserveinc.orphanagemenu.dao;

import java.util.List;

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
	public List<Dish> getAllDish() {
		return (List<Dish>)em.createQuery("SELECT d FROM Dish d").getResultList();
	}

	public Dish getDishById(Long id) {
		return (Dish) em.createQuery("SELECT d FROM Dish d WHERE d.id="+id).getSingleResult();
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
		return (Dish)em.createQuery("SELECT d FROM Dish d WHERE d.id=" + dishByName.getId()).getSingleResult();
	}

	
	public Boolean checkIfDishExist(Dish dish) {
		try{
		em.createQuery("SELECT d FROM Dish d WHERE d.id="+dish.getId()).getSingleResult();
		}catch(IllegalArgumentException e){
			return false;
		}
		return true;
	}
	
	@SuppressWarnings("unused")
	public Boolean checkIfDishExist(String name) {
		try{
		em.createQuery("SELECT d FROM Dish d WHERE d.name='"+name+"'").getSingleResult();
		}catch(Exception e){
			return false;
		}
		return true;
	}
}


