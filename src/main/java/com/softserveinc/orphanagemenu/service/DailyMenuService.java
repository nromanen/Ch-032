package com.softserveinc.orphanagemenu.service;

import java.util.List;
import com.softserveinc.orphanagemenu.model.DailyMenu;

public interface DailyMenuService {

	DailyMenu save(DailyMenu dailyMenu);

	void deleteByID(Long id);

	DailyMenu getById(Long id);

	List<DailyMenu> getAllDto();

}
