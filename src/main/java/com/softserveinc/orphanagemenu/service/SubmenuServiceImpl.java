package com.softserveinc.orphanagemenu.service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserveinc.orphanagemenu.dao.AgeCategoryDao;
import com.softserveinc.orphanagemenu.dao.DailyMenuDao;
import com.softserveinc.orphanagemenu.dao.DishDao;
import com.softserveinc.orphanagemenu.dao.FactProductQuantityDao;
import com.softserveinc.orphanagemenu.dao.SubmenuDao;
import com.softserveinc.orphanagemenu.forms.FactProductsQuantityForm;
import com.softserveinc.orphanagemenu.model.AgeCategory;
import com.softserveinc.orphanagemenu.model.Component;
import com.softserveinc.orphanagemenu.model.ComponentWeight;
import com.softserveinc.orphanagemenu.model.DailyMenu;
import com.softserveinc.orphanagemenu.model.Dish;
import com.softserveinc.orphanagemenu.model.FactProductQuantity;
import com.softserveinc.orphanagemenu.model.Submenu;

@Service
@Transactional
public class SubmenuServiceImpl implements SubmenuService {

	@Autowired
	private DailyMenuDao dailyMenuDao;

	@Autowired
	private DishDao dishDao;

	@Autowired
	private AgeCategoryDao ageCategoryDao;

	@Autowired
	private FactProductQuantityDao factProductQuantityDao;

	@Autowired
	private SubmenuDao submenuDao;

	@Override
	public List<Submenu> getSubmenuListByDailyMenuAndConsumptionTypeId(
			Long dailyMenuId, Long consumptionTypeId) {
		return this.submenuDao.getSubmenuListByDailyMenuAndConsumptionTypeId(
				dailyMenuId, consumptionTypeId);
	}

	@Override
	public FactProductsQuantityForm getFactProductsQuantityForm(
			String dailyMenuId, String dishId, String consumptionTypeId) {
		FactProductsQuantityForm factProductsQuantityForm = new FactProductsQuantityForm();
		factProductsQuantityForm.setDailyMenuId(dailyMenuId);
		List<Submenu> submenus = submenuDao
				.getSubmenuListByDailyMenuAndConsumptionTypeId(
						Long.parseLong(dailyMenuId),
						Long.parseLong(consumptionTypeId));
		List<AgeCategory> ageCategory1 = ageCategoryDao.getAllAgeCategory();
		List<String> ageCategoryNames = new ArrayList<>();
		for (AgeCategory ageCategory : ageCategory1) {
			ageCategoryNames.add(ageCategory.getName());
			for (Submenu submenu : submenus) {
				if (submenu.getAgeCategory().equals(ageCategory)) {
					for (Dish dish : submenu.getDishes()) {
						if (dish.getId().equals(Long.parseLong(dishId))) {
							if (factProductsQuantityForm.getDishName() == null) {
								factProductsQuantityForm.setDishName(dish
										.getName());
								List<String> productNames = new ArrayList<>();
								for (Component component : dish.getComponents()) {
									productNames.add(component.getProduct()
											.getName());
								}
								factProductsQuantityForm
										.setProductNames(productNames);
							}
							Map<Long, String> idQiantity = new TreeMap<>();
							for (Component component : dish.getComponents()) {
								for (ComponentWeight componentWeight : component
										.getComponents()) {
									if (ageCategory.getId().equals(
											componentWeight.getAgeCategory()
													.getId())) {
										FactProductQuantity fact = factProductQuantityDao
												.getFactProductQuantity(
														submenu,
														componentWeight);
										idQiantity.put(fact.getId(), fact
												.getFactProductQuantity()
												.toString().replace(".", ","));
									}
								}
							}
							if (ageCategory.equals(ageCategory1.get(0))) {
								factProductsQuantityForm
										.setFactProductQuantityFirstAgeCategory(idQiantity);
							} else if (ageCategory.equals(ageCategory1.get(1))) {
								factProductsQuantityForm
										.setFactProductQuantitySecondAgeCategory(idQiantity);
							} else if (ageCategory.equals(ageCategory1.get(2))) {
								factProductsQuantityForm
										.setFactProductQuantityThirdAgeCategory(idQiantity);
							} else if (ageCategory.equals(ageCategory1.get(3))) {
								factProductsQuantityForm
										.setFactProductQuantityFourthAgeCategory(idQiantity);
							}
						}
					}
				}
			}
		}
		factProductsQuantityForm.setAgeCategoryNames(ageCategoryNames);
		return factProductsQuantityForm;
	}

