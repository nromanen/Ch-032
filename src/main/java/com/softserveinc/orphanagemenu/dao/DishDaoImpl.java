
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
		try{
		return (Dish) em.createQuery("SELECT d FROM Dish d WHERE d.id="+id).getSingleResult();
		}catch(Exception e){
			return null;
		}
	}
		
	@Override
	public void updateDish(Dish dish){
		em.merge(dish);
	}
	
	@Override
	public Dish getDish(String name) {
		try {
			return em.createQuery("SELECT d FROM Dish d WHERE LOWER (d.name)=?",
					Dish.class).setParameter(1, name.toLowerCase())  
			.getSingleResult();
		}
		catch(NoResultException e){
		return null;
		}
	}
	
	@Override
	public Long getCount() {
		return em.createQuery("SELECT Count(d) FROM Dish d", Long.class)
				.getSingleResult();
	}

	@Override
	public Boolean getAvailable(Long id) {
		return em.find(Dish.class, id).getIsAvailable();
	}
}


