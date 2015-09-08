package com.softserveinc.orphanagemenu.service;

import java.util.List;
import java.util.Map;

import com.softserveinc.orphanagemenu.dto.SubmenuEditPageDto;
import com.softserveinc.orphanagemenu.forms.FactProductsQuantityForm;
import com.softserveinc.orphanagemenu.model.Submenu;

public interface SubmenuService {

	List<Submenu> getSubmenuListByDailyMenuAndConsumptionTypeId(Long dailyMenuId, Long consumptionTypeId);

	FactProductsQuantityForm getFactProductsQuantityForm(String dailyMenuId, String dishId, String consumptionTypeId);

	FactProductsQuantityForm getStandartComponentQuantityForm(FactProductsQuantityForm factProductsQuantityForm);

	void saveFactProductQuantity(FactProductsQuantityForm factProductsQuantityForm);

	Submenu getById(Long id);

	SubmenuEditPageDto createSubmenuEditPageDto(Long dailyMenuId, Long consumptionTypeId);
	
	void addDishToSubmenus(Long dailyMenuId, Long consumptionTypeId, Long dishId);
	
	void removeDishFromSubmenus(Long dailyMenuId, Long consumptionTypeId, Long dishId);
	
	void setChildQuantityToSubmenus(Long dailyMenuId, Long consumptionTypeId, Map<String, String> params);

}
