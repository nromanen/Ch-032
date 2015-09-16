package com.softserveinc.orphanagemenu.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.softserveinc.orphanagemenu.model.Product;
import com.softserveinc.orphanagemenu.model.WarehouseItem;

@Repository("WarehouseItemDao")
@Transactional
public class WarehouseItemDaoImpl implements WarehouseItemDao {

	@PersistenceContext
	private EntityManager em;

	public WarehouseItem getItem(Long id) {
		return em.find(WarehouseItem.class, id);
	}

	@Override
	public WarehouseItem getItemByProduct(Product product) {
		String sql = "SELECT wi FROM WarehouseItem wi where wi.product = :product";
	
		@SuppressWarnings("unchecked")
		List<WarehouseItem> warehouseItems = (List<WarehouseItem>) em.createQuery(sql)
				.setParameter("product", product)
				.getResultList();
		 WarehouseItem warehouseItem = null;
		 if (warehouseItems.size() != 0){
			 warehouseItem = warehouseItems.get(0);
		 }
		 return warehouseItem;
	}
	public List<WarehouseItem> getAll() {
		String sql = " SELECT wi FROM WarehouseItem wi WHERE wi.quantity != 0 order by wi.product.name ASC ";
		return em.createQuery(sql, WarehouseItem.class).getResultList();
	}

	public Long getCount() {
		String sql = "SELECT Count(wi) FROM WarehouseItem wi WHERE wi.quantity != 0";
		return em.createQuery(sql, Long.class).getSingleResult();
	}

	public Long getCount(String name) {
		String sql = "SELECT Count(wi) FROM WarehouseItem wi WHERE LOWER(wi.product.name) LIKE :searchKeyword and wi.quantity != 0";
		return em
				.createQuery(sql, Long.class)
				.setParameter("searchKeyword",
						"%" + name.trim().toLowerCase() + "%")
				.getSingleResult();
	}

	public List<WarehouseItem> getPage(Integer offset, Integer count) {
		String sql = " SELECT wi FROM WarehouseItem wi WHERE wi.quantity != 0 order by wi.product.name ASC ";
		return em.createQuery(sql, WarehouseItem.class).setFirstResult(offset)
				.setMaxResults(count).getResultList();
	}

	@Override
	public List<WarehouseItem> getPage(String name, Integer offset,
			Integer count) {
		String sql = " SELECT wi FROM WarehouseItem wi WHERE LOWER(wi.product.name) LIKE  :searchKeyword  and wi.quantity != 0 "
				+ "order by wi.product.name ASC";
		return em
				.createQuery(sql, WarehouseItem.class)
				.setParameter("searchKeyword",
						"%" + name.toLowerCase().trim() + "%")
				.setFirstResult(offset).setMaxResults(count).getResultList();
	}


	@Override
	public Long saveItem(WarehouseItem warehouseItem) {
		 em.persist(warehouseItem);
		 return warehouseItem.getId();
	}

	@Override
	public Long updateItem(WarehouseItem warehouseItem) {
		return em.merge(warehouseItem).getId();

	}

	public List<Product> getNewProducts() {
		String sql = " SELECT wi.product FROM WarehouseItem wi WHERE wi.quantity = 0 order by wi.product.name ASC ";
		return em.createQuery(sql, Product.class).getResultList();
	}

	}
