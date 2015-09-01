package com.softserveinc.orphanagemenu.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.softserveinc.orphanagemenu.model.AgeCategory;
import com.softserveinc.orphanagemenu.model.Submenu;

/**
 * @author Vladimir Perepeliuk
 * @author Olexii Riabokon
 */
@Repository("submenuDao")
@Transactional
public class SubmenuDaoImpl implements SubmenuDao {

	private static final String SUBMENU_LIST_BY_DAILY_MENU_ID = "SELECT s "
			+ "FROM Submenu s " + "WHERE s.dailyMenu.id = :dailyMenuId ";

	private static final String SUBMENU_LIST_BY_DAILY_MENU_AND_CONSUMPTION_TYPE = "SELECT s "
			+ "FROM Submenu s "
			+ "WHERE s.dailyMenu.id = :dailyMenuId "
			+ "AND s.consumptionType.id = :consumptionTypeId";

	private static final String SUBMENU_BY_DAILY_MENU_AND_CONSUMPTION_TYPE_AND_AGE_CATEGORY = "SELECT s "
			+ "FROM Submenu s "
			+ "WHERE s.dailyMenu.id = :dailyMenuId "
			+ "AND s.consumptionType.id = :consumptionTypeId "
			+ "AND s.ageCategory.id = :ageCategoryId";

	@PersistenceContext
	private EntityManager em;

	@Override
	public Submenu save(Submenu submenu) {
		em.persist(submenu);
		return submenu;
	}

	public Submenu update(Submenu submenu) {
		em.merge(submenu);
		return submenu;
	}

	@Override
	public void delete(Submenu submenu) {
		em.remove(em.merge(submenu));
	}

	@Override
	public Submenu getById(Long id) {
		return em.find(Submenu.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Submenu> getAll() {
		return (ArrayList<Submenu>) em.createQuery("SELECT s FROM Submenu s")
				.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Submenu> getSubmenuListByDailyMenuAndConsumptionTypeId(
			Long dailyMenuId, Long consumptionTypeId) {
		return (ArrayList<Submenu>) em
				.createQuery(SUBMENU_LIST_BY_DAILY_MENU_AND_CONSUMPTION_TYPE)
				.setParameter("dailyMenuId", dailyMenuId)
				.setParameter("consumptionTypeId", consumptionTypeId)
				.getResultList();
	}

	@Override
	public Submenu getSubmenuByDailyMenuAndConsumptionTypeAndAgeCategory(
			Long dailyMenuId, Long consumptionTypeId, AgeCategory ageCategory) {
		return (Submenu) em
				.createQuery(
						SUBMENU_BY_DAILY_MENU_AND_CONSUMPTION_TYPE_AND_AGE_CATEGORY)
				.setParameter("dailyMenuId", dailyMenuId)
				.setParameter("consumptionTypeId", consumptionTypeId)
				.setParameter("ageCategoryId", ageCategory.getId())
				.getResultList().get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Submenu> getAllByDailyMenuId(Long dailyMenuId) {
		return (ArrayList<Submenu>) em
				.createQuery(SUBMENU_LIST_BY_DAILY_MENU_ID)
				.setParameter("dailyMenuId", dailyMenuId).getResultList();
	}
}
