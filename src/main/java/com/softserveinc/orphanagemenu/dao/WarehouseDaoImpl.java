package com.softserveinc.orphanagemenu.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.softserveinc.orphanagemenu.model.Product;
import com.softserveinc.orphanagemenu.model.WarehouseItem;

@Repository("WarehouseDao")
@Transactional
public class WarehouseDaoImpl implements WarehouseDao {

	@Autowired
	private ProductDao productDAO;

	@PersistenceContext
	private EntityManager em;

	public List<WarehouseItem> getAllItems() {
		String pjql = "SELECT p FROM WarehouseItem p";

		TypedQuery<WarehouseItem> query = em.createQuery(pjql,
				WarehouseItem.class);
		return query.getResultList();
	}

	public List<WarehouseItem> getAllItemsSorted() {
		String pjql = "SELECT p FROM WarehouseItem p inner join p.product x "
				+ "where  ((p.product=x.id) and (p.quantity not between -0.001 and 0.001)) "
				+ "order by x.name ASC";
		//
		TypedQuery<WarehouseItem> query = em.createQuery(pjql,
				WarehouseItem.class);
		return query.getResultList();
	}

	public List<Product> getEmptyProducts() {
		String pjql = "SELECT p FROM Product p  where p.id not in"
				+ "(SELECT z.product from WarehouseItem z where (z.quantity not between -0.001 and 0.001))";

		TypedQuery<Product> query = em.createQuery(pjql, Product.class);
		return query.getResultList();
	}
//TODO save and edit product
	public Long saveItem(String productName, Double quantity) {
		try {
			WarehouseItem warehouseItem = getItem(productName);
			warehouseItem.setQuantity(quantity);
			em.merge(warehouseItem);
			return warehouseItem.getId();
		} catch (javax.persistence.NoResultException e) {
			WarehouseItem warehouseItem = new WarehouseItem();
			Product product = productDAO.getProduct(productName);
			warehouseItem.setProduct(product);
			warehouseItem.setQuantity(quantity);
			em.persist(warehouseItem);
			return warehouseItem.getId();
		}
	}

	public WarehouseItem getItem(String productName) {
		Long productId = productDAO.getProduct(productName).getId();
		String pjql = "SELECT a FROM WarehouseItem a where product_id=\'"
				+ productId + "\'";
		TypedQuery<WarehouseItem> query = em.createQuery(pjql,
				WarehouseItem.class);
		return query.getSingleResult();

	}

}
