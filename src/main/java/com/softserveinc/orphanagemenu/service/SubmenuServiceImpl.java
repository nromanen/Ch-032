package com.softserveinc.orphanagemenu.service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserveinc.orphanagemenu.dao.AgeCategoryDao;
import com.softserveinc.orphanagemenu.dao.ConsumptionTypeDao;
import com.softserveinc.orphanagemenu.dao.DailyMenuDao;
import com.softserveinc.orphanagemenu.dao.DishDao;
import com.softserveinc.orphanagemenu.dao.FactProductQuantityDao;
import com.softserveinc.orphanagemenu.dao.ProductDao;
import com.softserveinc.orphanagemenu.dao.SubmenuDao;
import com.softserveinc.orphanagemenu.dao.WarehouseItemDao;
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
	public FactProductsQuantityForm getFactProductsQuantityForm() {
		FactProductsQuantityForm factProductsQuantityForm = new FactProductsQuantityForm();
		DailyMenu dailyMenu = dailyMenuDao.getById(1L);
		Dish dish = dishDao.getDishById(3L);
		factProductsQuantityForm.setDishName(dish.getName());
		System.out.println("Назва страви: "
				+ factProductsQuantityForm.getDishName());
		Map<String, String> productsName = new TreeMap<>();
		for (Component component : dish.getComponents()) {
			productsName.put(component.getId().toString(), component
					.getProduct().getName());
		}
		factProductsQuantityForm.setProductsName(productsName);
		for (Map.Entry<String, String> name : factProductsQuantityForm
				.getProductsName().entrySet()) {
			System.out.println("Компонент: " + name.getKey() + name.getValue());
		}
		List<AgeCategory> ageCategory = ageCategoryDao.getAllAgeCategory();
		for (AgeCategory ageCategory1 : ageCategory) {
			for (Submenu submenu : dailyMenu.getSubmenus()) {
				if (submenu.getConsumptionType().getId().equals(2L)
						&& submenu.getAgeCategory().equals(ageCategory1)) {
					System.out.println(submenu.getId() + "----------------");
					for (Dish dish1 : submenu.getDishes()) {
						if (dish1.getId().equals(3L)) {
							for (Component component : dish1.getComponents()) {
								for (ComponentWeight componentWeight : component
										.getComponents()) {
									if (ageCategory1.getId().equals(
											componentWeight.getAgeCategory()
													.getId())) {
										
										
										Map<Long, Double> idQiantity = new TreeMap<>();
										System.out.println(submenu.getId()+"   " +componentWeight.getId());
										
										FactProductQuantity fact = factProductQuantityDao.getFactProductQuantity(submenu, componentWeight);
										
										System.out.println(fact.getId()+"   " +fact.getFactProductQuantity());
										
										idQiantity.put(fact.getId(), fact.getComponentWeight().getStandartWeight());
										
										factProductsQuantityForm.setFactProductQuantityFirstAgeCategory(idQiantity);
//										System.out.println(factProductsQuantityForm.getFactProductQuantityFirstAgeCategory().toString());
										
										factProductsQuantityForm.setFactProductQuantitySecondAgeCategory(idQiantity);
										factProductsQuantityForm.setFactProductQuantityThirdAgeCategory(idQiantity);
										factProductsQuantityForm.setFactProductQuantityFourthAgeCategory(idQiantity);
										
						
									}
								}
							}

							System.out.println("Стравa на обід "
									+ dish1.toString()
									+ submenu.getAgeCategory().toString());

						}

					}

				}
			}
		}
		
		return factProductsQuantityForm;
	}
}
