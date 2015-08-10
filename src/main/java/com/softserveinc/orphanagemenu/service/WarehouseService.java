package com.softserveinc.orphanagemenu.service;

import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserveinc.orphanagemenu.dao.ProductDao;
import com.softserveinc.orphanagemenu.dao.WarehouseItemDao;
import com.softserveinc.orphanagemenu.forms.WarehouseItemForm;
import com.softserveinc.orphanagemenu.model.Product;
import com.softserveinc.orphanagemenu.model.WarehouseItem;

@Service
public class WarehouseService {

	@Autowired
	private WarehouseItemDao warehouseItemDAO;

	@Autowired
	private ProductDao productDAO;

	@PersistenceContext
	private EntityManager em;

	public WarehouseItem getItem(String name) {
		Long productId = productDAO.getProduct(name).getId();
		String sql = "SELECT a FROM WarehouseItem a where product_id=\'" + productId + "\'";
		return em.createQuery(sql, WarehouseItem.class).getSingleResult();
	}

	public WarehouseItem getItem(Long id) {
		return warehouseItemDAO.getItem(id);
	}

	@Transactional
	public Long saveItem(String name, Double quantity) {
		try {
			WarehouseItem warehouseItem = getItem(name);
			warehouseItem.setQuantity(quantity);
			em.merge(warehouseItem);
			return warehouseItem.getId();
		} catch (javax.persistence.NoResultException e) {
			WarehouseItem warehouseItem = new WarehouseItem();
			Product product = productDAO.getProduct(name);
			warehouseItem.setProduct(product);
			warehouseItem.setQuantity(quantity);
			em.persist(warehouseItem);
			return warehouseItem.getId();
		}
	}
	
	@Transactional
	public Long getCount() {
		return warehouseItemDAO.getCount();
	}

	@Transactional
	public Long getCount(String name) {
		return warehouseItemDAO.getCount(name);
	}

	@Transactional
	public List<WarehouseItem> getAll() {
		return warehouseItemDAO.getAll();
	}

	public List<Product> getAllEmpty() {
		addMissingProducts();
		String sql = " SELECT wi.product FROM WarehouseItem wi WHERE wi.quantity = 0 order by wi.product.name ASC ";
		return em.createQuery(sql, Product.class).getResultList();
	}

	public void addMissingProducts() {
		String sql = "SELECT p FROM Product p  where p.id not in" + "(SELECT z.product from WarehouseItem z where z.quantity !=0 ))";
		List<Product> productList = em.createQuery(sql, Product.class).getResultList();
		Iterator<Product> iterator = productList.iterator();
		while (iterator.hasNext()) {
			saveItem(iterator.next().getName(), 0d);
		}
	}

	@Transactional
	public List<WarehouseItem> getPage(Integer offset, Integer count) {
		return warehouseItemDAO.getPage(offset, count);
	}

	@Transactional
	public List<WarehouseItem> getPage(String name, Integer offset, Integer count) {
		return warehouseItemDAO.getPage(name, offset, count);
	}

	@Transactional
	public WarehouseItemForm getForm(Long id) throws NullPointerException {
		WarehouseItemForm form = new WarehouseItemForm();
		WarehouseItem item = warehouseItemDAO.getItem(id);

		if (item != null) {
			form.setId(item.getId().toString());
			form.setDimension(item.getProduct().getDimension().getName());
			form.setItemName(item.getProduct().getName());
			form.setQuantity(item.getQuantity().toString());
		}
		return form;
	}

	public Boolean saveForm(WarehouseItemForm form) {
		String name = form.getItemName();
		Double quantity = Double.parseDouble(form.getQuantity());
		saveItem(name, quantity);
		return true;
	}

}
