package com.softserveinc.orphanagemenu.service;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.NoResultException;
import javax.persistence.TransactionRequiredException;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.dozer.MappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserveinc.orphanagemenu.dao.ConsumptionTypeDao;
import com.softserveinc.orphanagemenu.dao.DailyMenuDao;
import com.softserveinc.orphanagemenu.dao.RoleDao;
import com.softserveinc.orphanagemenu.dao.UserAccountDao;
import com.softserveinc.orphanagemenu.dto.DailyMenuDto;
import com.softserveinc.orphanagemenu.dto.Deficit;
import com.softserveinc.orphanagemenu.dto.DishesForConsumption;
import com.softserveinc.orphanagemenu.dto.IncludingDeficitDish;
import com.softserveinc.orphanagemenu.exception.NotSuccessDBException;
import com.softserveinc.orphanagemenu.forms.UserAccountForm;
import com.softserveinc.orphanagemenu.model.ConsumptionType;
import com.softserveinc.orphanagemenu.model.DailyMenu;
import com.softserveinc.orphanagemenu.model.Dish;
import com.softserveinc.orphanagemenu.model.Role;
import com.softserveinc.orphanagemenu.model.Submenu;
import com.softserveinc.orphanagemenu.model.UserAccount;

@Service("dailyMenuService")
@Transactional
public class DailyMenuServiceImpl implements DailyMenuService {

	@Autowired
	@Qualifier("dailyMenuDao")
	private DailyMenuDao dailyMenuDao;
	
	@Autowired
	@Qualifier("consumptionTypeDao")
	private ConsumptionTypeDao consumptionTypeDao;
	
	@Override
	public DailyMenu save(DailyMenu dailyMenu) {
		dailyMenuDao.save(dailyMenu);
		return dailyMenu;
	}

	@Override
	public DailyMenu getById(Long id) {
		return dailyMenuDao.getById(id);
	}

	@Override
	public void deleteByID(Long id) {
		dailyMenuDao.delete(dailyMenuDao.getById(id));
	}

	@Override
	public List<DailyMenuDto> getWeeklyDto(){

		List<DailyMenu> dailyMenus = dailyMenuDao.getAll();
		List<DailyMenuDto> dailyMenuDtos = new ArrayList<>();
		for (DailyMenu dailyMenu : dailyMenus){
			DailyMenuDto dailyMenuDto = new DailyMenuDto();
			GregorianCalendar calendar = new GregorianCalendar();
			calendar.setGregorianChange(dailyMenu.getDate());
//			dailyMenuDto.setDate(
//				calendar.get(calendar.DAY_OF_MONTH) + "."
//			+	calendar.get(calendar.MONTH) + "."
//			+   calendar.get(calendar.YEAR));
			dailyMenuDto.setDate("1.03.06");
			dailyMenuDto.setDay("monday");
			dailyMenuDto.setIsAccepted(dailyMenu.isAccepted());
			List<DishesForConsumption> dishesForConsumptions = new ArrayList<>();
			List<ConsumptionType> consumptionTypes = consumptionTypeDao.getAll();
			for (ConsumptionType consumptionType : consumptionTypes){
				DishesForConsumption dishesForConsumption = new DishesForConsumption();
				dishesForConsumption.setConsumptionType(consumptionType);
				Set<IncludingDeficitDish> includingDeficitDishes = new HashSet<>();
				for (Submenu submenu : dailyMenu.getSubmenus()){
					if (submenu.getConsumptionType().equals(consumptionType)){
						for (Dish dish : submenu.getDishes()){
							IncludingDeficitDish includingDeficitDish = new IncludingDeficitDish();
							includingDeficitDish.setDish(dish);
							includingDeficitDish.setDeficits(getDeficits(dish,submenu));
							includingDeficitDishes.add(includingDeficitDish);
						}
						break;
					}
				}
				dishesForConsumption.setIncludingDeficitDishes(includingDeficitDishes);
			}
			dailyMenuDtos.add(dailyMenuDto);
		}
		
		
		
//		Mapper mapper = new DozerBeanMapper();
//		for (DailyMenu dailyMenu : dailyMenus){
//			dailyMenusDto.add(mapper.map(dailyMenu, DailyMenu.class));
//		}
		return null;
	}

	private List<Deficit> getDeficits(Dish dish, Submenu submenu) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ConsumptionType> getAllConsumptionType() {
		return consumptionTypeDao.getAll();
	}

}
