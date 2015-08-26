package com.softserveinc.orphanagemenu.dao;

import java.util.List;

import com.softserveinc.orphanagemenu.model.Submenu;

/**
 * @author Vladimir Perepeliuk
 * @author Olexii Riabokon
 */
public interface SubmenuDao {
	
	Submenu save(Submenu submenu);
	
	void delete(Submenu submenu);
	
	Submenu getById(Long id);
	
	List<Submenu> getAll();

	List<Submenu> getSubmenuListByDailyMenuAndConsumptionTypeId(
			Long dailyMenuId, Long consumptionTypeId);
	
}
