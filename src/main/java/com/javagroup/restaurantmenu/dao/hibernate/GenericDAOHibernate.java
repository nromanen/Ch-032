package com.javagroup.restaurantmenu.dao.hibernate;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;

import com.javagroup.restaurantmenu.dao.GenericDAO;

public abstract class GenericDAOHibernate<T> implements GenericDAO<T> {

	private Session session;	
	
    public abstract Class<T> getPersistentClass();
	
	protected Session getSession() {
        if (session == null || !session.isOpen()){
        	session = HibernateUtil.getSessionFactory().getCurrentSession();
        }
        return session;
    }
    
    public void makePersistent(T entity) {
		getSession().saveOrUpdate(entity);
//		return entity;
		return;
	}

    public void makeTransient(T entity) {
        getSession().delete(entity);
    }

    @SuppressWarnings("unchecked")    
    public T findById(long id) {
    	T entity;
        entity = (T) getSession().load(getPersistentClass(), id);
        return entity;
    }
    
    @SuppressWarnings("unchecked")
	public List<T> findAll() {
    	List<T> ingredientCategories;
    	Criteria crit = getSession().createCriteria(getPersistentClass());
    	ingredientCategories = (List<T>) crit.list();
    	return ingredientCategories;
    }
}
