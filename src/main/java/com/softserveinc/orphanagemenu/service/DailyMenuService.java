package com.softserveinc.orphanagemenu.service;

import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;

import com.softserveinc.orphanagemenu.dto.DailyMenuDto;
import com.softserveinc.orphanagemenu.dto.ProductNorms;
import com.softserveinc.orphanagemenu.dto.ProductWithLackAndNeededQuantityDto;
import com.softserveinc.orphanagemenu.model.ConsumptionType;
import com.softserveinc.orphanagemenu.model.DailyMenu;

public interface DailyMenuService {

	DailyMenu save(DailyMenu dailyMenu);

	void deleteByID(Long id);

	DailyMenu getById(Long id);

	DailyMenuDto getDailyMenuDtoForDay(Date date);

	List<DailyMenuDto> getDailyMenuDtoForWeek(Date date);

	List<ConsumptionType> getAllConsumptionType();


	void updateDailyMenu(DailyMenu dailyMenu);

	 List<ProductNorms> getProductWithStandartAndFactQuantityList(
			Long id);
	 
	List<ProductWithLackAndNeededQuantityDto> getAllProductsWithQuantitiesForDailyMenu(Long id);
		
}
