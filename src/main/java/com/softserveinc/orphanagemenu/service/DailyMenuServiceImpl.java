package com.softserveinc.orphanagemenu.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
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
import com.softserveinc.orphanagemenu.dao.FactProductQuantityDao;
import com.softserveinc.orphanagemenu.dao.ProductDao;
import com.softserveinc.orphanagemenu.dao.RoleDao;
import com.softserveinc.orphanagemenu.dao.UserAccountDao;
import com.softserveinc.orphanagemenu.dao.WarehouseItemDao;
import com.softserveinc.orphanagemenu.dto.DailyMenuDto;
import com.softserveinc.orphanagemenu.dto.Deficit;
import com.softserveinc.orphanagemenu.dto.DishesForConsumption;
import com.softserveinc.orphanagemenu.dto.IncludingDeficitDish;
import com.softserveinc.orphanagemenu.exception.NotSuccessDBException;
import com.softserveinc.orphanagemenu.forms.UserAccountForm;
import com.softserveinc.orphanagemenu.model.Component;
import com.softserveinc.orphanagemenu.model.ComponentWeight;
import com.softserveinc.orphanagemenu.model.ConsumptionType;
import com.softserveinc.orphanagemenu.model.DailyMenu;
import com.softserveinc.orphanagemenu.model.Dish;
import com.softserveinc.orphanagemenu.model.Product;
import com.softserveinc.orphanagemenu.model.Role;
import com.softserveinc.orphanagemenu.model.Submenu;
import com.softserveinc.orphanagemenu.model.UserAccount;
import com.softserveinc.orphanagemenu.model.WarehouseItem;

@Service("dailyMenuService")
@Transactional
public class DailyMenuServiceImpl implements DailyMenuService {

	@Autowired
	@Qualifier("dailyMenuDao")
	private DailyMenuDao dailyMenuDao;
	
	@Autowired
	@Qualifier("consumptionTypeDao")
	private ConsumptionTypeDao consumptionTypeDao;
	
	@Autowired
	@Qualifier("productDaoImpl")
	private ProductDao productDao;
	
	@Autowired
	@Qualifier("WarehouseItemDao")
	private WarehouseItemDao warehouseItemDao;
	
	@Autowired
	private WarehouseService warehouseService;
	
	
	@Autowired
	@Qualifier("factProductQuantityDao")
	private FactProductQuantityDao factProductQuantityDao;
	
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

	public DailyMenuDto getDailyMenuDto(Date date){
		Map<Product, Double> productBalance = getProductBalanceByDate(date);
		DailyMenu dailyMenu = dailyMenuDao.getByDate(date);
		DailyMenuDto dailyMenuDto = new DailyMenuDto();
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setGregorianChange(dailyMenu.getDate());
//			dailyMenuDto.setDate(
//				calendar.get(calendar.DAY_OF_MONTH) + "."
//			+	calendar.get(calendar.MONTH) + "."
//			+   calendar.get(calendar.YEAR));
		dailyMenuDto.setDate("1.03.06");
		dailyMenuDto.setDay("monday");
		dailyMenuDto.setAccepted(dailyMenu.isAccepted());
		List<DishesForConsumption> dishesForConsumptions = new ArrayList<>();
		List<ConsumptionType> consumptionTypes = consumptionTypeDao.getAll();
		for (ConsumptionType consumptionType : consumptionTypes){
			DishesForConsumption dishesForConsumption = new DishesForConsumption();
			dishesForConsumption.setConsumptionType(consumptionType);
			List<IncludingDeficitDish> includingDeficitDishes = new ArrayList<>();
			for (Submenu submenu : dailyMenu.getSubmenus()){
				if (submenu.getConsumptionType().equals(consumptionType)){
					for (Dish dish : submenu.getDishes()){
						IncludingDeficitDish includingDeficitDish = new IncludingDeficitDish();
						includingDeficitDish.setDish(dish);
						includingDeficitDish.setDeficits(getDeficits(dish,submenu,productBalance));
						includingDeficitDishes.add(includingDeficitDish);
					}
					break;
				}
			}
			dishesForConsumption.setIncludingDeficitDishes(includingDeficitDishes);
			dishesForConsumptions.add(dishesForConsumption);
		}
		dailyMenuDto.setDishesForConsumptions(dishesForConsumptions);
		return dailyMenuDto;
	}
	
	
	@Override
	public List<DailyMenuDto> getWeeklyDto(){

		List<DailyMenuDto> dailyMenuDtos = new ArrayList<>();
		return dailyMenuDtos;
	}

	@Override
	public List<ConsumptionType> getAllConsumptionType() {
		return consumptionTypeDao.getAll();
	}
	
	private List<Deficit> getDeficits(Dish dish, Submenu submenu, Map<Product, Double> productBalance) {
		List<Deficit> deficits = new ArrayList<>();
		return null;
	}
	
	private Map<Product, Double> getProductBalanceByDate(Date date){
		Map<Product, Double> productBalanceByDate = getCurrentProductBalance();
		List<DailyMenu> dailyMenus = dailyMenuDao.getFromCurrentDateToFutureDate(date);
		for (DailyMenu dailyMenu : dailyMenus){
			productBalanceByDate = getProductBalanceMinusDailyMenu(productBalanceByDate, dailyMenu);
		}
		return null;
	}
	
	private Map<Product, Double> getProductBalanceMinusDailyMenu(
				Map<Product, Double> productBalance,
				DailyMenu dailyMenu) {
		for (Submenu submenu : dailyMenu.getSubmenus()){
			for (Dish dish : submenu.getDishes()){
				for (Component component : dish.getComponents()){
					for (ComponentWeight componentWeight : component.getComponents()){
						if(submenu.getAgeCategory().equals(componentWeight.getAgeCategory())){
							Double currentQuantity = productBalance.get(component.getProduct());
							Double factProductQuantity = factProductQuantityDao
									.getBySubmenuAndComponentWeight(submenu, componentWeight)
									.getFactProductQuantity();
							Double resultProductQuantity = currentQuantity - factProductQuantity * submenu.getChildQuantity(); 
							productBalance.put(component.getProduct(), resultProductQuantity);
							break;
						}
					}
				}
			}
		}
		
		return null;
	}

	private Map<Product, Double> getCurrentProductBalance(){
		Map<Product, Double> currentProductBalance = new HashMap<>();
		List<Product> products = productDao.getAllProduct();
		for(Product product : products){
			WarehouseItem warehouseItem = warehouseService.getItem(product.getName());
			if(warehouseItem != null){
				currentProductBalance.put(product, warehouseItem.getQuantity());
			} else {
				currentProductBalance.put(product, 0D);
			}
		}
		return currentProductBalance;
	}

}
