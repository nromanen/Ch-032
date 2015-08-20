package com.softserveinc.orphanagemenu.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserveinc.orphanagemenu.dao.ConsumptionTypeDao;
import com.softserveinc.orphanagemenu.dao.DailyMenuDao;
import com.softserveinc.orphanagemenu.dao.FactProductQuantityDao;
import com.softserveinc.orphanagemenu.dao.ProductDao;
import com.softserveinc.orphanagemenu.dao.WarehouseItemDao;
import com.softserveinc.orphanagemenu.dto.AgeCategoryNorms;
import com.softserveinc.orphanagemenu.dto.DailyMenuDto;
import com.softserveinc.orphanagemenu.dto.Deficit;
import com.softserveinc.orphanagemenu.dto.DishesForConsumption;
import com.softserveinc.orphanagemenu.dto.IncludingDeficitDish;
import com.softserveinc.orphanagemenu.dto.NormAndFactList;
import com.softserveinc.orphanagemenu.dto.ProductNorms;
import com.softserveinc.orphanagemenu.model.Component;
import com.softserveinc.orphanagemenu.model.ComponentWeight;
import com.softserveinc.orphanagemenu.model.ConsumptionType;
import com.softserveinc.orphanagemenu.model.DailyMenu;
import com.softserveinc.orphanagemenu.model.Dish;
import com.softserveinc.orphanagemenu.model.Product;
import com.softserveinc.orphanagemenu.model.Submenu;
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

	@Override
	public List<ConsumptionType> getAllConsumptionType() {
		return consumptionTypeDao.getAll();
	}

	public DailyMenuDto getDailyMenuDtoForDay(Date actualDate) {

		DateTime currentDateTime = new DateTime();
		currentDateTime = new DateTime(currentDateTime.getYear(),
				currentDateTime.getMonthOfYear(),
				currentDateTime.getDayOfMonth(), 0, 0, 0);
		DailyMenuDto dailyMenuDto = new DailyMenuDto();

		DateTime actualDateTime = new DateTime(actualDate);
		actualDateTime = new DateTime(actualDateTime.getYear(),
				actualDateTime.getMonthOfYear(),
				actualDateTime.getDayOfMonth(), 0, 0, 0);

		DateTimeFormatter dateTimeFormatter = DateTimeFormat
				.forPattern("dd.MM.yy");
		dailyMenuDto.setDate(dateTimeFormatter.print(actualDateTime));
		dateTimeFormatter = DateTimeFormat.forPattern("EEEE").withLocale(
				new Locale("uk"));
		dailyMenuDto.setDay(dateTimeFormatter.print(actualDateTime));
		DailyMenu dailyMenu = dailyMenuDao.getByDate(actualDate);
		if (dailyMenu == null) {
			dailyMenuDto.setExist(false);
			return dailyMenuDto;
		} else {
			dailyMenuDto.setExist(true);
		}

		dailyMenuDto.setDailyMenuId(dailyMenu.getId().toString());
		dailyMenuDto.setAccepted(dailyMenu.isAccepted());

		Map<Product, Double> productBalance = getProductBalanceByDate(actualDate);
		List<DishesForConsumption> dishesForConsumptions = new ArrayList<>();
		List<ConsumptionType> consumptionTypes = consumptionTypeDao.getAll();

		for (ConsumptionType consumptionType : consumptionTypes) {
			DishesForConsumption dishesForConsumption = new DishesForConsumption();
			dishesForConsumption.setConsumptionType(consumptionType);
			Set<IncludingDeficitDish> includingDeficitDishes = new TreeSet<>();
			List<Dish> dishesForConsumptionType = new ArrayList<>();
			for (Submenu submenu : dailyMenu.getSubmenus()) {
				if ((long) submenu.getConsumptionType().getId() == (long) consumptionType
						.getId()) {
					dishesForConsumptionType = new ArrayList<>(
							submenu.getDishes());
					break;
				}
			}
			for (Dish dish : dishesForConsumptionType) {
				for (Submenu submenu : dailyMenu.getSubmenus()) {
					if ((long) submenu.getConsumptionType().getId() == (long) consumptionType
							.getId()) {

						IncludingDeficitDish includingDeficitDish = new IncludingDeficitDish();

						Mapper mapper = new DozerBeanMapper();
						Dish dishDto = mapper.map(dish, Dish.class);
						includingDeficitDish.setDish(dishDto);
						if (actualDateTime.isAfter(currentDateTime)
								|| actualDateTime.isEqual(currentDateTime)) {
							includingDeficitDish.setDeficits(getDeficits(dish,
									submenu, productBalance));
						}
						if (includingDeficitDishes
								.contains(includingDeficitDish)) {
							includingDeficitDishes.remove(includingDeficitDish);
							includingDeficitDishes.add(includingDeficitDish);
						} else {
							includingDeficitDishes.add(includingDeficitDish);
						}
					}
				}
			}
			List<IncludingDeficitDish> includingDeficitDishesList = new ArrayList<>(
					includingDeficitDishes);
			dishesForConsumption
					.setIncludingDeficitDishes(includingDeficitDishesList);
			dishesForConsumptions.add(dishesForConsumption);
		}
		dailyMenuDto.setDishesForConsumptions(dishesForConsumptions);
		return dailyMenuDto;
	}

	@Override
	public List<DailyMenuDto> getDailyMenuDtoForWeek(Date date) {
		DateTime monday = new DateTime(date).withDayOfWeek(1);
		List<DailyMenuDto> dailyMenuDtos = new ArrayList<>();
		for (int i = 0; i < 7; i++) {
			dailyMenuDtos
					.add(getDailyMenuDtoForDay(monday.plusDays(i).toDate()));
		}
		return dailyMenuDtos;
	}

	private Map<Product, Double> getCurrentProductBalance() {
		Map<Product, Double> currentProductBalance = new HashMap<>();
		List<Product> products = productDao.getAllProduct();
		for (Product product : products) {
			WarehouseItem warehouseItem = warehouseService.getItem(product
					.getName());
			if (warehouseItem != null) {
				currentProductBalance.put(product, warehouseItem.getQuantity());
			} else {
				currentProductBalance.put(product, 0D);
			}
		}
		return currentProductBalance;
	}

	private Map<Product, Double> getProductBalanceByDate(Date date) {
		Map<Product, Double> productBalanceByDate = getCurrentProductBalance();
		List<DailyMenu> dailyMenus = dailyMenuDao
				.getFromCurrentDateToFutureDate(date);
		for (DailyMenu dailyMenu : dailyMenus) {
			productBalanceByDate = getProductBalanceMinusDailyMenu(
					productBalanceByDate, dailyMenu);
		}
		return productBalanceByDate;
	}

	private Map<Product, Double> getProductBalanceMinusDailyMenu(
			Map<Product, Double> productBalance, DailyMenu dailyMenu) {
		for (Submenu submenu : dailyMenu.getSubmenus()) {
			for (Dish dish : submenu.getDishes()) {
				for (Component component : dish.getComponents()) {
					for (ComponentWeight componentWeight : component
							.getComponents()) {
						if (submenu.getAgeCategory().equals(
								componentWeight.getAgeCategory())) {
							Double currentQuantity = productBalance
									.get(component.getProduct());
							Double factProductQuantity = factProductQuantityDao
									.getBySubmenuAndComponentWeight(submenu,
											componentWeight)
									.getFactProductQuantity();
							Double resultProductQuantity = currentQuantity
									- factProductQuantity
									* submenu.getChildQuantity();
							productBalance.put(component.getProduct(),
									resultProductQuantity);
							break;
						}
					}
				}
			}
		}

		return productBalance;
	}

	private List<Deficit> getDeficits(Dish dish, Submenu submenu,
			Map<Product, Double> productBalance) {
		List<Deficit> deficits = new ArrayList<>();
		for (Component component : dish.getComponents()) {
			Deficit deficit = new Deficit();
			deficit.setProduct(component.getProduct());
			for (ComponentWeight componentWeight : component.getComponents()) {
				if (submenu.getAgeCategory().equals(
						componentWeight.getAgeCategory())) {
					Double currentQuantity = productBalance.get(component
							.getProduct());
					Double factProductQuantity = factProductQuantityDao
							.getBySubmenuAndComponentWeight(submenu,
									componentWeight).getFactProductQuantity();
					Double resultProductQuantity = currentQuantity
							- factProductQuantity * submenu.getChildQuantity();
					Mapper mapper = new DozerBeanMapper();
					Product productDto = mapper.map(component.getProduct(),
							Product.class);
					productBalance.put(productDto, resultProductQuantity);
					deficit.setQuantity(resultProductQuantity);
					break;
				}
			}
			if (deficit.getQuantity() < 0) {
				deficit.setQuantity(Math.abs(deficit.getQuantity()));
				deficits.add(deficit);
			}
		}
		return deficits;
	}

	public List<ProductNorms> getProductWithStandartAndFactQuantityList(
			Long id) {
		
		NormAndFactList producsNormCompliance = new NormAndFactList();

		for (Component component : dailyMenuDao.getAllComponents(id)) {

			for (ComponentWeight componentWeight : component.getComponents()) {

				AgeCategoryNorms normAndFact = new AgeCategoryNorms();
				normAndFact.setAgeCategory(componentWeight
						.getAgeCategory());
				
				normAndFact.setStandartProductQuantityFromComponent(component);
				
				normAndFact.setFactProductQuantity(componentWeight
						.getStandartWeight());

				ProductNorms productNormCompliance = new ProductNorms();
				productNormCompliance.setProductName(component.getProduct()
						.getName());

				productNormCompliance
						.addCategoryWithNormsAndFact(normAndFact);
				producsNormCompliance.add(productNormCompliance);


			}

		}
	
		return producsNormCompliance.getProductsNormsAndFacts();
	}
}
