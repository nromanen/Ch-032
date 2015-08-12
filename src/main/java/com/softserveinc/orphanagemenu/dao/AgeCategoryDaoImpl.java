package com.softserveinc.orphanagemenu.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.softserveinc.orphanagemenu.model.AgeCategory;

@Repository("ageCategoryImpl")
@Transactional
public class AgeCategoryDaoImpl implements AgeCategoryDao {
	
	@PersistenceContext
	private EntityManager em;
	
	
	@SuppressWarnings("unchecked")
	public List<AgeCategory> getAllAgeCategory() {
		return (ArrayList<AgeCategory>)em.createQuery("SELECT a FROM AgeCategory a ORDER BY a.name asc").getResultList();
	}

}
