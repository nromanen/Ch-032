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
	public Submenu getByID(Long id) {
		return em.find(Submenu.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Submenu> getAll() {
		return (ArrayList<Submenu>)em.createQuery("SELECT s FROM Submenu s").getResultList();
	}

}
