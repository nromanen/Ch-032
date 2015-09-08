package com.softserveinc.orphanagemenu.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.softserveinc.orphanagemenu.model.AgeCategory;
import com.softserveinc.orphanagemenu.model.Component;
import com.softserveinc.orphanagemenu.model.ComponentWeight;
import com.softserveinc.orphanagemenu.model.ConsumptionType;
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

	public List<ComponentWeight> getAllComponents(Long DailyMenuID) {
		List<ComponentWeight> compontWeights = new ArrayList<ComponentWeight>();

		for (Submenu subMenu : getById(DailyMenuID).getSubmenus()) {

			AgeCategory subMenuAgeCategory = subMenu.getAgeCategory();
			System.out.println("Sub" + subMenu.getId());

			for (Dish dish : subMenu.getDishes()) {

				for (Component component : dish.getComponents()) {
					for (ComponentWeight componentWeight : component
							.getComponents()) {
						if (componentWeight.getAgeCategory().equals(
								subMenuAgeCategory)) {
							compontWeights.add(componentWeight);
						}
					}

				}

			}

		}

		return compontWeights;
	}

	@Override
	public Boolean getDailyMenuAccepted(Long id) {
		return em.find(DailyMenu.class, id).isAccepted();
	}

	public List<Product> getProductsForDailyMenu(Date date) {
		DailyMenu dailyMenu = getByDate(date);
		Set<Product> productSet = new HashSet<>();
		for (Submenu submenu : dailyMenu.getSubmenus()) {
			for (Dish dish : submenu.getDishes()) {
				for (Component component : dish.getComponents()) {
					productSet.add(component.getProduct());
				}
			}
		}
		return new ArrayList<Product>(productSet);
	}

	public List<ConsumptionType> getConsumptionTypesForDailyMenu(Date date) {
		DailyMenu dailyMenu = getByDate(date);
		Set<ConsumptionType> consumptionTypeSet = new HashSet<>();
		for (Submenu submenu : dailyMenu.getSubmenus()) {
			if (submenu.getDishes().size() > 0)
				consumptionTypeSet.add(submenu.getConsumptionType());
		}
		ArrayList<ConsumptionType> consumptionTypeList = new ArrayList<>(
				consumptionTypeSet);
		Collections.sort(consumptionTypeList,
				new Comparator<ConsumptionType>() {
					public int compare(ConsumptionType first,
							ConsumptionType second) {
						if (first.getOrderby() > second.getOrderby()) {
							return 1;
						} else {
							return -1;
						}
					}
				});
		return consumptionTypeList;
	}

	@Override
	public Long createByTemplate(Long id, Date inputDate) {
		java.sql.Date date = new java.sql.Date(inputDate.getTime());
		
		  Session session = (Session) em.getDelegate();
		 
		  Query query = session.createSQLQuery("SELECT create_menu_by_template(:id,:date)");
		  query.setInteger("id", Integer.parseInt(id.toString()));
		  query.setString("date", date.toString());	  
		
		  long newId = (Integer)query.list().get(0);

		return newId;
	}

}
