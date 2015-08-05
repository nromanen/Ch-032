package com.softserveinc.orphanagemenu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserveinc.orphanagemenu.dao.WarehouseDao;
import com.softserveinc.orphanagemenu.exception.MenuException;
import com.softserveinc.orphanagemenu.forms.WarehouseItemForm;
import com.softserveinc.orphanagemenu.model.Product;
import com.softserveinc.orphanagemenu.model.WarehouseItem;

@Service
public class WarehouseService {

	@Autowired
	private WarehouseDao warehouseDAO;

	@Transactional
	public void addProduct(String name, Double quantity) throws Exception {
		warehouseDAO.saveItem(name, quantity);
	}

	@Transactional
	public Long getWarehouseItemsQuantity() {
		return warehouseDAO.getWarehouseItemsQuantity();
	}

	@Transactional
	public List<WarehouseItem> getAllProductsAndQuantity() {
		return warehouseDAO.getAllItems();
	}

	@Transactional
	public List<WarehouseItem> getPieceOfAllProductsAndQuantity(Integer offset,
			Integer count) throws Exception {
		return warehouseDAO.getItemsByCount(offset, count);
	}

	public WarehouseItem geItemByName(String name) throws Exception {
		return warehouseDAO.getItem(name);
	}

	public List<Product> getAllEmptyItems() throws Exception {
		return warehouseDAO.getMissingProducts();
	}

	@Transactional
	public WarehouseItemForm getForm(Long id) throws MenuException {
		WarehouseItemForm form = new WarehouseItemForm();

		WarehouseItem item = warehouseDAO.getItem(id);

		form.setId(item.getId().toString());
		form.setDimension(item.getProduct().getDimension().getName());
		form.setItemName(item.getProduct().getName());
		form.setQuantity(item.getQuantity().toString());
		return form;
	}

	public Boolean saveForm(WarehouseItemForm form) throws Exception {
		String name = form.getItemName();
		Double quantity = Double.parseDouble(form.getQuantity());
		warehouseDAO.saveItem(name, quantity);

		return true;

	}
	
	@Transactional
	public List<WarehouseItem> searchNames(String name, Integer offset,
			Integer count) throws Exception {
		List<WarehouseItem> findItems = warehouseDAO.getLikeName(name, offset, count);
		return findItems;
	}
	
	@Transactional
	public Long searchNamesQuantity(String name) throws MenuException{
		return warehouseDAO.getLikeNameQuantity(name);
	}

}
