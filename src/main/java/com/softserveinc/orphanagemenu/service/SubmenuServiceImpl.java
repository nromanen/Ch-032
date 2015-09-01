package com.softserveinc.orphanagemenu.service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserveinc.orphanagemenu.dao.AgeCategoryDao;
import com.softserveinc.orphanagemenu.dao.ComponentWeightDao;
import com.softserveinc.orphanagemenu.dao.ConsumptionTypeDao;
import com.softserveinc.orphanagemenu.dao.DailyMenuDao;
import com.softserveinc.orphanagemenu.dao.DishDao;
import com.softserveinc.orphanagemenu.dao.FactProductQuantityDao;
import com.softserveinc.orphanagemenu.dao.SubmenuDao;
import com.softserveinc.orphanagemenu.dto.DailyMenuDto;
import com.softserveinc.orphanagemenu.dto.DishesForConsumption;
import com.softserveinc.orphanagemenu.dto.IncludingDeficitDish;
import com.softserveinc.orphanagemenu.dto.SubmenuDto;
import com.softserveinc.orphanagemenu.dto.SubmenuEditTableDto;
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

	private static final Logger LOGGER = LoggerFactory
			.getLogger(SubmenuServiceImpl.class);

	@Autowired
	private ComponentWeightDao componentWeightDao;

	@Autowired
	private DailyMenuDao dailyMenuDao;

	@Autowired
	private SubmenuDao submenuDao;

	@Autowired
	private DishDao dishDao;

	@Autowired
	private ConsumptionTypeDao consumptionTypeDao;

	@Autowired
	private DailyMenuService dailyMenuService;

	@Autowired
	private AgeCategoryDao ageCategoryDao;

	@Autowired
	private FactProductQuantityDao factProductQuantityDao;

	@Override
	public List<Submenu> getSubmenuListByDailyMenuAndConsumptionTypeId(
			Long dailyMenuId, Long consumptionTypeId) {
		return this.submenuDao.getSubmenuListByDailyMenuAndConsumptionTypeId(
				dailyMenuId, consumptionTypeId);
	}
