package com.softserveinc.orphanagemenu.service;

/**
 * @author Vladimir Perepeliuk
 * @author Olexii Riabokon
 */
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.softserveinc.orphanagemenu.dto.DailyMenuDto;
import com.softserveinc.orphanagemenu.dto.NormstForAgeCategoryDto;
import com.softserveinc.orphanagemenu.dto.ProductWithLackAndNeededQuantityDto;
import com.softserveinc.orphanagemenu.model.ConsumptionType;
import com.softserveinc.orphanagemenu.model.DailyMenu;
import com.softserveinc.orphanagemenu.model.Product;

public interface DailyMenuService {

	DailyMenu save(DailyMenu dailyMenu);

	void deleteByID(Long id);

	DailyMenu getById(Long id);

	DailyMenuDto getDailyMenuDtoForDay(Date date);

	List<DailyMenuDto> getDailyMenuDtoForWeek(Date date);

	List<ConsumptionType> getAllConsumptionType();

	void updateDailyMenu(DailyMenu dailyMenu);

	Map<Product, List<NormstForAgeCategoryDto>> getProductsWithNorms(Long id);

	List<ProductWithLackAndNeededQuantityDto> getAllProductNeededQuantityAndLack(
			Long id);

	Date getDateById(Long id);

	Boolean getDailyMenuAccepted(Long id);

	Long create(Date date);

	Long createByTemplate(Long id, Date date);

	List<ProductWithLackAndNeededQuantityDto> getLackList(
			Long id);

	boolean exist(Date date);


}
