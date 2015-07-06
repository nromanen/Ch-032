package com.javagroup.restaurantmenu.dao.hibernate;

import com.javagroup.restaurantmenu.dao.DishDAO;
import com.javagroup.restaurantmenu.model.Dish;

public class DishDAOHibernate 
				extends GenericDAOHibernate<Dish> 
				implements DishDAO {

	@Override
	public Class<Dish> getPersistentClass() {
		return Dish.class;
	}

}
