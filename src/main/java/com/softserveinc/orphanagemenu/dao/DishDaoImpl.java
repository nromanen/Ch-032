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
		Dish dish = (Dish) em.createQuery("SELECT d FROM Dish d WHERE d.dishName='"+name+"'").getSingleResult();
		return dish;
	}
		
	public void updateDish(Dish dish){
		em.merge(dish);
	}
}


