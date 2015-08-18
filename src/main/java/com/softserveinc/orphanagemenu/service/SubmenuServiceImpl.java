package com.softserveinc.orphanagemenu.service;

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
import com.softserveinc.orphanagemenu.model.Product;
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
		DailyMenu dailyMenu = dailyMenuDao.getById(Long.parseLong(dailyMenuId));
		factProductsQuantityForm.setAgeCategory(ageCategoryDao
				.getAllAgeCategory());
		for (AgeCategory ageCategory : factProductsQuantityForm
				.getAgeCategory()) {
			for (Submenu submenu : dailyMenu.getSubmenus()) {
				if (submenu.getConsumptionType().getId()
						.equals(Long.parseLong(consumptionTypeId))
						&& submenu.getAgeCategory().equals(ageCategory)) {
					for (Dish dish : submenu.getDishes()) {
						if (dish.getId().equals(Long.parseLong(dishId))) {
							if (factProductsQuantityForm.getDishName() == null) {
								factProductsQuantityForm.setDishName(dish
										.getName());
								List<Product> products = new ArrayList<>();
								for (Component component : dish.getComponents()) {
									products.add(component.getProduct());
								}
								factProductsQuantityForm.setProducts(products);
							}
							Map<Long, Double> idQiantity = new TreeMap<>();
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
										idQiantity.put(fact.getId(),
												fact.getFactProductQuantity());
									}
								}
							}
							if (ageCategory.equals(factProductsQuantityForm
									.getAgeCategory().get(0))) {
								factProductsQuantityForm
										.setFactProductQuantityFirstAgeCategory(idQiantity);
							} else if (ageCategory
									.equals(factProductsQuantityForm
											.getAgeCategory().get(1))) {
								factProductsQuantityForm
										.setFactProductQuantitySecondAgeCategory(idQiantity);
							} else if (ageCategory
									.equals(factProductsQuantityForm
											.getAgeCategory().get(2))) {
								factProductsQuantityForm
										.setFactProductQuantityThirdAgeCategory(idQiantity);
							} else if (ageCategory
									.equals(factProductsQuantityForm
											.getAgeCategory().get(3))) {
								factProductsQuantityForm
										.setFactProductQuantityFourthAgeCategory(idQiantity);
							}
						}
					}
				}
			}
		}
		return factProductsQuantityForm;
	}

	@Override
	public FactProductsQuantityForm getStandartComponentQuantityForm(
			FactProductsQuantityForm factProductsQuantityForm) {
		// TODO Auto-generated method stub
		return factProductsQuantityForm;
	}

}