package com.softserveinc.orphanagemenu.dao;

import java.util.Iterator;
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
		String sql = " SELECT wi FROM WarehouseItem wi WHERE wi.quantity != 0 order by wi.product.name ASC ";
		TypedQuery<WarehouseItem> query = em.createQuery(sql,
				WarehouseItem.class);
		return query.getResultList();
	}
	
	public Long getWarehouseItemsQuantity() {
		String sql = "SELECT Count(wi) FROM WarehouseItem wi";
		TypedQuery<Long> query = em.createQuery(sql, Long.class);
		return query.getSingleResult();
	}
	
	public List<WarehouseItem> getItemsByCount(Integer offset, Integer count) {
		String sql = " SELECT wi FROM WarehouseItem wi WHERE wi.quantity != 0 order by wi.product.name ASC ";
		TypedQuery<WarehouseItem> query = em.createQuery(sql, WarehouseItem.class);
		return query.setFirstResult(offset).setMaxResults(count).getResultList();
	}

	public List<Product> getMissingProducts() {
		String pjql = "SELECT p FROM Product p  where p.id not in"
				+ "(SELECT z.product from WarehouseItem z where z.quantity !=0 ))";
		TypedQuery<Product> query = em.createQuery(pjql, Product.class);
		List<Product> productList = query.getResultList();

		Iterator<Product> iterator = productList.iterator();
		while (iterator.hasNext()) {
			saveItem(iterator.next().getName(), 0d);
		}

		String sql = " SELECT wi.product FROM WarehouseItem wi WHERE wi.quantity = 0 order by wi.product.name ASC ";
		query = em.createQuery(sql, Product.class);
		return query.getResultList();
	}

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
		String sql = "SELECT a FROM WarehouseItem a where product_id=\'"
				+ productId + "\'";
		TypedQuery<WarehouseItem> query = em.createQuery(sql,
				WarehouseItem.class);
		return query.getSingleResult();

	}

	public WarehouseItem getItem(Long id) {
		WarehouseItem warehouseItem = em.find(WarehouseItem.class, id);
		return warehouseItem;

	}

	@Override
	public List<WarehouseItem> getLikeName(String name) {

		String sql = " SELECT wi FROM WarehouseItem wi WHERE LOWER(wi.product.name) LIKE  :searchKeyword";
		TypedQuery<WarehouseItem> query = em.createQuery(sql,
				WarehouseItem.class);
		query.setParameter("searchKeyword", "%" + name.toLowerCase() + "%");

		return query.getResultList();

	}

}
