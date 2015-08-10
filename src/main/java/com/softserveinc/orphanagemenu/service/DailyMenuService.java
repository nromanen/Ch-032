package com.softserveinc.orphanagemenu.service;

import java.util.Date;
import java.util.List;

import com.softserveinc.orphanagemenu.dto.DailyMenuDto;
import com.softserveinc.orphanagemenu.model.ConsumptionType;
import com.softserveinc.orphanagemenu.model.DailyMenu;

public interface DailyMenuService {

	DailyMenu save(DailyMenu dailyMenu);

	void deleteByID(Long id);

	DailyMenu getById(Long id);

	List<DailyMenuDto> getWeeklyDto();
	
	DailyMenuDto getDailyMenuDto(Date date);
	
	List<ConsumptionType> getAllConsumptionType();
	

}
