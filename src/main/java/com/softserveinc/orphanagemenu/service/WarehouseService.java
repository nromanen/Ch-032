package com.softserveinc.orphanagemenu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserveinc.orphanagemenu.dao.WarehouseDao;
import com.softserveinc.orphanagemenu.model.Product;
import com.softserveinc.orphanagemenu.model.WarehouseItem;

@Service
public class WarehouseService {

	@Autowired
	private WarehouseDao warehouseDAO;

	@Transactional
	public List<WarehouseItem> getAllItems() {
		return warehouseDAO.getAllItemsSorted();
	}

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
		
		return warehouseDAO.getEmptyProducts();
	}
	

}
