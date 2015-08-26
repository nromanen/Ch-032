package com.softserveinc.orphanagemenu.service;

import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;

import com.softserveinc.orphanagemenu.dto.DailyMenuDto;
import com.softserveinc.orphanagemenu.dto.ProductNormsAndFact;
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

	 List<ProductNormsAndFact> getProductWithStandartAndFactQuantityList(
			Long id);

	 Date getDateById(Long id);
	 
	List<ProductWithLackAndNeededQuantityDto> getAllProductsWithQuantitiesForDailyMenu(Long id);

	public Boolean getDailyMenuAccepted(Long id);


}
