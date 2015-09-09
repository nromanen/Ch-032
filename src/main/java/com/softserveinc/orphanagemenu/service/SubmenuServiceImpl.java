package com.softserveinc.orphanagemenu.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserveinc.orphanagemenu.dao.ComponentWeightDao;
import com.softserveinc.orphanagemenu.dao.ConsumptionTypeDao;
import com.softserveinc.orphanagemenu.dao.DailyMenuDao;
import com.softserveinc.orphanagemenu.dao.DishDao;
import com.softserveinc.orphanagemenu.dao.FactProductQuantityDao;
import com.softserveinc.orphanagemenu.dao.SubmenuDao;
import com.softserveinc.orphanagemenu.dto.DailyMenuDto;
import com.softserveinc.orphanagemenu.dto.DishesForConsumption;
import com.softserveinc.orphanagemenu.dto.IncludingDeficitDish;
import com.softserveinc.orphanagemenu.dto.SubmenuEditPageDto;
import com.softserveinc.orphanagemenu.dto.SubmenuEditPageTableDto;
import com.softserveinc.orphanagemenu.forms.FactProductsQuantityForm;
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
	private FactProductQuantityDao factProductQuantityDao;

	@Override
	public List<Submenu> getSubmenuListByDailyMenuAndConsumptionTypeId(Long dailyMenuId, Long consumptionTypeId) {
		return this.submenuDao.getSubmenuListByDailyMenuAndConsumptionTypeId(dailyMenuId, consumptionTypeId);
	}

	@Override
	public FactProductsQuantityForm getFactProductsQuantityForm(String dailyMenuId, String dishId, String consumptionTypeId) {
		List<Submenu> submenus = submenuDao.getSubmenuListByDailyMenuAndConsumptionTypeId(Long.parseLong(dailyMenuId),
				Long.parseLong(consumptionTypeId));
		List<FactProductQuantity> factProductQuantities = getFactProductQuantityListForDish(submenus, dishId);
		FactProductsQuantityForm factProductsQuantityForm = new FactProductsQuantityForm();
		factProductsQuantityForm.setDailyMenuId(dailyMenuId);
		factProductsQuantityForm.setDishName(getDishName(submenus, dishId));
		factProductsQuantityForm.setAgeCategoryNames(getAgeCategoryNames(factProductQuantities));
		factProductsQuantityForm
				.setFactProductQuantityFirstAgeCategory(getFactProductQuantityMapByAgeCategoryId(factProductQuantities, 1L));
		factProductsQuantityForm
				.setFactProductQuantitySecondAgeCategory(getFactProductQuantityMapByAgeCategoryId(factProductQuantities, 2L));
		factProductsQuantityForm
				.setFactProductQuantityThirdAgeCategory(getFactProductQuantityMapByAgeCategoryId(factProductQuantities, 3L));
		factProductsQuantityForm
				.setFactProductQuantityFourthAgeCategory(getFactProductQuantityMapByAgeCategoryId(factProductQuantities, 4L));
		factProductsQuantityForm.setProductNames(getProductNames(factProductQuantities, factProductsQuantityForm));
		return factProductsQuantityForm;
	}

	private String getDishName(List<Submenu> submenus, String dishId) {
		for (Submenu submenu : submenus) {
			return getDishName(submenu.getDishes(), dishId);
		}
		return null;
	}

	private String getDishName(Set<Dish> dishes, String dishId) {
		for (Dish dish : dishes) {
			if (dish.getId().toString().equals(dishId)) {
				return dish.getName();
			}
		}
		return null;
	}

	private List<String> getProductNames(List<FactProductQuantity> factProductQuantities, FactProductsQuantityForm factProductsQuantityForm) {
		List<String> productNames = new ArrayList<>();
		for (Map.Entry<Long, String> factProductQuantity : factProductsQuantityForm.getFactProductQuantityFirstAgeCategory().entrySet()) {
			productNames.add(getProductName(factProductQuantity.getKey(), factProductQuantities));
		}
		return productNames;
	}

	private String getProductName(Long factProductQuantityId, List<FactProductQuantity> factProductQuantityList) {
		for (FactProductQuantity factProductQuantity : factProductQuantityList) {
			if (factProductQuantityId.equals(factProductQuantity.getId())) {
				String name = factProductQuantity.getComponentWeight().getComponent().getProduct().getName() + " ("
						+ factProductQuantity.getComponentWeight().getComponent().getProduct().getDimension().getName() + ")";
				return name;
			}
		}
		return null;
	}

	private List<String> getAgeCategoryNames(List<FactProductQuantity> factProductQuantityList) {
		Set<String> ageCategoryNames = new LinkedHashSet<>();
		for (FactProductQuantity factProductQuantity : factProductQuantityList) {
			ageCategoryNames.add(factProductQuantity.getComponentWeight().getAgeCategory().getName());
		}
		List<String> ageCategoryNameList = new ArrayList<>();
		ageCategoryNameList.addAll(ageCategoryNames);
		return ageCategoryNameList;
	}

	/**
	 * Fill the map according to age category in accordance to ageCategoryId
	 * variable Replace "." to "," for showing "," on view in product norm
	 * value.
	 * 
	 **/
	private Map<Long, String> getFactProductQuantityMapByAgeCategoryId(List<FactProductQuantity> factProductQuantities, Long ageCategoryId) {
		Map<Long, String> factProductQuantityMap = new TreeMap<>();
		for (FactProductQuantity factProductQuantity : factProductQuantities) {
			if (factProductQuantity.getComponentWeight().getAgeCategory().getId().equals(ageCategoryId)) {
				factProductQuantityMap.put(factProductQuantity.getId(),
						factProductQuantity.getFactProductQuantity().toString().replace(".", ","));
			}
		}
		return factProductQuantityMap;
	}

	private List<FactProductQuantity> getFactProductQuantityListForDish(List<Submenu> submenus, String dishId) {
		List<FactProductQuantity> factProductQuantityList = new ArrayList<>();
		for (Submenu submenu : submenus) {
			List<FactProductQuantity> factProductQuantities = getListFactProductQuantityFromSubmenu(submenu, dishId);
			factProductQuantityList.addAll(factProductQuantities);
		}
		return factProductQuantityList;
	}

	private List<FactProductQuantity> getListFactProductQuantityFromSubmenu(Submenu submenu, String dishId) {
		List<FactProductQuantity> factProductQuantityList = new ArrayList<>();
		for (FactProductQuantity factProductQuantity : submenu.getFactProductQuantities()) {
			if (factProductQuantity.getComponentWeight().getComponent().getDish().getId().equals(Long.parseLong(dishId))) {
				factProductQuantityList.add(factProductQuantity);
			}
		}
		return factProductQuantityList;
	}

	@Override
	public FactProductsQuantityForm getStandartComponentQuantityForm(FactProductsQuantityForm factProductsQuantityForm) {
		List<Long> factProductQuantityIds = getFactProductQuantityIdsList(factProductsQuantityForm);
		List<FactProductQuantity> factProductQuantityList = factProductQuantityDao
				.getFactProductQuantityListByIdies(factProductQuantityIds);
		List<ComponentWeight> componentWeightList = getComponentWeightList(factProductQuantityList);
		List<Map<Long, String>> factProductQuantityMapList = getFactProductQuantityMapList(factProductsQuantityForm);
		for (Map<Long, String> factProductQuantityFormMap : factProductQuantityMapList) {
			setStandartComponentWeightFactProductWeight(factProductQuantityFormMap, factProductQuantityList, componentWeightList);
		}
		setFactProductQuantityFirstAgeCategory(factProductQuantityMapList, factProductsQuantityForm);
		setFactProductQuantitySecondAgeCategory(factProductQuantityMapList, factProductsQuantityForm);
		setFactProductQuantityThirdAgeCategory(factProductQuantityMapList, factProductsQuantityForm);
		setFactProductQuantityFourthAgeCategory(factProductQuantityMapList, factProductsQuantityForm);
		return factProductsQuantityForm;
	}

	private void setFactProductQuantityFourthAgeCategory(List<Map<Long, String>> factProductQuantityMapList,
			FactProductsQuantityForm factProductsQuantityForm) {
		factProductsQuantityForm.setFactProductQuantityFourthAgeCategory(factProductQuantityMapList.get(3));
	}

	private void setFactProductQuantityThirdAgeCategory(List<Map<Long, String>> factProductQuantityMapList,
			FactProductsQuantityForm factProductsQuantityForm) {
		factProductsQuantityForm.setFactProductQuantityThirdAgeCategory(factProductQuantityMapList.get(2));
	}

	private void setFactProductQuantitySecondAgeCategory(List<Map<Long, String>> factProductQuantityMapList,
			FactProductsQuantityForm factProductsQuantityForm) {
		factProductsQuantityForm.setFactProductQuantitySecondAgeCategory(factProductQuantityMapList.get(1));
	}

	private void setFactProductQuantityFirstAgeCategory(List<Map<Long, String>> factProductQuantityMapList,
			FactProductsQuantityForm factProductsQuantityForm) {
		factProductsQuantityForm.setFactProductQuantityFirstAgeCategory(factProductQuantityMapList.get(0));
	}

	private void setStandartComponentWeightFactProductWeight(Map<Long, String> factProductQuantityFormMap,
			List<FactProductQuantity> factProductQuantityList, List<ComponentWeight> componentWeightList) {
		for (Map.Entry<Long, String> factProductQuantityFormEntity : factProductQuantityFormMap.entrySet()) {
			FactProductQuantity factProductQuantity = getFactProductQuantityById(factProductQuantityFormEntity.getKey(),
					factProductQuantityList);
			ComponentWeight componentWeight = getComponentWeightById(componentWeightList, factProductQuantity);
			factProductQuantityFormEntity.setValue(componentWeight.getStandartWeight().toString().replace(".", ","));
		}
	}

	private ComponentWeight getComponentWeightById(List<ComponentWeight> componentWeightList, FactProductQuantity factProductQuantity) {
		for (ComponentWeight componentWeight : componentWeightList) {
			if (componentWeight.getId().equals(factProductQuantity.getComponentWeight().getId())) {
				return componentWeight;
			}
		}
		return null;
	}

	private FactProductQuantity getFactProductQuantityById(Long key, List<FactProductQuantity> factProductQuantityList) {
		for (FactProductQuantity factProductQuantity : factProductQuantityList) {
			if (factProductQuantity.getId().equals(key)) {
				return factProductQuantity;
			}
		}
		return null;
	}

	private List<Map<Long, String>> getFactProductQuantityMapList(FactProductsQuantityForm factProductsQuantityForm) {
		List<Map<Long, String>> factProductQuantityMapList = new ArrayList<>();
		factProductQuantityMapList.add(factProductsQuantityForm.getFactProductQuantityFirstAgeCategory());
		factProductQuantityMapList.add(factProductsQuantityForm.getFactProductQuantitySecondAgeCategory());
		factProductQuantityMapList.add(factProductsQuantityForm.getFactProductQuantityThirdAgeCategory());
		factProductQuantityMapList.add(factProductsQuantityForm.getFactProductQuantityFourthAgeCategory());
		return factProductQuantityMapList;
	}

	private List<ComponentWeight> getComponentWeightList(List<FactProductQuantity> factProductQuantityList) {
		List<Long> componentWeightIds = new ArrayList<>();
		for (FactProductQuantity factProductQuantity : factProductQuantityList) {
			componentWeightIds.add(factProductQuantity.getComponentWeight().getId());
		}
		return componentWeightDao.getComponentWeightListByIdies(componentWeightIds);
	}

	private List<Long> getFactProductQuantityIdsList(FactProductsQuantityForm factProductsQuantityForm) {
		List<Long> ids = new ArrayList<>();
		for (Map.Entry<Long, String> firstAgeMap : factProductsQuantityForm.getFactProductQuantityFirstAgeCategory().entrySet()) {
			ids.add(firstAgeMap.getKey());
		}
		for (Map.Entry<Long, String> secondAgeMap : factProductsQuantityForm.getFactProductQuantitySecondAgeCategory().entrySet()) {
			ids.add(secondAgeMap.getKey());
		}
		for (Map.Entry<Long, String> thirdAgeMap : factProductsQuantityForm.getFactProductQuantityThirdAgeCategory().entrySet()) {
			ids.add(thirdAgeMap.getKey());
		}
		for (Map.Entry<Long, String> fourthAgeMap : factProductsQuantityForm.getFactProductQuantityFourthAgeCategory().entrySet()) {
			ids.add(fourthAgeMap.getKey());
		}
		return ids;
	}

	@Override
	public void saveFactProductQuantity(FactProductsQuantityForm factProductsQuantityForm) {
		DailyMenu dailyMenu = dailyMenuDao.getById(Long.parseLong(factProductsQuantityForm.getDailyMenuId()));
		Map<Long, String> allFactProductQuantity = new TreeMap<>();
		List<Map<Long, String>> factProductQuantityMapList = getFactProductQuantityMapList(factProductsQuantityForm);
		for (Map<Long, String> factProductQuantityMap : factProductQuantityMapList) {
			collectFactProductQuantityMapEntity(factProductQuantityMap, allFactProductQuantity);
		}
		for (Submenu submenu : dailyMenu.getSubmenus()) {
			updateFactProductQuantitySet(submenu, allFactProductQuantity);
		}
		dailyMenuDao.updateDailyMenu(dailyMenu);
	}

	private void updateFactProductQuantitySet(Submenu submenu, Map<Long, String> allFactProductQuantity) {
		for (FactProductQuantity factProductQuantity : submenu.getFactProductQuantities()) {
			setNewFactProductQuantity(factProductQuantity, allFactProductQuantity);
		}
	}

	private void setNewFactProductQuantity(FactProductQuantity factProductQuantity, Map<Long, String> allFactProductQuantity) {
		for (Map.Entry<Long, String> quantityMap : allFactProductQuantity.entrySet()) {
			if (factProductQuantity.getId().equals(quantityMap.getKey())) {
				factProductQuantity.setFactProductQuantity(Double.valueOf(quantityMap.getValue().replace(",", ".")));
			}
		}
	}

	private void collectFactProductQuantityMapEntity(Map<Long, String> factProductQuantityMap, Map<Long, String> allFactProductQuantity) {
		for (Map.Entry<Long, String> factProductQuantityEntity : factProductQuantityMap.entrySet()) {
			allFactProductQuantity.put(factProductQuantityEntity.getKey(), factProductQuantityEntity.getValue());
		}
	}

	@Override
	public Submenu getById(Long id) {
		return this.submenuDao.getById(id);
	}

	public void addDishToSubmenus(Long dailyMenuId, Long consumptionTypeId, Long dishId) {
		for (Submenu submenu : submenuDao.getSubmenuListByDailyMenuAndConsumptionTypeId(dailyMenuId, consumptionTypeId)) {
			if (submenu.getConsumptionType().getId().equals(consumptionTypeId)) {
				insertDish(submenu, dishDao.getDishById(dishId));
			}
		}
	}

	public void removeDishFromSubmenus(Long dailyMenuId, Long consumptionTypeId, Long dishId) {
		for (Submenu submenu : submenuDao.getSubmenuListByDailyMenuAndConsumptionTypeId(dailyMenuId, consumptionTypeId)) {
			if (submenu.getConsumptionType().getId().equals(consumptionTypeId)) {
				removeDish(submenu, dishDao.getDishById(dishId));
			}
		}
	}

	public void setChildQuantityToSubmenus(Long dailyMenuId, Long consumptionTypeId, Map<String, String> params) {
		for (Submenu submenu : getSubmenuListByDailyMenuAndConsumptionTypeId(dailyMenuId, consumptionTypeId)) {
			submenu.setChildQuantity(Integer.parseInt(params.get(submenu.getAgeCategory().getId().toString())));
			submenuDao.update(submenu);
		}
	}

	public void insertDish(Submenu submenu, Dish dish) {
		submenu.getDishes().add(dish);
		for (Component component : dish.getComponents()) {
			for (ComponentWeight compWeight : component.getComponents()) {
				if (!submenu.getAgeCategory().equals(compWeight.getAgeCategory())) {
					continue;
				}
				FactProductQuantity factProductQuantity = new FactProductQuantity();
				factProductQuantity.setComponentWeight(compWeight);
				factProductQuantity.setSubmenu(submenu);
				factProductQuantity.setFactProductQuantity(compWeight.getStandartWeight());
				submenu.getFactProductQuantities().add(factProductQuantity);
			}
		}
		submenuDao.update(submenu);
	}

	public void removeDish(Submenu submenu, Dish dish) {
		submenu.getDishes().remove(dish);
		for (FactProductQuantity item : submenu.getFactProductQuantities()) {
			if (item.getComponentWeight().getComponent().getDish().equals(dish)) {
				submenu.getFactProductQuantities().remove(item);
				break;
			}

		}
		submenuDao.update(submenu);
	}
	
	/**
	 * Forming of SubmenuEditPageDto for SubmenuEdit.jsp
	 **/
	public SubmenuEditPageDto createSubmenuEditPageDto(Long dailyMenuId, Long consumptionTypeId) {
		SubmenuEditPageDto submenuDto = new SubmenuEditPageDto();
		Set<Dish> presentDishes = new HashSet<Dish>();
		List<Dish> allDishes = dishDao.getAllDish();
		List<IncludingDeficitDish> dishesWithDeficit = new ArrayList<IncludingDeficitDish>();
		List<SubmenuEditPageTableDto> submenuEditTableDtos = new ArrayList<SubmenuEditPageTableDto>();

		// get list of dishes with deficits for our submenu
		DailyMenuDto dmdto = dailyMenuService.getDailyMenuDtoForDay(dailyMenuService.getById(dailyMenuId).getDate());
		for (DishesForConsumption a : dmdto.getDishesForConsumptions()) {
			if (a.getConsumptionType().getId().equals(consumptionTypeId)) {
				dishesWithDeficit = a.getIncludingDeficitDishes();
				submenuDto.setAgeCatsAndQty(a.getChildQuantity());
			}
		}
		// create new collection of SubmenuEditDto's
		for (IncludingDeficitDish x : dishesWithDeficit) {
			// forming list of all product
			if (!presentDishes.contains(x.getDish())) {
				presentDishes.add(x.getDish());
			}
			SubmenuEditPageTableDto a = new SubmenuEditPageTableDto();
			a.setDishAndDeficit(x);
			submenuEditTableDtos.add(a);
		}
		for (Dish dish : presentDishes) {
			allDishes.remove(dish);
		}
		submenuDto.setDishes(allDishes);
		submenuDto.setSubmenuEditTableDtos(submenuEditTableDtos);
		submenuDto.setConsumptionTypeName(consumptionTypeDao.getById(consumptionTypeId).getName());
		submenuDto.setDate(dmdto.getDate());
		return submenuDto;
	}
}
