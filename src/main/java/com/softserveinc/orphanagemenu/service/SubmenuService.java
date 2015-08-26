package com.softserveinc.orphanagemenu.service;

import java.util.List;

import com.softserveinc.orphanagemenu.dto.SubmenuEditDto;
import com.softserveinc.orphanagemenu.forms.FactProductsQuantityForm;
import com.softserveinc.orphanagemenu.model.Submenu;

public interface SubmenuService {

	List<Submenu> getSubmenuListByDailyMenuAndConsumptionTypeId(Long dailyMenuId, Long consumptionTypeId);

	FactProductsQuantityForm getFactProductsQuantityForm(String dailyMenuId, String dishId, String consumptionTypeId);

	FactProductsQuantityForm getStandartComponentQuantityForm(FactProductsQuantityForm factProductsQuantityForm);

	void saveFactProductQuantity(FactProductsQuantityForm factProductsQuantityForm);

	public Submenu getById(Long id);

	public List<SubmenuEditDto> getSubmenuEditDtoList(Long dailyMenuId, Long consumptionTypeId);

	public String getConsumptionTypeName(Long consumptionTypeId);
}
