package com.softserveinc.orphanagemenu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softserveinc.orphanagemenu.dao.SubmenuDao;
import com.softserveinc.orphanagemenu.forms.FactProductsQuantityForm;
import com.softserveinc.orphanagemenu.model.Submenu;

@Service
public class SubmenuServiceImpl implements SubmenuService {
	
	@Autowired
	private SubmenuDao submenuDao;

	@Override
	public List<Submenu> getSubmenuListByDailyMenuAndConsumptionTypeId(
			String dailyMenuId, String consumptionTypeId) {
		return this.submenuDao.getSubmenuListByDailyMenuAndConsumptionTypeId(dailyMenuId, consumptionTypeId);
	}

	@Override
	public FactProductsQuantityForm getFactProductsQuantityForm(
			List<Submenu> Submenus) {
		// TODO Auto-generated method stub
		return null;
	}

}
