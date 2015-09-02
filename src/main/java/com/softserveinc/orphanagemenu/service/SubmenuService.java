package com.softserveinc.orphanagemenu.service;

import java.util.List;
import java.util.Map;

import com.softserveinc.orphanagemenu.dto.SubmenuDto;
import com.softserveinc.orphanagemenu.forms.FactProductsQuantityForm;
import com.softserveinc.orphanagemenu.model.Submenu;

public interface SubmenuService {

	List<Submenu> getSubmenuListByDailyMenuAndConsumptionTypeId(Long dailyMenuId, Long consumptionTypeId);

	FactProductsQuantityForm getFactProductsQuantityForm(String dailyMenuId, String dishId, String consumptionTypeId);

	FactProductsQuantityForm getStandartComponentQuantityForm(FactProductsQuantityForm factProductsQuantityForm);

	void saveFactProductQuantity(FactProductsQuantityForm factProductsQuantityForm);

	public Submenu getById(Long id);

	public SubmenuDto getSubmenuDto(Long dailyMenuId, Long consumptionTypeId);
	
	public void addDishToSubmenuList(Long dailyMenuId, Long consumptionTypeId, Long dishId);
	
	public void removeDishFromSubmenuList(Long dailyMenuId, Long consumptionTypeId, Long dishId);
	
	public void setChildQuantityToSubmenuListByDailyMenuAndConsumptionTypeId(Long dailyMenuId, Long consumptionTypeId, Map<String, String> params);

}
