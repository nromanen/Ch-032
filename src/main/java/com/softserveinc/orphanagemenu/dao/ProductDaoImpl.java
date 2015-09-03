package com.softserveinc.orphanagemenu.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.softserveinc.orphanagemenu.model.Product;

/**
 * @author Pavlo
 * @author Sviatoslav Fedechko
 */
@Repository("productDao")
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

	@Override
	public void updateProduct(Product product) {
		em.merge(product);
	}
	
	@Override
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

	@Override
	public Long getCount() {
		return em.createQuery("SELECT Count(p) FROM Product p", Long.class)
				.getSingleResult();
	}

	@Override
	public List<Product> getPage(Integer offset, Integer pagecount) {
		String sql = "SELECT p FROM Product p ORDER BY name ASC";
		return em.createQuery(sql, Product.class).setFirstResult(offset)
				.setMaxResults(pagecount).getResultList();
	}

	@Override
	public List<Product> getPage(String keyWord, Integer offset,
			Integer pagecount) {
		String sql = "SELECT p FROM Product p WHERE LOWER(name) LIKE :searchKeyword ORDER BY name ASC";
		return em
				.createQuery(sql, Product.class)
				.setParameter("searchKeyword",
						"%" + keyWord.toLowerCase().trim() + "%")
				.setFirstResult(offset).setMaxResults(pagecount).getResultList();
	}

	@Override
	public Long getCount(String keyWord) {
		String sql = "SELECT Count(p) FROM Product p WHERE LOWER(name) LIKE :searchKeyword";
		return em
				.createQuery(sql, Long.class)
				.setParameter("searchKeyword",
						"%" + keyWord.trim().toLowerCase() + "%")
				.getSingleResult();
	}
}
