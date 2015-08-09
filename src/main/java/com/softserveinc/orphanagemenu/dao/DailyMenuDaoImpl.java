package com.softserveinc.orphanagemenu.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.softserveinc.orphanagemenu.model.DailyMenu;
import com.softserveinc.orphanagemenu.model.Dish;
import com.softserveinc.orphanagemenu.model.Submenu;
import com.softserveinc.orphanagemenu.model.UserAccount;

@Repository("dailyMenuDao")
@Transactional
public class DailyMenuDaoImpl implements DailyMenuDao {

	@PersistenceContext
    private EntityManager em;
	
	@Override
	public DailyMenu save(DailyMenu dailyMenu) {
		em.persist(dailyMenu);
		return null;
	}

	@Override
	public void delete(DailyMenu dailyMenu) {
		em.remove(em.merge(dailyMenu));
	}

	@Override
	public DailyMenu getById(Long id) {
		return em.find(DailyMenu.class, id);
	}

	@Override
	public List<DailyMenu> getAll() {
		return (ArrayList<DailyMenu>)em.createQuery("SELECT dm FROM DailyMenu dm").getResultList();
	}

	@Override
	public void print() {
		DailyMenu dailyMenu = getById(1L);
		System.out.println(dailyMenu);
		System.out.println(dailyMenu.getSubmenus());
		Submenu submenu = (Submenu)dailyMenu.getSubmenus().toArray()[0];
		System.out.println(submenu);
		System.out.println(submenu.getFactProductQuantities());
		
	}

}
