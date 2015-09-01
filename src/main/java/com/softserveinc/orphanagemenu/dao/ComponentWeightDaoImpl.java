package com.softserveinc.orphanagemenu.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.softserveinc.orphanagemenu.model.ComponentWeight;

@Repository("componentWeightDao")
@Transactional
public class ComponentWeightDaoImpl implements ComponentWeightDao {
	
	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<ComponentWeight> getComponentWeightListByIdies(
			List<Long> componentWeightIds) {
		return (List<ComponentWeight>) em
				.createQuery(
						"SELECT cw FROM ComponentWeight cw WHERE cw.id IN (:ids)")
				.setParameter("ids", componentWeightIds).getResultList();
		
	}
}
