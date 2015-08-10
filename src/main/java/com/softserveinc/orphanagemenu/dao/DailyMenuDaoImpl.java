package com.softserveinc.orphanagemenu.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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

	private static final String DAILY_MENU_BY_DATE = 
			"SELECT dm FROM DailyMenu dm WHERE dm.date = :date";
	private static final String DAILY_MENU_CURRENT_DATE_TO_FUTURE_DATE = 
			"SELECT dm FROM DailyMenu dm WHERE dm.date >= :currentDate and dm.date < :futureDate";
	
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

	@Override
	public DailyMenu getByDate(Date date) {
		return (DailyMenu)em.createQuery(DAILY_MENU_BY_DATE)
				.setParameter("date", date)
				.getResultList();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<DailyMenu> getFromCurrentDateToFutureDate(Date futureDate) {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.set(calendar.get(Calendar.YEAR),
				calendar.get(Calendar.MONTH),
				calendar.get(Calendar.DAY_OF_MONTH),
				0, 0, 0);
		Date currentDate = calendar.getTime();
		return (List<DailyMenu>)em.createQuery(DAILY_MENU_CURRENT_DATE_TO_FUTURE_DATE)
				.setParameter("currentDate", currentDate)
				.setParameter("futureDate", futureDate)
				.getResultList();
	}

}
