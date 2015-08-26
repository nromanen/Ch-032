package com.softserveinc.orphanagemenu.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.softserveinc.orphanagemenu.model.ConsumptionType;

/**
 * @author Vladimir Perepeliuk
 * @author Olexii Riabokon
 */
@Repository("consumptionTypeDao")
@Transactional
public class ConsumptionTypeDaoImpl implements ConsumptionTypeDao {
	
	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<ConsumptionType> getAll() {
		return (List<ConsumptionType>)em.createQuery("SELECT ct FROM ConsumptionType ct ORDER BY ct.orderby").getResultList();
	}
	
	public ConsumptionType getById(Long id){
		return em.find(ConsumptionType.class, id);
	}

}
