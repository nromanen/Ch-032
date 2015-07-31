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
		String sql = "SELECT p FROM WarehouseItem p";
		TypedQuery<WarehouseItem> query = em.createQuery(sql,
				WarehouseItem.class);
		return query.getResultList();
	}

	public List<WarehouseItem> getAllItemsSorted() {
		String sql =" SELECT wi FROM WarehouseItem wi WHERE wi.quantity != 0 order by wi.product.name ASC ";
		TypedQuery<WarehouseItem> query = em.createQuery(sql, WarehouseItem.class);
		return query.getResultList();
	}

	public List<Product> getMissingProducts() {
		String pjql = "SELECT p FROM Product p  where p.id not in"
				+ "(SELECT z.product from WarehouseItem z where (z.quantity not between -0.001 and 0.001))";

		TypedQuery<Product> query = em.createQuery(pjql, Product.class);
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
				 return  warehouseItem;

	}

	@Override
	public List<WarehouseItem> getLikeName(String name) {
		//TODO ignore case
		String sql = " SELECT wi FROM WarehouseItem wi WHERE wi.product.name LIKE :searchKeyword";
		TypedQuery<WarehouseItem> query = em.createQuery(sql,WarehouseItem.class);
	    query.setParameter("searchKeyword", "%"+name+"%");
	     
	    return query.getResultList();
								
	}
	


}
