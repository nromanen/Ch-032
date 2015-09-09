package com.softserveinc.orphanagemenu.dao;

import java.util.List;

import com.softserveinc.orphanagemenu.model.AgeCategory;
import com.softserveinc.orphanagemenu.model.Dish;
import com.softserveinc.orphanagemenu.model.Submenu;

/**
 * @author Vladimir Perepeliuk
 * @author Olexii Riabokon
 * @author Sviatoslav Fedechko
 */
public interface SubmenuDao {

	Submenu save(Submenu submenu);

	Submenu update(Submenu submenu);

	void delete(Submenu submenu);

	Submenu getById(Long id);

	List<Submenu> getAll();

	List<Submenu> getSubmenuListByDailyMenuAndConsumptionTypeId(Long dailyMenuId, Long consumptionTypeId);

	Submenu getSubmenuByDailyMenuAndConsumptionTypeAndAgeCategory(Long dailyMenuId, Long consumptionTypeId, AgeCategory ageCategory);

	List<Submenu> getAllByDailyMenuId(Long id);
	
}
