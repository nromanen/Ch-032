package com.softserveinc.orphanagemenu.dao;

import java.util.List;

import com.softserveinc.orphanagemenu.exception.MenuException;
import com.softserveinc.orphanagemenu.model.Product;
import com.softserveinc.orphanagemenu.model.WarehouseItem;

public interface WarehouseDao {

	public List<WarehouseItem> getAllItems();
	
	public Long getWarehouseItemsQuantity();
	
	public List<WarehouseItem> getItemsByCount(Integer offset, Integer count) throws MenuException;
	
	public List<Product> getMissingProducts() throws MenuException;
	
	public Long saveItem(String productName, Double quantity) throws MenuException;

	public WarehouseItem getItem(String productName) throws MenuException;

	public WarehouseItem getItem(Long id) throws MenuException;
	
	public List<WarehouseItem> getLikeName(String name, Integer offset, Integer count) throws MenuException;
	
	public Long getLikeNameQuantity(String name) throws MenuException;
	
	

}
