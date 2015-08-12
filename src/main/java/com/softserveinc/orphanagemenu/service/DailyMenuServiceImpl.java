package com.softserveinc.orphanagemenu.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

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
	@Qualifier("productDao")
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

	public DailyMenuDto gDailyMenuDto(Date date){
		Map<Product, Double> productBalance = getProductBalanceByDate(date);
//		Map<Product, Double> productBalance = new HashMap<>();
		DailyMenu dailyMenu = dailyMenuDao.getByDate(date);
		System.out.println("-------------- 31");
		DailyMenuDto dailyMenuDto = new DailyMenuDto();
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setGregorianChange(dailyMenu.getDate());
//		calendar.set(calendar.get(Calendar.YEAR),
//				calendar.get(Calendar.MONTH),
//				calendar.get(Calendar.DAY_OF_MONTH),
//				0, 0, 0);
		
		dailyMenuDto.setDate("1.03.06");
		dailyMenuDto.setDay("monday");
		dailyMenuDto.setAccepted(dailyMenu.isAccepted());
		List<DishesForConsumption> dishesForConsumptions = new ArrayList<>();
		List<ConsumptionType> consumptionTypes = consumptionTypeDao.getAll();
		for (ConsumptionType consumptionType : consumptionTypes){
			System.out.println("--------------------------");
			DishesForConsumption dishesForConsumption = new DishesForConsumption();
			dishesForConsumption.setConsumptionType(consumptionType);
			Set<IncludingDeficitDish> includingDeficitDishes = new TreeSet<>();
			for (Submenu submenu : dailyMenu.getSubmenus()){
				if ((long)submenu.getConsumptionType().getId() == (long)consumptionType.getId()){
					for (Dish dish : submenu.getDishes()){
						IncludingDeficitDish includingDeficitDish = new IncludingDeficitDish();

						Mapper mapper = new DozerBeanMapper();
						Dish dishDto = mapper.map(dish, Dish.class);
						includingDeficitDish.setDish(dishDto);
						includingDeficitDish.setDeficits(getDeficits(dish,submenu,productBalance));
						if(includingDeficitDishes.contains(includingDeficitDish)){
							includingDeficitDishes.remove(includingDeficitDish);
							includingDeficitDishes.add(includingDeficitDish);
						} else {
							includingDeficitDishes.add(includingDeficitDish);
						}
						System.out.println(includingDeficitDish);
						System.out.println("------");
					}
				}
			}
			List<IncludingDeficitDish> includingDeficitDishesList = new ArrayList<>(includingDeficitDishes);
			dishesForConsumption.setIncludingDeficitDishes(includingDeficitDishesList);
			dishesForConsumptions.add(dishesForConsumption);
		}
		dailyMenuDto.setDishesForConsumptions(dishesForConsumptions);
		System.out.println(dailyMenuDto);
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
		for(Component component : dish.getComponents()){
			Deficit deficit = new Deficit();
			deficit.setProduct(component.getProduct());
			for (ComponentWeight componentWeight : component.getComponents()){
				if(submenu.getAgeCategory().equals(componentWeight.getAgeCategory())){
					Double currentQuantity = productBalance.get(component.getProduct());
					Double factProductQuantity = factProductQuantityDao
							.getBySubmenuAndComponentWeight(submenu, componentWeight)
							.getFactProductQuantity();
					Double resultProductQuantity = currentQuantity - factProductQuantity * submenu.getChildQuantity(); 
					Mapper mapper = new DozerBeanMapper();
					Product productDto = mapper.map(component.getProduct(), Product.class);
					productBalance.put(productDto, resultProductQuantity);
					deficit.setQuantity(resultProductQuantity);
					break;
				}
			}
			if (deficit.getQuantity() < 0){
				deficit.setQuantity(Math.abs(deficit.getQuantity()));
				deficits.add(deficit);
			}
		}
		return deficits;
	}
	
	private Map<Product, Double> getProductBalanceByDate(Date date){
		Map<Product, Double> productBalanceByDate = getCurrentProductBalance();
		List<DailyMenu> dailyMenus = dailyMenuDao.getFromCurrentDateToFutureDate(date);
		for (DailyMenu dailyMenu : dailyMenus){
			productBalanceByDate = getProductBalanceMinusDailyMenu(productBalanceByDate, dailyMenu);
		}
		return productBalanceByDate;
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
		
		return productBalance;
	}

	private Map<Product, Double> getCurrentProductBalance(){
		Map<Product, Double> currentProductBalance = new HashMap<>();
		List<Product> products = productDao.getAllProduct();
		for(Product product : products){
			WarehouseItem warehouseItem = warehouseService.getItem(product.getName());
			if (warehouseItem != null) {
				currentProductBalance.put(product, warehouseItem.getQuantity());
			} else {
				currentProductBalance.put(product, 0D);
			}
		}
		return currentProductBalance;
	}

}
