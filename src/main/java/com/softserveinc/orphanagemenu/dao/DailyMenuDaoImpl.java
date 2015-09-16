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
	private static final String PRODUCTS_BY_DATE = 
			  "SELECT DISTINCT p "
			+ "FROM DailyMenu dm "
			+ "JOIN dm.submenus s "
			+ "JOIN s.dishes d "
			+ "JOIN d.components c "
			+ "JOIN c.product p "
			+ "WHERE dm.date = :date";
	
	private static final String NOT_EMPTY_CONSUMPTION_TYPES_FOR_DAILY_MENU = 
			  "SELECT DISTINCT ct "
			+ "FROM DailyMenu dm "
			+ "JOIN dm.submenus s "
			+ "JOIN s.consumptionType ct "
			+ "WHERE dm.date = :date "
			+ "AND size(s.dishes) > 0 "
			+ "ORDER BY ct.orderby";
	
	private static final String MATRIX_CONSUMPTION_DISH_PRODUCT_AGE_FACT_QUANTITY = 
			  "SELECT ct, d, p, ac, fpq "
			+ "FROM DailyMenu dm, FactProductQuantity fpq "
			+ "JOIN dm.submenus s "
			+ "JOIN s.consumptionType ct "
			+ "JOIN s.ageCategory ac "
			+ "JOIN s.dishes d "
			+ "JOIN d.components c "
			+ "JOIN c.product p "
			+ "JOIN c.componentWeight cw "
			+ "WHERE dm.date = :date "
			+ "AND ac IN (:ageCategories) "
			+ "AND fpq.submenu = s "
			+ "AND fpq.componentWeight = cw "
			+ "ORDER BY ct.orderby";
	
	private static final String MATRIX_PRODUCT_AGE_FACT_QUANTITY = 
			  "SELECT p, ac, fpq "
			+ "FROM DailyMenu dm, FactProductQuantity fpq "
			+ "JOIN dm.submenus s "
			+ "JOIN s.consumptionType ct "
			+ "JOIN s.ageCategory ac "
			+ "JOIN s.dishes d "
			+ "JOIN d.components c "
			+ "JOIN c.product p "
			+ "JOIN c.componentWeight cw "
			+ "WHERE dm.date = :date "
			+ "AND ac IN (:ageCategories) "
			+ "AND fpq.submenu = s "
			+ "AND fpq.componentWeight = cw "
			+ "ORDER BY ct.orderby";
	
	private static final String MATRIX_SUBMENU_PRODUCT_FACT_QUANTITY = 
			  "SELECT s, p, fpq "
			+ "FROM DailyMenu dm "
			+ "JOIN dm.submenus s "
			+ "JOIN s.factProductQuantities fpq "
			+ "JOIN s.dishes d "
			+ "JOIN d.components c "
			+ "JOIN c.product p "
			+ "JOIN c.componentWeight cw "
			+ "WHERE dm.date = :date "
			+ "AND fpq.submenu = s "
			+ "AND fpq.componentWeight = cw";

	@PersistenceContext
	private EntityManager em;

	public DailyMenu save(DailyMenu dailyMenu) {
		return em.merge(dailyMenu);
	}

	public void updateDailyMenu(DailyMenu dailyMenu) {
		em.merge(dailyMenu);
	}

	public void delete(DailyMenu dailyMenu) {
		em.remove(em.merge(dailyMenu));
	}

	public DailyMenu getById(Long id) {
		return em.find(DailyMenu.class, id);
	}

	public Date getDateById(Long id) {
		return em.find(DailyMenu.class, id).getDate();
	}

	@SuppressWarnings("unchecked")
	public List<DailyMenu> getAll() {
		return (ArrayList<DailyMenu>) em.createQuery(
				"SELECT dm FROM DailyMenu dm").getResultList();
	}

	@SuppressWarnings("unchecked")
	public DailyMenu getByDate(Date date) {
		List<DailyMenu> dailyMenus = (List<DailyMenu>) em
				.createQuery(DAILY_MENU_BY_DATE)
				.setParameter("date", date)
				.getResultList();
		if (dailyMenus.size() > 0) {
			return dailyMenus.get(0);
		}
		return null;
	}

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

	public Boolean getDailyMenuAccepted(Long id) {
		return em.find(DailyMenu.class, id).isAccepted();
	}
	
	@SuppressWarnings("unchecked")
	public List<Product> getProductsForDailyMenu(Date date) {
		List<Product> products = (List<Product>) em
			.createQuery(PRODUCTS_BY_DATE)
			.setParameter("date", date)
			.getResultList();
		return products;
	}
	
	// old inefficient implementation of getProductsForDailyMenu(Date date) 
	// without "joins" but with "for"
	public List<Product> getProductsForDailyMenuOld(Date date) {
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

	@SuppressWarnings("unchecked")
	public List<ConsumptionType> getConsumptionTypesForDailyMenu(Date date) {
		List<ConsumptionType> consumptionTypes = (List<ConsumptionType>) em
			.createQuery(NOT_EMPTY_CONSUMPTION_TYPES_FOR_DAILY_MENU)
			.setParameter("date", date)
			.getResultList();
		return consumptionTypes;
	}

	// old inefficient implementation of getConsumptionTypesForDailyMenu(Date date) 
	// without "joins" but with "for" and ugly sort
	public List<ConsumptionType> getConsumptionTypesForDailyMenu2(Date date) {
		DailyMenu dailyMenu = getByDate(date);
		Set<ConsumptionType> consumptionTypeSet = new HashSet<>();
		for (Submenu submenu : dailyMenu.getSubmenus()) {
			if (submenu.getDishes().size() > 0)
				consumptionTypeSet.add(submenu.getConsumptionType());
		}
		List<ConsumptionType> consumptionTypeList = new ArrayList<>(consumptionTypeSet);
		Collections.sort(
				consumptionTypeList, 
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

	public Long createByTemplate(Long id, Date inputDate) {
		DailyMenu dailyMenu = getByDate(inputDate);
		if (!(dailyMenu == null)) {
			delete(dailyMenu);
		}
		java.sql.Date date = new java.sql.Date(inputDate.getTime());
		Session session = (Session) em.getDelegate();
		Query query = session
				.createSQLQuery("SELECT create_menu_by_template(:id,:date)");
		query.setInteger("id", Integer.parseInt(id.toString()));
		query.setString("date", date.toString());
		int newDailyMenuId = (int) query.list().get(0);
		return (long) newDailyMenuId;
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> getMatrixConsumptionTypeDishProductAgeCategoryFactProductQuantity(
			Date date,
			List<AgeCategory> ageCategories) {
		List<Object[]> matrix = (List<Object[]>) em
			.createQuery(MATRIX_CONSUMPTION_DISH_PRODUCT_AGE_FACT_QUANTITY)
			.setParameter("date", date)
			.setParameter("ageCategories", ageCategories)
			.getResultList();
		return matrix;
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getMatrixProductAgeCategoryFactProductQuantity(
			Date date,
			List<AgeCategory> ageCategories) {
		List<Object[]> matrix = (List<Object[]>) em
			.createQuery(MATRIX_PRODUCT_AGE_FACT_QUANTITY)
			.setParameter("date", date)
			.setParameter("ageCategories", ageCategories)
			.getResultList();
		return matrix;
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> getMatrixSubmenuProductFactProductQuantity(Date date) {
		List<Object[]> matrix = (List<Object[]>) em
			.createQuery(MATRIX_SUBMENU_PRODUCT_FACT_QUANTITY)
			.setParameter("date", date)
			.getResultList();
		return matrix;
	}

}
