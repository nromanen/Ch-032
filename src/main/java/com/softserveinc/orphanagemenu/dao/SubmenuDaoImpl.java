package com.softserveinc.orphanagemenu.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.softserveinc.orphanagemenu.model.AgeCategory;
import com.softserveinc.orphanagemenu.model.Component;
import com.softserveinc.orphanagemenu.model.ComponentWeight;
import com.softserveinc.orphanagemenu.model.Dish;
import com.softserveinc.orphanagemenu.model.FactProductQuantity;
import com.softserveinc.orphanagemenu.model.Submenu;

/**
 * @author Vladimir Perepeliuk
 * @author Olexii Riabokon
 * @author Sviatoslav Fedechko
 */
@Repository("submenuDao")
@Transactional
public class SubmenuDaoImpl implements SubmenuDao {

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
		return (ArrayList<Submenu>) em.createQuery("SELECT s FROM Submenu s").getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Submenu> getSubmenuListByDailyMenuAndConsumptionTypeId(Long dailyMenuId, Long consumptionTypeId) {

		return (ArrayList<Submenu>) em.createNamedQuery("submenuListByDailyMenuAndConsumptionTypeId")
				.setParameter("dailyMenuId", dailyMenuId).setParameter("consumptionTypeId", consumptionTypeId).getResultList();
	}

	@Override
	public Submenu getSubmenuByDailyMenuAndConsumptionTypeAndAgeCategory(Long dailyMenuId, Long consumptionTypeId, AgeCategory ageCategory) {
		return (Submenu) em.createNamedQuery("submenuByDailyMenuAndConsumptionTypeAndAgeCategory").setParameter("dailyMenuId", dailyMenuId)
				.setParameter("consumptionTypeId", consumptionTypeId).setParameter("ageCategoryId", ageCategory.getId()).getResultList()
				.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Submenu> getAllByDailyMenuId(Long dailyMenuId) {
		return (ArrayList<Submenu>) em.createNamedQuery("submenuListByDailyMenuId").setParameter("dailyMenuId", dailyMenuId)
				.getResultList();
	}
}
