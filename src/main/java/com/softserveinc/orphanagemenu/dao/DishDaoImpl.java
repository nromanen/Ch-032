
package com.softserveinc.orphanagemenu.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.softserveinc.orphanagemenu.model.Dish;


@Repository("dishDao")
@Transactional
public class DishDaoImpl implements DishDao {

	@PersistenceContext
    private EntityManager em;
	
	@Override
	public void addDish(Dish dish) {
		em.persist(dish);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Dish> getAllDish() {
		return (List<Dish>)em.createQuery("SELECT d FROM Dish d").getResultList();
	}

	@Override
	public Dish getDishById(Long id) {
		return (Dish) em.createQuery("SELECT d FROM Dish d WHERE d.id="+id).getSingleResult();
	}
	
	@Override
	public Dish getDishByName(String name) {
		try{
		return  (Dish) em.createQuery("SELECT d FROM Dish d WHERE d.name='"+name+"'").getSingleResult();
		}
		catch(NoResultException e){
		return null;
		}
	}
		
	@Override
	public void updateDish(Dish dish){
		em.merge(dish);
	}
	
	@Override
	public Dish getDish(String dishName) {
		try {
			return em.createQuery("SELECT d FROM Dish d WHERE (d.name)=?",
					Dish.class).setParameter(1, dishName.toLowerCase())
			.getSingleResult();
		} catch (Exception e) {
		}
		return null;
	}
}