	@Override
	public FactProductsQuantityForm getStandartComponentQuantityForm(
			FactProductsQuantityForm factProductsQuantityForm) {
		List<AgeCategory> ageCategories = ageCategoryDao.getAllAgeCategory();
		DailyMenu dailyMenu = dailyMenuDao.getById(Long
				.parseLong(factProductsQuantityForm.getDailyMenuId()));
		for (Submenu submenu : dailyMenu.getSubmenus()) {
			for (Dish dish : submenu.getDishes()) {
				for (Component component : dish.getComponents()) {
					for (ComponentWeight componentWeight : component
							.getComponents()) {
						if (componentWeight.getAgeCategory().equals(
								ageCategories.get(0))) {
							for (Map.Entry<Long, String> quantityFirstCat : factProductsQuantityForm
									.getFactProductQuantityFirstAgeCategory()
									.entrySet()) {
								if (componentWeight.getId().equals(
										quantityFirstCat.getKey())) {
									quantityFirstCat.setValue(componentWeight
											.getStandartWeight().toString()
											.replace(".", ","));
								}
							}
						} else if (componentWeight.getAgeCategory().equals(
								ageCategories.get(1))) {
							for (Map.Entry<Long, String> quantitySecCat : factProductsQuantityForm
									.getFactProductQuantitySecondAgeCategory()
									.entrySet()) {
								if (componentWeight.getId().equals(
										quantitySecCat.getKey())) {
									quantitySecCat.setValue(componentWeight
											.getStandartWeight().toString()
											.replace(".", ","));
								}
							}
						} else if (componentWeight.getAgeCategory().equals(
								ageCategories.get(2))) {
							for (Map.Entry<Long, String> quantityThirdCat : factProductsQuantityForm
									.getFactProductQuantityThirdAgeCategory()
									.entrySet()) {
								if (componentWeight.getId().equals(
										quantityThirdCat.getKey())) {
									quantityThirdCat.setValue(componentWeight
											.getStandartWeight().toString()
											.replace(".", ","));
								}
							}
						} else if (componentWeight.getAgeCategory().equals(
								ageCategories.get(3))) {
							for (Map.Entry<Long, String> quantityFourthCat : factProductsQuantityForm
									.getFactProductQuantityFourthAgeCategory()
									.entrySet()) {
								if (componentWeight.getId().equals(
										quantityFourthCat.getKey())) {
									quantityFourthCat.setValue(componentWeight
											.getStandartWeight().toString()
											.replace(".", ","));
								}
							}
						}
					}
				}
			}
		}
		return factProductsQuantityForm;
	}

	@Override
	public void saveFactProductQuantity(
			FactProductsQuantityForm factProductsQuantityForm) {

		DailyMenu dailyMenu = dailyMenuDao.getById(Long
				.parseLong(factProductsQuantityForm.getDailyMenuId()));
		Map<Long, String> allFactProductQuantity = new TreeMap<>();
		List<Map<Long, String>> mapList = new ArrayList<>();
		mapList.add(factProductsQuantityForm
				.getFactProductQuantityFirstAgeCategory());
		mapList.add(factProductsQuantityForm
				.getFactProductQuantitySecondAgeCategory());
		mapList.add(factProductsQuantityForm
				.getFactProductQuantityThirdAgeCategory());
		mapList.add(factProductsQuantityForm
				.getFactProductQuantityFourthAgeCategory());
		for (Map<Long, String> map : mapList) {
			for (Map.Entry<Long, String> map1 : map.entrySet()) {
				allFactProductQuantity.put(map1.getKey(), map1.getValue());
			}
		}
		for (Submenu submenu : dailyMenu.getSubmenus()) {
			for (FactProductQuantity factProductQuantity : submenu
					.getFactProductQuantities()) {
				for (Map.Entry<Long, String> quantityMap : allFactProductQuantity
						.entrySet()) {
					if (factProductQuantity.getId()
							.equals(quantityMap.getKey())) {
						quantityMap.setValue(quantityMap.getValue().replace(
								",", "."));
						quantityMap.setValue(Double.toString(Double
								.valueOf(new DecimalFormat("#.##")
										.format(Double.parseDouble(quantityMap
												.getValue())))));
						factProductQuantity.setFactProductQuantity(Double
								.parseDouble(quantityMap.getValue()));
					}
				}
			}
		}
		dailyMenuDao.updateDailyMenu(dailyMenu);
	}

	@Override
	public Submenu getByID(Long id) {
		return this.submenuDao.getByID(id);
	}
	
	
	@Override
	public List<Dish> getAllDishes(Submenu submenu){
		return this.submenuDao.getAllDishes(submenu);
	}

}
