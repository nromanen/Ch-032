package com.softserveinc.orphanagemenu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.softserveinc.orphanagemenu.dao.SubmenuDao;
import com.softserveinc.orphanagemenu.model.Submenu;

public class SubmenuServiceImpl implements SubmenuService {
	
	@Autowired
	private SubmenuDao submenuDao;

	@Override
	public List<Submenu> getSubmenuListByDailyMenuAndConsumptionTypeId(
			String dailyMenuId, String consumptionTypeId) {
		return this.submenuDao.getSubmenuListByDailyMenuAndConsumptionTypeId(dailyMenuId, consumptionTypeId);
	}

}
