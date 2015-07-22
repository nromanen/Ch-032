package com.softserveinc.orphanagemenu.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.softserveinc.orphanagemenu.model.Dimension;

@Repository("DimensionDao")
@Transactional
public class DimensionDaoImpl implements DimensionDao {

	@PersistenceContext
	private EntityManager em;

	// Повертає існуючий dimensionId по назві, якщо немає, то створює такий
	public Long save(String dimensionName) {
		try {
			Dimension dimension = getDimension(dimensionName.trim().toLowerCase());
			return dimension.getId();
		} catch (javax.persistence.NoResultException e) {
			Dimension dimension = new Dimension(dimensionName);
			em.merge(dimension);
			return dimension.getId();
		}
	}

	// Повертає діменшн по Id
	public Dimension getDimension(Long dimensionId) {
		Dimension dimension = em.find(Dimension.class, dimensionId);
		return dimension;
	}

	// Повертає діменшн по назві
	public Dimension getDimension(String dimensionName) {
		TypedQuery<Dimension> query = em.createQuery(
				"SELECT d FROM Dimension d WHERE name=\'"
						+ dimensionName.trim().toLowerCase() + "\'", Dimension.class);
		Dimension dimension = query.getSingleResult();
		return dimension;
	}

	public List<Dimension> getAll() {
		TypedQuery<Dimension> query = em.createQuery(
				"SELECT d FROM Dimension d", Dimension.class);
		List<Dimension> dimensions = query.getResultList();
		return dimensions;
	}

}
