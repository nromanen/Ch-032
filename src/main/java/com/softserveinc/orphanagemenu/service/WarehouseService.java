package com.softserveinc.orphanagemenu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserveinc.orphanagemenu.dao.WarehouseDao;
import com.softserveinc.orphanagemenu.model.Product;
import com.softserveinc.orphanagemenu.model.WarehouseItem;
import com.softserveinc.orphanagemenu.validator.warehouse.WarehouseItemForm;

@Service
public class WarehouseService {

	@Autowired
	private WarehouseDao warehouseDAO;

	@Transactional
	public void addProduct(String name, Double quantity) {
		warehouseDAO.saveItem(name, quantity);
	}

	@Transactional
	public List<WarehouseItem> getAllProductsAndQuantity() {
		return warehouseDAO.getAllItems();
	}

	public WarehouseItem geItemByName(String name) {
		return warehouseDAO.getItem(name);
	}

	public List<Product> getAllEmptyItems() {
		return warehouseDAO.getMissingProducts();
	}

	@Transactional
	public WarehouseItemForm getForm(Long id) {
		WarehouseItemForm form = new WarehouseItemForm();

		WarehouseItem item = warehouseDAO.getItem(id);
		form.setId(item.getId().toString());
		form.setDimension(item.getProduct().getDimension().getName());
		form.setItemName(item.getProduct().getName());
		form.setQuantity(item.getQuantity().toString());
		return form;
	}

	public Boolean saveForm(WarehouseItemForm form) {
		String name = form.getItemName();
		Double quantity = Double.parseDouble(form.getQuantity());
		warehouseDAO.saveItem(name, quantity);

		return true;

	}

	public List<WarehouseItem> searchNames(String name) {
		List<WarehouseItem> findItems = warehouseDAO.getLikeName(name);
		return findItems;
	}
	public void addAllMisedWithZeroQuontity(){
		//TODO		
	}

}
