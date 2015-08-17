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
		Dish dish = dishDao.getDishById(Long.parseLong(dishId));
		factProductsQuantityForm.setDishName(dish.getName());
		List<Product> products = new ArrayList<>();
		for (Component component : dish.getComponents()) {
			products.add(component.getProduct());
		}
		factProductsQuantityForm.setProducts(products);
		factProductsQuantityForm.setAgeCategory(ageCategoryDao
				.getAllAgeCategory());
		for (AgeCategory ageCategory1 : factProductsQuantityForm
				.getAgeCategory()) {
			for (Submenu submenu : dailyMenu.getSubmenus()) {
				if (submenu.getConsumptionType().getId()
						.equals(Long.parseLong(consumptionTypeId))
						&& submenu.getAgeCategory().equals(ageCategory1)) {
					for (Dish dish1 : submenu.getDishes()) {
						if (dish1.getId().equals(3L)) {
							Map<Long, Double> idQiantity = new TreeMap<>();
							for (Component component : dish1.getComponents()) {
								for (ComponentWeight componentWeight : component
										.getComponents()) {
									if (ageCategory1.getId().equals(
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
							if (ageCategory1.getId().equals(1L)) {
								factProductsQuantityForm
										.setFactProductQuantityFirstAgeCategory(idQiantity);
							} else if (ageCategory1.getId().equals(2L)) {
								factProductsQuantityForm
										.setFactProductQuantitySecondAgeCategory(idQiantity);
							} else if (ageCategory1.getId().equals(3L)) {
								factProductsQuantityForm
										.setFactProductQuantityThirdAgeCategory(idQiantity);
							} else if (ageCategory1.getId().equals(4L)) {
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
}
