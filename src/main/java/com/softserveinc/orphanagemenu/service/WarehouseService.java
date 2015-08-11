package com.softserveinc.orphanagemenu.service;


import java.util.List;
import com.softserveinc.orphanagemenu.forms.WarehouseItemForm;
import com.softserveinc.orphanagemenu.model.Product;
import com.softserveinc.orphanagemenu.model.WarehouseItem;


public interface WarehouseService {

	public WarehouseItem getItem(String name);
	public WarehouseItem getItem(Long id);
	public Long saveItem(String name, Double quantity);
	public Long getCount();
	public Long getCount(String name);
	public List<WarehouseItem> getAll();
	public List<Product> getAllEmpty();
	public void addMissingProducts();
	public List<WarehouseItem> getPage(Integer offset, Integer count);
	public List<WarehouseItem> getPage(String name, Integer offset, Integer count);
	public WarehouseItemForm getForm(Long id) throws NullPointerException;
	public Boolean saveForm(WarehouseItemForm form);

}
