package com.softserveinc.orphanagemenu.service;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
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

import com.softserveinc.orphanagemenu.dao.AgeCategoryDao;
import com.softserveinc.orphanagemenu.dao.ConsumptionTypeDao;
import com.softserveinc.orphanagemenu.dao.DailyMenuDao;
import com.softserveinc.orphanagemenu.dao.FactProductQuantityDao;
import com.softserveinc.orphanagemenu.dao.ProductDao;
import com.softserveinc.orphanagemenu.dao.SubmenuDao;
import com.softserveinc.orphanagemenu.dto.DailyMenuDto;
import com.softserveinc.orphanagemenu.dto.Deficit;
import com.softserveinc.orphanagemenu.dto.DishesForConsumption;
import com.softserveinc.orphanagemenu.dto.IncludingDeficitDish;
import com.softserveinc.orphanagemenu.dto.NormstForAgeCategoryDto;
import com.softserveinc.orphanagemenu.dto.ProductWithLackAndNeededQuantityDto;
import com.softserveinc.orphanagemenu.model.AgeCategory;
import com.softserveinc.orphanagemenu.model.Component;
import com.softserveinc.orphanagemenu.model.ComponentWeight;
import com.softserveinc.orphanagemenu.model.ConsumptionType;
import com.softserveinc.orphanagemenu.model.DailyMenu;
import com.softserveinc.orphanagemenu.model.Dish;
import com.softserveinc.orphanagemenu.model.FactProductQuantity;
import com.softserveinc.orphanagemenu.model.Product;
import com.softserveinc.orphanagemenu.model.Submenu;
import com.softserveinc.orphanagemenu.model.WarehouseItem;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * @author Vladimir Perepeliuk
 * @author Olexii Riabokon
 */
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
	@Qualifier("ageCategoryDao")
	private AgeCategoryDao ageCategoryDao;

	@Autowired
	@Qualifier("productDao")
	private ProductDao productDao;

	@Autowired
	private SubmenuDao subMenuDao;

	@Autowired
	private WarehouseService warehouseService;

	@Autowired
	@Qualifier("factProductQuantityDao")
	private FactProductQuantityDao factProductQuantityDao;

	@Autowired
	private DailyProductNormsService statisticHelperService;

	@Autowired
	private AgeCategoryService ageCategoryService;

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
	public void updateDailyMenu(DailyMenu dailyMenu) {
		this.dailyMenuDao.updateDailyMenu(dailyMenu);
	}

	@Override
	public List<ConsumptionType> getAllConsumptionType() {
		return consumptionTypeDao.getAll();
	}

	@Override
	public Long create(Date date) {

		if (dailyMenuDao.getByDate(date) == null) {
			DailyMenu dailyMenu = new DailyMenu();
			dailyMenu.setDate(date);
			dailyMenu.setAccepted(false);
			dailyMenuDao.save(dailyMenu);
			dailyMenu = dailyMenuDao.getByDate(date);
			Set<Submenu> submenuSet = new LinkedHashSet<Submenu>();
			for (ConsumptionType ct : consumptionTypeDao.getAll()) {
				for (AgeCategory ac : ageCategoryDao.getAllAgeCategory()) {
					Submenu submenu = new Submenu();
					submenu.setChildQuantity(0);
					submenu.setDailyMenu(dailyMenu);
					submenu.setAgeCategory(ac);
					submenu.setConsumptionType(ct);
					Set<Dish> dishSetTemp = new LinkedHashSet<Dish>();
					submenu.setDishes(dishSetTemp);
					Set<FactProductQuantity> factProductQuantities = new HashSet<>();
					submenu.setFactProductQuantities(factProductQuantities);
					submenuSet.add(submenu);
				}
			}
			dailyMenu.setSubmenus(submenuSet);
			dailyMenuDao.updateDailyMenu(dailyMenu);
		}
		return dailyMenuDao.getByDate(date).getId();
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
				.forPattern("dd.MM.yyyy");
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
			Map<AgeCategory, Integer> childs = new TreeMap<AgeCategory, Integer>();
			for (Submenu submenu : dailyMenu.getSubmenus()) {
				if ((long) submenu.getConsumptionType().getId() == (long) consumptionType
						.getId()) {
					childs.put(submenu.getAgeCategory(),
							submenu.getChildQuantity());
				}
			}

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
			dishesForConsumption.setChildQuantity(childs);
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

	public Map<Product, List<NormstForAgeCategoryDto>> getProductsWithNorms(
			Long id) {
		return statisticHelperService.parseComponents(subMenuDao
				.getAllByDailyMenuId(id));

	}

	@Override
	public Date getDateById(Long id) {
		return this.dailyMenuDao.getDateById(id);
	}

	private List<ProductWithLackAndNeededQuantityDto> getAllProductsWithQuantitiesForDailyMenuAndAgeCategory(
			Long dailyMenuId, AgeCategory neededCategory) {

		List<ProductWithLackAndNeededQuantityDto> productWithLackAndNeededQuantityDtoList = new ArrayList<ProductWithLackAndNeededQuantityDto>();

		for (Submenu subMenu : getById(dailyMenuId).getSubmenus()) {

			if (neededCategory.equals(subMenu.getAgeCategory())) {

				for (Dish dish : subMenu.getDishes()) {
					for (Component component : dish.getComponents()) {

						for (ComponentWeight componentWeight : component
								.getComponents()) {

							if (neededCategory.equals(componentWeight
									.getAgeCategory())) {
								// refactor with age categories
								if (productWithLackAndNeededQuantityDtoList
										.size() == 0) {
									addNewProductWithLackDto(

									productWithLackAndNeededQuantityDtoList,
											componentWeight);
								}
								if (!checkExistingInCollectionForProductWithLackDto(
										productWithLackAndNeededQuantityDtoList,
										componentWeight)) {
									addNewProductWithLackDto(
											productWithLackAndNeededQuantityDtoList,
											componentWeight);
								} else {
									productWithLackAndNeededQuantityDtoList
											.get(getIndexForIncreasingQuantityForProductWithLackDto(
													productWithLackAndNeededQuantityDtoList,
													componentWeight))
											.increaseNeededQuantity(
													componentWeight
															.getStandartWeight());
								}
							}
						}
					}
				}
			}
		}
		return productWithLackAndNeededQuantityDtoList;
	}

	private void addNewProductWithLackDto(
			List<ProductWithLackAndNeededQuantityDto> dtoListObject,
			ComponentWeight componentWeight) {
		ProductWithLackAndNeededQuantityDto newDtoObject = new ProductWithLackAndNeededQuantityDto();
		newDtoObject.setProduct(componentWeight.getComponent().getProduct());
		newDtoObject.setNeededQuantity(componentWeight.getStandartWeight());
		dtoListObject.add(newDtoObject);
	}

	private boolean checkExistingInCollectionForProductWithLackDto(
			List<ProductWithLackAndNeededQuantityDto> someList,
			ComponentWeight someWeight) {
		boolean result = false;
		for (int i = 0; i < someList.size(); i++) {
			if (someList.get(i).getProduct().getName() == someWeight
					.getComponent().getProduct().getName()) {
				result = true;
			}
		}
		return result;
	}

	private int getIndexForIncreasingQuantityForProductWithLackDto(
			List<ProductWithLackAndNeededQuantityDto> someList,
			ComponentWeight someWeight) {
		for (int i = 0; i < someList.size(); i++) {
			if (someList.get(i).getProduct().getName() == someWeight
					.getComponent().getProduct().getName()) {
				return i;
			}
		}
		return -1;
	}

	public List<ProductWithLackAndNeededQuantityDto> calculateWithClidQuantity(
			List<ProductWithLackAndNeededQuantityDto> dtoListObject,
			int childQuantity) {
		for (ProductWithLackAndNeededQuantityDto currentDto : dtoListObject) {
			currentDto.calculateWithChildQuantity(childQuantity);
		}
		return dtoListObject;
	}

	@Override
	public List<ProductWithLackAndNeededQuantityDto> getAllProductNeededQuantityAndLack(
			Long menuId) {

		List<AgeCategory> allAgeCategories = ageCategoryService
				.getAllAgeCategory();
		List<ProductWithLackAndNeededQuantityDto> resultList = new ArrayList<ProductWithLackAndNeededQuantityDto>();

		for (AgeCategory currentCategory : allAgeCategories) {

			if (resultList.size() == 0) {
				resultList = calculateWithClidQuantity(
						getAllProductsWithQuantitiesForDailyMenuAndAgeCategory(
								menuId, currentCategory),
						getChildQuantityByAgeCategory(menuId, currentCategory));
			} else {
				for (int i = 0; i < resultList.size(); i++) {
					resultList
							.get(i)
							.increaseNeededQuantity(
									calculateWithClidQuantity(
											getAllProductsWithQuantitiesForDailyMenuAndAgeCategory(
													menuId, currentCategory),
											getChildQuantityByAgeCategory(
													menuId, currentCategory))
											.get(i).getNeededQuantity());
				}
			}
		}

		Map<Product, Double> currentProductBalance = getCurrentProductBalance();
		for (ProductWithLackAndNeededQuantityDto dto : resultList) {
			dto.setQuantityAvailable(currentProductBalance.get(dto.getProduct()));
			dto.calculateLack();
		}
		return resultList;
	}

	private int getChildQuantityByAgeCategory(Long dailyMenuId,
			AgeCategory neededCategory) {
		for (Submenu subMenu : getById(dailyMenuId).getSubmenus()) {

			if (neededCategory.equals(subMenu.getAgeCategory())) {
				return subMenu.getChildQuantity();
			}
		}
		return -1;
	}

	@Override
	public Boolean getDailyMenuAccepted(Long id) {
		return dailyMenuDao.getDailyMenuAccepted(id);
	}

	@Override
	public Long createByTemplate(Long id, Date date) {

		return dailyMenuDao.createByTemplate(id, date);
	}

	@Override
	public List<ProductWithLackAndNeededQuantityDto> getLackList(
			Long id) {
		List <ProductWithLackAndNeededQuantityDto> target = getAllProductNeededQuantityAndLack (id);
		List<ProductWithLackAndNeededQuantityDto> resultList = new ArrayList ();
		for (ProductWithLackAndNeededQuantityDto object : target)
		{
			if (object.getLack()!=0)
			{
				resultList.add(object);
			}
		}
		return resultList;
	}

	@Override
	public boolean exist(Date date) {
		if (dailyMenuDao.getByDate(date) != null) {
			return true;
		}
		return false;
	}

}
