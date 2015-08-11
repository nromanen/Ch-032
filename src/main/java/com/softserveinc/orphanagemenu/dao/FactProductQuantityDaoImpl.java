package com.softserveinc.orphanagemenu.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.softserveinc.orphanagemenu.model.ComponentWeight;
import com.softserveinc.orphanagemenu.model.FactProductQuantity;
import com.softserveinc.orphanagemenu.model.Submenu;

@Repository("factProductQuantityDao")
@Transactional
public class FactProductQuantityDaoImpl implements FactProductQuantityDao {
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public FactProductQuantity getBySubmenuAndComponentWeight(
			Submenu submenu, 
			ComponentWeight componentWeight) {

		String sql = "SELECT fpq FROM FactProductQuantity fpq " 
				+ "WHERE fpq.submenu = :submenu and fpq.componentWeight = :componentWeight";
		
		return  (FactProductQuantity) em.createQuery(sql)
				.setParameter("submenu", submenu)
				.setParameter("componentWeight", componentWeight)
				.getSingleResult();
	}

}
