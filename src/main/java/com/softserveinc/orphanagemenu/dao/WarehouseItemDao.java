package com.softserveinc.orphanagemenu.dao;

import java.util.List;

import com.softserveinc.orphanagemenu.model.Product;
import com.softserveinc.orphanagemenu.model.WarehouseItem;

public interface WarehouseItemDao {
	
	public WarehouseItem getItem(Long id) ;
	public WarehouseItem getItemByProduct(Product product) ;
	
	public List<WarehouseItem> getAll();
	
	public Long getCount();
	public Long getCount(String name) ;
	
	public List<WarehouseItem> getPage(Integer offset, Integer count);
	public List<WarehouseItem> getPage(String name, Integer offset, Integer count);
		
}
