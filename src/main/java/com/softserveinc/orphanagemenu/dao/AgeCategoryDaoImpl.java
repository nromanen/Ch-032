package com.softserveinc.orphanagemenu.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.softserveinc.orphanagemenu.model.AgeCategory;

@Repository("ageCategoryDao")
@Transactional
public class AgeCategoryDaoImpl implements AgeCategoryDao {
	
	@PersistenceContext
	private EntityManager em;
	
	
	@Override
	@SuppressWarnings("unchecked")
	public List<AgeCategory> getAllAgeCategory() {
		return (List<AgeCategory>)em.createQuery("SELECT a FROM AgeCategory a").getResultList();
	}
	
	
	@Override
	public Long getCount() {
		return em.createQuery("SELECT Count(a) FROM AgeCategory a", Long.class)
				.getSingleResult();
	}
}
