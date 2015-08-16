package com.softserveinc.orphanagemenu.service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import com.softserveinc.orphanagemenu.dto.DailyMenuDto;
import com.softserveinc.orphanagemenu.forms.FactProductsQuantityForm;
import com.softserveinc.orphanagemenu.model.AgeCategory;
import com.softserveinc.orphanagemenu.model.ConsumptionType;
import com.softserveinc.orphanagemenu.model.DailyMenu;

public interface DailyMenuService {

	DailyMenu save(DailyMenu dailyMenu);

	void deleteByID(Long id);

	DailyMenu getById(Long id);
	
	DailyMenuDto getDailyMenuDtoForDay(Date date);
	
	List<DailyMenuDto> getDailyMenuDtoForWeek(Date date);
	
	List<ConsumptionType> getAllConsumptionType();
	
}
