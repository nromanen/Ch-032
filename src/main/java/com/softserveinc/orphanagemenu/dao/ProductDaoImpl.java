package com.softserveinc.orphanagemenu.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.softserveinc.orphanagemenu.model.AgeCategory;
import com.softserveinc.orphanagemenu.model.Dimension;
import com.softserveinc.orphanagemenu.model.Product;

@Repository("productDaoImpl")
@Transactional
public class ProductDaoImpl implements ProductDao {

	@PersistenceContext
	private EntityManager em;

	public void saveProduct(Product product) {
		em.persist(product);
	}

	@SuppressWarnings("unchecked")
	public List<Product> getAllProduct(String... order) {
		String sort = (order.length == 0) ? "asc" : order[0];
		return (List<Product>) em.createQuery(
				"SELECT p FROM Product p ORDER BY p.name " + sort)
				.getResultList();
	}

	public Product getProductById(Long id) {
		return (Product) em.createQuery(
				"SELECT p FROM Product p WHERE p.id=" + id).getSingleResult();
	}

	@SuppressWarnings("unchecked")
	public List<Dimension> getAllDimension() {
		return (List<Dimension>) em.createQuery("SELECT d FROM Dimension d")
				.getResultList();
	}

	@Override
	public void updateProduct(Product product) {
		em.merge(product);
	}

	public Product getProduct(String productName) {
		try {
			return em
					.createQuery(
							"SELECT p FROM Product p WHERE LOWER(p.name)=?",
							Product.class)
					.setParameter(1, productName.toLowerCase())
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AgeCategory> getAllAgeCategory() {
		return (List<AgeCategory>) em
				.createQuery("SELECT a FROM AgeCategory a").getResultList();
	}

	@Override
	public Dimension getDimensionByName(String dimension) {
		try {
			return em
					.createQuery("SELECT d FROM Dimension d WHERE d.name=?",
							Dimension.class).setParameter(1, dimension)
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

}