//	@Override
//	public FactProductsQuantityForm getFactProductsQuantityForm(
//			String dailyMenuId, String dishId, String consumptionTypeId) {
//		FactProductsQuantityForm factProductsQuantityForm = new FactProductsQuantityForm();
//		factProductsQuantityForm.setDailyMenuId(dailyMenuId);
//		List<Submenu> submenus = submenuDao
//				.getSubmenuListByDailyMenuAndConsumptionTypeId(
//						Long.parseLong(dailyMenuId),
//						Long.parseLong(consumptionTypeId));
//		List<AgeCategory> ageCategory1 = ageCategoryDao.getAllAgeCategory();
//		List<String> ageCategoryNames = new ArrayList<>();
//		for (AgeCategory ageCategory : ageCategory1) {
//			ageCategoryNames.add(ageCategory.getName());
//			for (Submenu submenu : submenus) {
//				if (submenu.getAgeCategory().equals(ageCategory)) {
//					for (Dish dish : submenu.getDishes()) {
//						if (dish.getId().equals(Long.parseLong(dishId))) {
//							if (factProductsQuantityForm.getDishName() == null) {
//								factProductsQuantityForm.setDishName(dish
//										.getName());
//								List<String> productNames = new ArrayList<>();
//								for (Component component : dish.getComponents()) {
//									productNames.add(component.getProduct()
//											.getName());
//								}
//								factProductsQuantityForm
//										.setProductNames(productNames);
//							}
//							Map<Long, String> idQiantity = new TreeMap<>();
//							for (Component component : dish.getComponents()) {
//								for (ComponentWeight componentWeight : component
//										.getComponents()) {
//									if (ageCategory.getId().equals(
//											componentWeight.getAgeCategory()
//													.getId())) {
//										FactProductQuantity fact = factProductQuantityDao
//												.getFactProductQuantity(
//														submenu,
//														componentWeight);
//										idQiantity.put(fact.getId(), fact
//												.getFactProductQuantity()
//												.toString().replace(".", ","));
//									}
//								}
//							}
//							if (ageCategory.equals(ageCategory1.get(0))) {
//								factProductsQuantityForm
//										.setFactProductQuantityFirstAgeCategory(idQiantity);
//							} else if (ageCategory.equals(ageCategory1.get(1))) {
//								factProductsQuantityForm
//										.setFactProductQuantitySecondAgeCategory(idQiantity);
//							} else if (ageCategory.equals(ageCategory1.get(2))) {
//								factProductsQuantityForm
//										.setFactProductQuantityThirdAgeCategory(idQiantity);
//							} else if (ageCategory.equals(ageCategory1.get(3))) {
//								factProductsQuantityForm
//										.setFactProductQuantityFourthAgeCategory(idQiantity);
//							}
//						}
//					}
//				}
//			}
//		}
//		factProductsQuantityForm.setAgeCategoryNames(ageCategoryNames);
//		return factProductsQuantityForm;
//	}
	
	@Override
	public FactProductsQuantityForm getFactProductsQuantityForm(
			String dailyMenuId, String dishId, String consumptionTypeId) {
		List<Submenu> submenus = submenuDao
				.getSubmenuListByDailyMenuAndConsumptionTypeId(
						Long.parseLong(dailyMenuId),
						Long.parseLong(consumptionTypeId));
		List<FactProductQuantity> factProductQuantityList = getFactProductQuantityListForDish(submenus, dishId);
		FactProductsQuantityForm factProductsQuantityForm = new FactProductsQuantityForm();
		factProductsQuantityForm.setDailyMenuId(dailyMenuId);
		factProductsQuantityForm.setDishName(factProductQuantityList.get(0).getComponentWeight().getComponent().getDish().getName());
		factProductsQuantityForm.setAgeCategoryNames(getAgeCategoryNames(factProductQuantityList));
		factProductsQuantityForm.setProductNames(getProductNamesList(factProductQuantityList));
		factProductsQuantityForm.setFactProductQuantityFirstAgeCategory(getFactProductQuantityMapByAgeCategoryId(factProductQuantityList, 1L));
		factProductsQuantityForm.setFactProductQuantitySecondAgeCategory(getFactProductQuantityMapByAgeCategoryId(factProductQuantityList, 2L));
		factProductsQuantityForm.setFactProductQuantityThirdAgeCategory(getFactProductQuantityMapByAgeCategoryId(factProductQuantityList, 3L));
		factProductsQuantityForm.setFactProductQuantityFourthAgeCategory(getFactProductQuantityMapByAgeCategoryId(factProductQuantityList, 4L));
		return factProductsQuantityForm;
	}

	private List<String> getProductNamesList(
			List<FactProductQuantity> factProductQuantityList) {
		Set<String> productNames = new LinkedHashSet<>();
		for (FactProductQuantity factProductQuantity : factProductQuantityList){
			productNames.add(factProductQuantity.getComponentWeight().getComponent().getProduct().getName());
		}
		List<String> productNamesList = new ArrayList<>();
		productNamesList.addAll(productNames);
		return productNamesList;
	}

