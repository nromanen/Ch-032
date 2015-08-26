package com.softserveinc.orphanagemenu.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.softserveinc.orphanagemenu.model.Submenu;

@Repository("submenuDao")
@Transactional
public class SubmenuDaoImpl implements SubmenuDao {

	private static final String SUBMENU_LIST_BY_DAILY_MENU_AND_CONSUMPTION_TYPE_ID = "SELECT s FROM Submenu s WHERE s.dailyMenu.id = :dailyMenuId AND s.consumptionType.id = :consumptionTypeId";

	@PersistenceContext
	private EntityManager em;

	@Override
	public Submenu save(Submenu submenu) {
		em.persist(submenu);
		return null;
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
				.createQuery(SUBMENU_LIST_BY_DAILY_MENU_AND_CONSUMPTION_TYPE_ID)
				.setParameter("dailyMenuId", dailyMenuId)
				.setParameter("consumptionTypeId", consumptionTypeId)
				.getResultList();
	}
}
