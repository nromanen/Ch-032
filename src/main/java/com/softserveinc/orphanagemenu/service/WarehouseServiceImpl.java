package com.softserveinc.orphanagemenu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserveinc.orphanagemenu.dao.ProductDao;
import com.softserveinc.orphanagemenu.dao.WarehouseItemDao;
import com.softserveinc.orphanagemenu.forms.WarehouseItemForm;
import com.softserveinc.orphanagemenu.model.Product;
import com.softserveinc.orphanagemenu.model.WarehouseItem;

@Service("warehouseService")
@Transactional
public class WarehouseServiceImpl implements WarehouseService {

	@Autowired
	private WarehouseItemDao warehouseItemDAO;

	@Autowired
	private ProductDao productDAO;

	@Transactional
	public WarehouseItem getItem(String name) {
		Product product = productDAO.getProduct(name);
		return warehouseItemDAO.getItemByProduct(product);
	}

	@Transactional
	public WarehouseItem getItem(Long id) {
		return warehouseItemDAO.getItem(id);
	}

	@Transactional
	public Long saveItem(String name, Double quantity) {

		WarehouseItem warehouseItem = warehouseItemDAO
				.getItemByProduct(productDAO.getProduct(name));
		if (warehouseItem != null) {
			warehouseItem.setQuantity(quantity);
			return warehouseItemDAO.updateItem(warehouseItem);
		} else {
			Product product = productDAO.getProduct(name);
			warehouseItem = new WarehouseItem();
			warehouseItem.setProduct(product);
			warehouseItem.setQuantity(quantity);

			return warehouseItemDAO.saveItem(warehouseItem);
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

	@Transactional
	public List<Product> getNewProducts() {
		return warehouseItemDAO.getNewProducts();
	}

	

	@Transactional
	public List<WarehouseItem> getPage(Integer offset, Integer count) {
		return warehouseItemDAO.getPage(offset, count);
	}

	@Transactional
	public List<WarehouseItem> getPage(String name, Integer offset,
			Integer count) {
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
			form.setQuantity(item.getQuantity().toString().replace(".", ","));
		}
		return form;
	}


}
