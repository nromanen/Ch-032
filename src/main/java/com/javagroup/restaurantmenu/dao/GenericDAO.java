package com.javagroup.restaurantmenu.dao;

import java.util.List;

public interface GenericDAO<T> {

	void makePersistent(T entity);

	void makeTransient(T entity);
	
	T findById(long id);

	List<T> findAll();

}