//	private void addUniqueproductName(FactProductQuantity factProductQuantity,
//			List<String> productNames) {
//		int i=0;
//		for (String productName : productNames){
//			if (productName.equals(factProductQuantity.getComponentWeight().getComponent().getProduct().getName())){
//				i++;
//			}
//		}
//		if (i==0){
//			productNames.add(factProductQuantity.getComponentWeight().getComponent().getProduct().getName());
//		}
//	}

	private List<String> getAgeCategoryNames(
			List<FactProductQuantity> factProductQuantityList) {
		Set<String> ageCategoryNames = new LinkedHashSet<>();
		for (FactProductQuantity factProductQuantity : factProductQuantityList){
			ageCategoryNames.add(factProductQuantity.getComponentWeight().getAgeCategory().getName());
		}
		List<String> ageCategoryNameList = new ArrayList<>();
		ageCategoryNameList.addAll(ageCategoryNames);
		return ageCategoryNameList;
	}

	private Map<Long, String> getFactProductQuantityMapByAgeCategoryId(
			List<FactProductQuantity> factProductQuantityList, Long ageCategoryId) {
		Map<Long, String> factProductQuantityMap = new TreeMap<>();
		for (FactProductQuantity factProductQuantity : factProductQuantityList){
			if (factProductQuantity.getComponentWeight().getAgeCategory().getId().equals(ageCategoryId)){
				factProductQuantityMap.put(factProductQuantity.getId(), 
						factProductQuantity.getFactProductQuantity().toString().replace(".", ","));
			}
		}
		return factProductQuantityMap;
	}

	private List<FactProductQuantity> getFactProductQuantityListForDish(
			List<Submenu> submenus, String dishId) {
		List<FactProductQuantity> factProductQuantityList = new ArrayList<>();
		for (Submenu submenu : submenus) {
			List<FactProductQuantity> factProductQuantities = getListFactProductQuantityFromSubmenu(
					submenu, dishId);
			factProductQuantityList.addAll(factProductQuantities);
		}
		return factProductQuantityList;
	}

	private List<FactProductQuantity> getListFactProductQuantityFromSubmenu(
			Submenu submenu, String dishId) {
		List<FactProductQuantity> factProductQuantityList = new ArrayList<>();
		for (FactProductQuantity factProductQuantity : submenu
				.getFactProductQuantities()) {
			if (factProductQuantity.getComponentWeight().getComponent()
					.getDish().getId().equals(Long.parseLong(dishId))) {
				factProductQuantityList.add(factProductQuantity);
			}
		}
		return factProductQuantityList;
	}

	@Override
	public FactProductsQuantityForm getStandartComponentQuantityForm(
			FactProductsQuantityForm factProductsQuantityForm) {
		List<Long> factProductQuantityIds = getFactProductQuantityIdsList(factProductsQuantityForm);
		List<FactProductQuantity> factProductQuantityList = factProductQuantityDao
				.getFactProductQuantityListByIdies(factProductQuantityIds);
		List<ComponentWeight> componentWeightList = getComponentWeightList(factProductQuantityList);
		List<Map<Long, String>> factProductQuantityMapList = getFactProductQuantityMapList(factProductsQuantityForm);
		for (Map<Long, String> factProductQuantityFormMap : factProductQuantityMapList) {
			setStandartComponentWeightFactProductWeight(
					factProductQuantityFormMap, factProductQuantityList,
					componentWeightList);
		}
		setFactProductQuantityFirstAgeCategory(factProductQuantityMapList,
				factProductsQuantityForm);
		setFactProductQuantitySecondAgeCategory(factProductQuantityMapList,
				factProductsQuantityForm);
		setFactProductQuantityThirdAgeCategory(factProductQuantityMapList,
				factProductsQuantityForm);
		setFactProductQuantityFourthAgeCategory(factProductQuantityMapList,
				factProductsQuantityForm);
		return factProductsQuantityForm;
	}

	private void setFactProductQuantityFourthAgeCategory(
			List<Map<Long, String>> factProductQuantityMapList,
			FactProductsQuantityForm factProductsQuantityForm) {
		factProductsQuantityForm
				.setFactProductQuantityFourthAgeCategory(factProductQuantityMapList
						.get(3));
	}

	private void setFactProductQuantityThirdAgeCategory(
			List<Map<Long, String>> factProductQuantityMapList,
			FactProductsQuantityForm factProductsQuantityForm) {
		factProductsQuantityForm
				.setFactProductQuantityThirdAgeCategory(factProductQuantityMapList
						.get(2));
	}

	private void setFactProductQuantitySecondAgeCategory(
			List<Map<Long, String>> factProductQuantityMapList,
			FactProductsQuantityForm factProductsQuantityForm) {
		factProductsQuantityForm
				.setFactProductQuantitySecondAgeCategory(factProductQuantityMapList
						.get(1));
	}

	private void setFactProductQuantityFirstAgeCategory(
			List<Map<Long, String>> factProductQuantityMapList,
			FactProductsQuantityForm factProductsQuantityForm) {
		factProductsQuantityForm
				.setFactProductQuantityFirstAgeCategory(factProductQuantityMapList
						.get(0));
	}

	private void setStandartComponentWeightFactProductWeight(
			Map<Long, String> factProductQuantityFormMap,
			List<FactProductQuantity> factProductQuantityList,
			List<ComponentWeight> componentWeightList) {
		for (Map.Entry<Long, String> factProductQuantityFormEntity : factProductQuantityFormMap
				.entrySet()) {
			FactProductQuantity factProductQuantity = getFactProductQuantityById(
					factProductQuantityFormEntity.getKey(),
					factProductQuantityList);
			ComponentWeight componentWeight = getComponentWeightById(
					componentWeightList, factProductQuantity);
			factProductQuantityFormEntity.setValue(componentWeight
					.getStandartWeight().toString().replace(".", ","));
		}
	}

	private ComponentWeight getComponentWeightById(
			List<ComponentWeight> componentWeightList,
			FactProductQuantity factProductQuantity) {
		for (ComponentWeight componentWeight : componentWeightList) {
			if (componentWeight.getId().equals(
					factProductQuantity.getComponentWeight().getId())) {
				return componentWeight;
			}
		}
		return null;
	}

	private FactProductQuantity getFactProductQuantityById(Long key,
			List<FactProductQuantity> factProductQuantityList) {
		for (FactProductQuantity factProductQuantity : factProductQuantityList) {
			if (factProductQuantity.getId().equals(key)) {
				return factProductQuantity;
			}
		}
		return null;
	}

	private List<Map<Long, String>> getFactProductQuantityMapList(
			FactProductsQuantityForm factProductsQuantityForm) {
		List<Map<Long, String>> factProductQuantityMapList = new ArrayList<>();
		factProductQuantityMapList.add(factProductsQuantityForm
				.getFactProductQuantityFirstAgeCategory());
		factProductQuantityMapList.add(factProductsQuantityForm
				.getFactProductQuantitySecondAgeCategory());
		factProductQuantityMapList.add(factProductsQuantityForm
				.getFactProductQuantityThirdAgeCategory());
		factProductQuantityMapList.add(factProductsQuantityForm
				.getFactProductQuantityFourthAgeCategory());
		return factProductQuantityMapList;
	}

	private List<ComponentWeight> getComponentWeightList(
			List<FactProductQuantity> factProductQuantityList) {
		List<Long> componentWeightIds = new ArrayList<>();
		for (FactProductQuantity factProductQuantity : factProductQuantityList) {
			componentWeightIds.add(factProductQuantity.getComponentWeight()
					.getId());
		}
		return componentWeightDao
				.getComponentWeightListByIdies(componentWeightIds);
	}

	private List<Long> getFactProductQuantityIdsList(
			FactProductsQuantityForm factProductsQuantityForm) {
		List<Long> ids = new ArrayList<>();
		for (Map.Entry<Long, String> firstAgeMap : factProductsQuantityForm
				.getFactProductQuantityFirstAgeCategory().entrySet()) {
			ids.add(firstAgeMap.getKey());
		}
		for (Map.Entry<Long, String> secondAgeMap : factProductsQuantityForm
				.getFactProductQuantitySecondAgeCategory().entrySet()) {
			ids.add(secondAgeMap.getKey());
		}
		for (Map.Entry<Long, String> thirdAgeMap : factProductsQuantityForm
				.getFactProductQuantityThirdAgeCategory().entrySet()) {
			ids.add(thirdAgeMap.getKey());
		}
		for (Map.Entry<Long, String> fourthAgeMap : factProductsQuantityForm
				.getFactProductQuantityFourthAgeCategory().entrySet()) {
			ids.add(fourthAgeMap.getKey());
		}
		return ids;
	}

	@Override
	public void saveFactProductQuantity(
			FactProductsQuantityForm factProductsQuantityForm) {
		DailyMenu dailyMenu = dailyMenuDao.getById(Long
				.parseLong(factProductsQuantityForm.getDailyMenuId()));
		Map<Long, String> allFactProductQuantity = new TreeMap<>();
		List<Map<Long, String>> factProductQuantityMapList = getFactProductQuantityMapList(factProductsQuantityForm);
		for (Map<Long, String> factProductQuantityMap : factProductQuantityMapList) {
			collectFactProductQuantityMapEntity(factProductQuantityMap,
					allFactProductQuantity);
		}
		for (Submenu submenu : dailyMenu.getSubmenus()) {
			updateFactProductQuantitySet(submenu, allFactProductQuantity);
		}
		dailyMenuDao.updateDailyMenu(dailyMenu);
		LOGGER.info("Info update ====================================================");
	}

	private void updateFactProductQuantitySet(Submenu submenu,
			Map<Long, String> allFactProductQuantity) {
		for (FactProductQuantity factProductQuantity : submenu
				.getFactProductQuantities()) {
			setNewFactProductQuantity(factProductQuantity,
					allFactProductQuantity);
		}
	}

	private void setNewFactProductQuantity(
			FactProductQuantity factProductQuantity,
			Map<Long, String> allFactProductQuantity) {
		for (Map.Entry<Long, String> quantityMap : allFactProductQuantity
				.entrySet()) {
			if (factProductQuantity.getId().equals(quantityMap.getKey())) {
				quantityMap.setValue(quantityMap.getValue().replace(",", "."));
				factProductQuantity.setFactProductQuantity(Double
						.valueOf(new DecimalFormat("#.##").format(Double
								.parseDouble(quantityMap.getValue()))));
			}
		}
	}

	private void collectFactProductQuantityMapEntity(
			Map<Long, String> factProductQuantityMap,
			Map<Long, String> allFactProductQuantity) {
		for (Map.Entry<Long, String> factProductQuantityEntity : factProductQuantityMap
				.entrySet()) {
			allFactProductQuantity.put(factProductQuantityEntity.getKey(),
					factProductQuantityEntity.getValue());
		}
	}

	@Override
	public Submenu getById(Long id) {
		return this.submenuDao.getById(id);
	}

	public SubmenuDto getSubmenuDto(Long dailyMenuId, Long consumptionTypeId) {
		SubmenuDto submenuDto = new SubmenuDto();
		Set<Dish> presentDishes = new HashSet<Dish>();
		List<Dish> allDishes = dishDao.getAllDish();
		List<IncludingDeficitDish> dishesWithDeficit = new ArrayList<IncludingDeficitDish>();
		List<SubmenuEditTableDto> submenuEditTableDtos = new ArrayList<SubmenuEditTableDto>();
		// get list of dishes with deficits for our submenu
		DailyMenuDto dmdto = dailyMenuService
				.getDailyMenuDtoForDay(dailyMenuService.getById(dailyMenuId)
						.getDate());
		for (DishesForConsumption a : dmdto.getDishesForConsumptions()) {
			if (a.getConsumptionType().getId().equals(consumptionTypeId)) {
				dishesWithDeficit = a.getIncludingDeficitDishes();
				submenuDto.setAgeCatsAndQty(a.getChildQuantity());
			}
		}
		// create new collection of SubmenuEditDto's
		for (IncludingDeficitDish x : dishesWithDeficit) {
			// формуємо список всіх страв
			if (!presentDishes.contains(x.getDish())) {
				presentDishes.add(x.getDish());
			}
			SubmenuEditTableDto a = new SubmenuEditTableDto();
			a.setDishAndDeficit(x);
			submenuEditTableDtos.add(a);
		}
		for (Dish dish : presentDishes) {
			allDishes.remove(dish);
		}
		submenuDto.setDishes(allDishes);
		submenuDto.setSubmenuEditTableDtos(submenuEditTableDtos);
		submenuDto.setConsumptionTypeName(consumptionTypeDao.getById(
				consumptionTypeId).getName());
		submenuDto.setDate(dmdto.getDate());
		return submenuDto;
	}

	public void addDishToSubmenuList(Long dailyMenuId, Long consumptionTypeId,
			Long dishId) {
		DailyMenu dm = dailyMenuDao.getById(dailyMenuId);
		Set<Submenu> submenuList = dm.getSubmenus();
		for (Submenu submenu : submenuList) {
			if (submenu.getConsumptionType().getId().equals(consumptionTypeId)) {
				Set<Dish> dishes = submenu.getDishes();
				Dish tempDish = dishDao.getDishById(dishId);
				dishes.add(tempDish);
				submenu.setDishes(dishes);
				for (Component component : tempDish.getComponents()) {
					for (ComponentWeight compWeight : component.getComponents()) {
						FactProductQuantity factProductQuantity = new FactProductQuantity();
						factProductQuantity.setComponentWeight(compWeight);
						factProductQuantity.setSubmenu(submenu);
						factProductQuantity.setFactProductQuantity(compWeight
								.getStandartWeight());
						submenu.getFactProductQuantities().add(
								factProductQuantity);
					}
				}
			}
		}
		dm.setSubmenus(submenuList);
		dailyMenuDao.updateDailyMenu(dm);
		LOGGER.info("Info update =================================================================");

	}
	
	public void setChildQuantityToSubmenuListByDailyMenuAndConsumptionTypeId(Long dailyMenuId, Long consumptionTypeId, Map<String, String> params){
		for(Submenu submenu : getSubmenuListByDailyMenuAndConsumptionTypeId(dailyMenuId, consumptionTypeId)) {
			Integer a = Integer.parseInt(params.get(submenu.getAgeCategory().getId().toString()));
			System.out.println(a);
						submenu.setChildQuantity(a);
			submenuDao.update(submenu);			
		}
	}
}
