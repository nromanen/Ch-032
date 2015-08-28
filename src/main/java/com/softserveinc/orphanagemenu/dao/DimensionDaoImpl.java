package com.softserveinc.orphanagemenu.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.softserveinc.orphanagemenu.model.Dimension;

@Repository("DimensionDao")
@Transactional
public class DimensionDaoImpl implements DimensionDao {

	@PersistenceContext
	private EntityManager em;

	// ������� �������� dimensionId �� ����, ���� ����, �� ������� �����
	public Long save(String dimensionName) {
		try {
			Dimension dimension = getDimension(dimensionName.trim()
					.toLowerCase());
			return dimension.getId();
		} catch (NoResultException e) {
			Dimension dimension = new Dimension(dimensionName);
			em.merge(dimension);
			return dimension.getId();
		}
	}

	// ������� ������ �� Id
	public Dimension getDimension(Long dimensionId) {
		return em.find(Dimension.class, dimensionId);
	}

	// ������� ������ �� ����
	public Dimension getDimension(String dimensionName) {
		return em.createQuery(
				"SELECT d FROM Dimension d WHERE name=\'"
						+ dimensionName.trim().toLowerCase() + "\'",
				Dimension.class).getSingleResult();
	}

	public List<Dimension> getAllDimension() {
		return em.createQuery("SELECT d FROM Dimension d", Dimension.class)
				.getResultList();
	}

}
