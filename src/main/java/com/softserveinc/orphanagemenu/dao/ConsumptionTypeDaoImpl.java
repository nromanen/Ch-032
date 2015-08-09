package com.softserveinc.orphanagemenu.dao;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.softserveinc.orphanagemenu.model.AgeCategory;
import com.softserveinc.orphanagemenu.model.ConsumptionType;

@Repository("ageCategoryImpl")
@Transactional
public class ConsumptionTypeDaoImpl implements ConsumptionTypeDao {
	
	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public ArrayList<ConsumptionType> getAll() {
		return (ArrayList<ConsumptionType>)em.createQuery("SELECT ct FROM ConsumptionType ct ORDER BY ct.orderby asc").getResultList();
	}

}
