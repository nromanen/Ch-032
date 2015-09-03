package com.softserveinc.orphanagemenu.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.softserveinc.orphanagemenu.model.Component;
import com.softserveinc.orphanagemenu.model.DailyMenu;
import com.softserveinc.orphanagemenu.model.Dish;
import com.softserveinc.orphanagemenu.model.Product;
import com.softserveinc.orphanagemenu.model.Submenu;

/**
 * @author Vladimir Perepeliuk
 * @author Olexii Riabokon
 */
@Repository("dailyMenuDao")
@Transactional
public class DailyMenuDaoImpl implements DailyMenuDao {

	private static final String DAILY_MENU_BY_DATE = "SELECT dm FROM DailyMenu dm WHERE dm.date = :date";
	private static final String DAILY_MENU_CURRENT_DATE_TO_FUTURE_DATE = "SELECT dm FROM DailyMenu dm WHERE dm.date >= :currentDate and dm.date < :futureDate";

	@PersistenceContext
	private EntityManager em;

	@Override
	public DailyMenu save(DailyMenu dailyMenu) {
		return em.merge(dailyMenu);

	}

	@Override
	public void updateDailyMenu(DailyMenu dailyMenu) {
		em.merge(dailyMenu);
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
	public Date getDateById(Long id) {
		return em.find(DailyMenu.class, id).getDate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DailyMenu> getAll() {
		return (ArrayList<DailyMenu>) em.createQuery(
				"SELECT dm FROM DailyMenu dm").getResultList();
	}

	@Override
	public void print() {
		DailyMenu dailyMenu = getById(1L);
		System.out.println(dailyMenu);
		System.out.println(dailyMenu.getSubmenus());
		Submenu submenu = (Submenu) dailyMenu.getSubmenus().toArray()[0];
		System.out.println(submenu);
		System.out.println(submenu.getFactProductQuantities());

	}

	@Override
	@SuppressWarnings("unchecked")
	public DailyMenu getByDate(Date date) {
		List<DailyMenu> dailyMenus = (List<DailyMenu>) em
				.createQuery(DAILY_MENU_BY_DATE).setParameter("date", date)
				.getResultList();
		DailyMenu dailyMenu = null;
		if (dailyMenus.size() != 0) {
			dailyMenu = dailyMenus.get(0);
		}
		return dailyMenu;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<DailyMenu> getFromCurrentDateToFutureDate(Date futureDate) {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
				calendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		Date currentDate = calendar.getTime();
		return (List<DailyMenu>) em
				.createQuery(DAILY_MENU_CURRENT_DATE_TO_FUTURE_DATE)
				.setParameter("currentDate", currentDate)
				.setParameter("futureDate", futureDate).getResultList();
	}

	public List<Component> getAllComponents(Long DailyMenuID) {
		List<Component> componenList = new ArrayList<Component>();
		for (Submenu subMenu : getById(DailyMenuID).getSubmenus()) {

			for (Dish dish : subMenu.getDishes()) {

				for (Component component : dish.getComponents()) {
					componenList.add(component);
				}
			}
		}
		return componenList;
	}

	@Override
	public Boolean getDailyMenuAccepted(Long id) {
		return em.find(DailyMenu.class, id).isAccepted();
	}

	public List<Product> getProductsForDailyMenu(Date date) {
		DailyMenu dailyMenu = getByDate(date);
		Set<Product> productSet = new HashSet<Product>();
		for (Submenu submenu : dailyMenu.getSubmenus()) {
			for (Dish dish : submenu.getDishes()) {
				for (Component component : dish.getComponents()) {
					productSet.add(component.getProduct());
				}
			}
		}
		return new ArrayList<Product>(productSet);
	}

	@Override
	public Long createByTemplate(Long id, Date inputDate) {
		java.sql.Date date = new java.sql.Date(inputDate.getTime());
		System.out.println("************"+date);
		StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("clone_menu");
	
		storedProcedure.registerStoredProcedureParameter("existing_id", Long.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter("date", java.sql.Date.class, ParameterMode.IN);
		
		storedProcedure.setParameter("existing_id", id);
		storedProcedure.setParameter("date", date);
		
		storedProcedure. execute();

		return 1L;
	}

}
