package com.softserveinc.orphanagemenu.dao;

import java.util.List;

import com.softserveinc.orphanagemenu.model.Product;
import com.softserveinc.orphanagemenu.model.WarehouseItem;

public interface WarehouseDao {

	public List<WarehouseItem> getAllItems();

	public Long saveItem(String productName, Double quantity);

	public WarehouseItem getItem(String productName);

	public List<WarehouseItem> getAllItemsSorted();

	public List<Product> getMissingProducts();

	public WarehouseItem getItem(Long id);

}
