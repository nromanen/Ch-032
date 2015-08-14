package com.softserveinc.orphanagemenu.service;

import java.util.List;

import com.softserveinc.orphanagemenu.forms.FactProductsQuantityForm;
import com.softserveinc.orphanagemenu.model.Submenu;

public interface SubmenuService {

	List<Submenu> getSubmenuListByDailyMenuAndConsumptionTypeId(String dailyMenuId,
			String consumptionTypeId);
	
	FactProductsQuantityForm getFactProductsQuantityForm (List<Submenu> Submenus);

}
