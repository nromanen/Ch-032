package com.softserveinc.orphanagemenu.dao;

import java.util.Date;
import java.util.List;

import com.softserveinc.orphanagemenu.model.DailyMenu;

public interface DailyMenuDao {
	DailyMenu save(DailyMenu dailyMenu);
	void delete(DailyMenu dailyMenu);
	DailyMenu getById(Long id);
	DailyMenu getByDate(Date date);
	List<DailyMenu> getAll();
	List<DailyMenu> getFromCurrentDateToFutureDate(Date futureDate);
	void print();
}
