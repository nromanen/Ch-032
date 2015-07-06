package com.javagroup.restaurantmenu.dao.memory;

import java.util.ArrayList;
import java.util.List;

import com.javagroup.restaurantmenu.dao.DishDAO;
import com.javagroup.restaurantmenu.model.Dish;
import com.javagroup.restaurantmenu.model.Group;

/**
 * Class for implementation of business logic connected with dishes.
 * It allows to return list of dishes using different separation criteria.
 * 
 * @author Oleksii Riabokon
 * @author Vova Perepelyuk
 */
public class DishDAOMemory implements DishDAO {

    /**
     * Emulate the DB that stores all the dishes.
     */
    private List<Dish> dishList;

    /**
     * Constructs a DishDAO and fill the emulated DB with specified 
     * in parameter list of dishes
     * @param dishList the list of the all the dishes
     */  	
	public DishDAOMemory(List<Dish> dishList) {
		this.dishList = dishList;
	}

    /**
     * Returns all the possible dishes 
     * @return a list of all the possible dishes
     */	
	public List<Dish> findAll() {
		return dishList;
	}

    /**
     * Returns all the possible dishes of chosen group including check 
     * for availability of the dishes (check if all ingredients are available)
     * @param group a Group of the dishes for which we select what dishes 
     * to return
     *  
     * @return a list of all the possible available dishes of chosen group 
     */	
	public List<Dish> getAllAvailableDishesByGroup(Group group) {
		List<Dish> resultList = new ArrayList<>();
		for (Dish dish : dishList) {
			if (dish.getAvailable() && dish.getGroup().equals(group)) {
				resultList.add(dish);
			}
		}
		return resultList;
	}
	
    /**
     * Returns all the possible dishes of chosen group without checking  
     * for availability of the dishes (check if all ingredients are available)
     * @param group a Group of the dishes for which we select what dishes 
     * to return
     *  
     * @return a list of all the possible dishes of chosen group without 
     * checking for availability of the dishes (check if all ingredients 
     * are available)
     */	
	public List<Dish> getAllDishesByGroup(Group group) {
		List<Dish> resultList = new ArrayList<>();
		for (Dish dish : dishList) {
			if (dish.getGroup().equals(group)) {
				resultList.add(dish);
			}
		}
		return resultList;
	}

	@Override
	public void makePersistent(Dish dish) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void makeTransient(Dish dish) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Dish findById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
