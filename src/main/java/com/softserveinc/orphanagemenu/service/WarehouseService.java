package com.softserveinc.orphanagemenu.service;

import java.util.List;

import com.softserveinc.orphanagemenu.forms.WarehouseItemForm;
import com.softserveinc.orphanagemenu.model.Product;
import com.softserveinc.orphanagemenu.model.WarehouseItem;

public interface WarehouseService {

	WarehouseItem getItem(String name);

	WarehouseItem getItem(Long id);

	Long saveItem(String name, Double quantity);

	Long getCount();

	Long getCount(String name);

	List<WarehouseItem> getAll();

	List<Product> getNewProducts();

	List<WarehouseItem> getPage(Integer offset, Integer count);

	List<WarehouseItem> getPage(String name, Integer offset, Integer count);

	WarehouseItemForm getForm(Long id) throws NullPointerException;

}
