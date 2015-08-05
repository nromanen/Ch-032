package com.softserveinc.orphanagemenu.dao;

import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.softserveinc.orphanagemenu.exception.MenuException;
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

	public List<WarehouseItem> getItemsByCount(Integer offset, Integer count)
			throws MenuException {
		String sql = " SELECT wi FROM WarehouseItem wi WHERE wi.quantity != 0 order by wi.product.name ASC ";
		TypedQuery<WarehouseItem> query = em.createQuery(sql,
				WarehouseItem.class);
		List<WarehouseItem> items = null;
		try {
			items = query.setFirstResult(offset).setMaxResults(count)
					.getResultList();
		} catch (Exception e) {
			MenuException exception = new MenuException("all.dbError");

			throw exception;
		}
		return items;
	}

	public List<Product> getMissingProducts() throws MenuException {
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

	public Long saveItem(String productName, Double quantity)
			throws MenuException {
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
		} catch (Exception e) {
			MenuException exception = new MenuException("all.dbError");

			throw exception;
		}
	}

	public WarehouseItem getItem(String productName) throws MenuException {
		Long productId = productDAO.getProduct(productName).getId();
		String sql = "SELECT a FROM WarehouseItem a where product_id=\'"
				+ productId + "\'";
		TypedQuery<WarehouseItem> query = em.createQuery(sql,
				WarehouseItem.class);
		WarehouseItem item = null;
		try {
			item = query.getSingleResult();
		} catch (javax.persistence.NoResultException e) {
			throw new javax.persistence.NoResultException();
		}

		catch (Exception e) {
			MenuException exception = new MenuException("all.dbError");

			throw exception;
		}
		return item;

	}

	public WarehouseItem getItem(Long id) throws MenuException {

		WarehouseItem warehouseItem;
		try {
			warehouseItem = em.find(WarehouseItem.class, id);

			if (warehouseItem == null)
				throw new NullPointerException();
		} catch (NullPointerException e) {
			MenuException exception = new MenuException("all.wrondData");

			throw exception;
		} catch (Exception e) {
			MenuException exception = new MenuException("all.dbError");

			throw exception;
		}
		return warehouseItem;

	}

	@Override
	public List<WarehouseItem> getLikeName(String name, Integer offset,
			Integer count) throws MenuException {
		List<WarehouseItem> items = null;
		String sql = " SELECT wi FROM WarehouseItem wi WHERE LOWER(wi.product.name) LIKE  :searchKeyword order by wi.product.name ASC";
		TypedQuery<WarehouseItem> query = em.createQuery(sql,
				WarehouseItem.class);
		query.setParameter("searchKeyword", "%" + name.toLowerCase().trim()
				+ "%");
		try {
			items = query.setFirstResult(offset).setMaxResults(count)
					.getResultList();

		} catch (Exception e) {

			MenuException exception = new MenuException("all.dbError");

			throw exception;
		}

		return items;

	}

	public Long getLikeNameQuantity(String name) throws MenuException {

		String sql = " SELECT Count(wi) FROM WarehouseItem wi WHERE LOWER(wi.product.name) LIKE :searchKeyword ";
		TypedQuery<Long> query = em.createQuery(sql, Long.class);
		query.setParameter("searchKeyword", "%" + name.trim().toLowerCase() + "%");
		try {
			return query.getSingleResult();
		} catch (Exception e) {

			MenuException exception = new MenuException("all.dbError");

			throw exception;
		}

	}

}
