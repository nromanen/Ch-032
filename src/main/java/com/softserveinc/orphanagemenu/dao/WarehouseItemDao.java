package com.softserveinc.orphanagemenu.dao;

import java.util.List;

import com.softserveinc.orphanagemenu.model.Product;
import com.softserveinc.orphanagemenu.model.WarehouseItem;

/**
 * @author Andriy Vyhidnyj 
 * @author Artur
 */
public interface WarehouseItemDao {

	WarehouseItem getItem(Long id);

	WarehouseItem getItemByProduct(Product product);

	List<WarehouseItem> getAll();

	Long getCount();

	Long getCount(String name);

	List<WarehouseItem> getPage(Integer offset, Integer count);

	List<WarehouseItem> getPage(String name, Integer offset, Integer count);

	Long saveItem(WarehouseItem warehouseItem);

	Long updateItem(WarehouseItem warehouseItem);

	List<Product> getNewProducts();

}
