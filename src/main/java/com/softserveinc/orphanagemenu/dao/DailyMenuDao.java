package com.softserveinc.orphanagemenu.dao;

import java.util.List;

import com.softserveinc.orphanagemenu.model.DailyMenu;

public interface DailyMenuDao {
	DailyMenu save(DailyMenu dailyMenu);
	void delete(DailyMenu dailyMenu);
	DailyMenu getByID(Long id);
	List<DailyMenu> getAll();
}
