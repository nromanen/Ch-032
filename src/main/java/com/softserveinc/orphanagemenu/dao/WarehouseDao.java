package com.softserveinc.orphanagemenu.dao;

import java.util.List;


import com.softserveinc.orphanagemenu.model.Product;
import com.softserveinc.orphanagemenu.model.WarehouseItem;

public interface WarehouseDao {

	public List<WarehouseItem> getAllItems();
	
	public Long getWarehouseItemsQuantity();
	
	public List<WarehouseItem> getItemsByCount(Integer offset, Integer count);
	
	public List<Product> getMissingProducts();
	
	public Long saveItem(String productName, Double quantity);

	public WarehouseItem getItem(String productName);

	public WarehouseItem getItem(Long id) ;
	
	public List<WarehouseItem> getLikeName(String name, Integer offset, Integer count);
	
	public Long getLikeNameQuantity(String name) ;
	
	

}
