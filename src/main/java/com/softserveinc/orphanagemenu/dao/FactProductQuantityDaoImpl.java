package com.softserveinc.orphanagemenu.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.softserveinc.orphanagemenu.model.ComponentWeight;
import com.softserveinc.orphanagemenu.model.FactProductQuantity;
import com.softserveinc.orphanagemenu.model.Submenu;

/**
 * @author Vladimir Perepeliuk
 * @author Olexii Riabokon
 * @author Sviatoslav Fedechko
 */
@Repository("factProductQuantityDao")
@Transactional
public class FactProductQuantityDaoImpl implements FactProductQuantityDao {

	private static final String FACT_PRODUCT_QUANTITY_BY_SUBMENU_AND_COMPONENT_WEIGHT = 
			"SELECT fpq FROM FactProductQuantity fpq WHERE fpq.submenu = :submenu and fpq.componentWeight = :componentWeight";

	@PersistenceContext
	private EntityManager em;

	@Override
	public FactProductQuantity getById(Long id) {
		return em.find(FactProductQuantity.class, id);
	}

	@Override
	public FactProductQuantity getBySubmenuAndComponentWeight(Submenu submenu,
			ComponentWeight componentWeight) {
		return (FactProductQuantity) em
				.createQuery(
						FACT_PRODUCT_QUANTITY_BY_SUBMENU_AND_COMPONENT_WEIGHT)
				.setParameter("submenu", submenu)
				.setParameter("componentWeight", componentWeight)
				.getResultList().get(0);

	}

	@Override
	public FactProductQuantity getFactProductQuantity(Submenu submenu,
			ComponentWeight componentWeight) {
		return em
				.createQuery(
						FACT_PRODUCT_QUANTITY_BY_SUBMENU_AND_COMPONENT_WEIGHT,
						FactProductQuantity.class)
				.setParameter("submenu", submenu)
				.setParameter("componentWeight", componentWeight)
				.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FactProductQuantity> getFactProductQuantityListByIdies(
			List<Long> factProductQuantityIds) {
		return (List<FactProductQuantity>) em
				.createQuery(
						"SELECT fpq FROM FactProductQuantity fpq WHERE fpq.id IN (:ids)")
				.setParameter("ids", factProductQuantityIds).getResultList();
	}

	@Override
	public void save(FactProductQuantity factProductQuantity) {
		em.merge(factProductQuantity);
		
	}
}
