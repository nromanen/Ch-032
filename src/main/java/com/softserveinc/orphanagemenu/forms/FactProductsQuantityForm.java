package com.softserveinc.orphanagemenu.forms;

import java.util.List;
import java.util.Map;

public class FactProductsQuantityForm {

	String dailyMenuId;
	String dishName;
	List<String> ageCategoryNames;
	List<String> productNames;
	// key = id factProductQuantity, value = factProductQuantity;
	Map<Long, String> factProductQuantityFirstAgeCategory;
	Map<Long, String> factProductQuantitySecondAgeCategory;
	Map<Long, String> factProductQuantityThirdAgeCategory;
	Map<Long, String> factProductQuantityFourthAgeCategory;

	public String getDishName() {
		return dishName;
	}

	public void setDishName(String dishName) {
		this.dishName = dishName;
	}

	public String getDailyMenuId() {
		return dailyMenuId;
	}

	public void setDailyMenuId(String dailyMenuId) {
		this.dailyMenuId = dailyMenuId;
	}

	public List<String> getAgeCategoryNames() {
		return ageCategoryNames;
	}

	public void setAgeCategoryNames(List<String> ageCategoryName) {
		this.ageCategoryNames = ageCategoryName;
	}

	public List<String> getProductNames() {
		return productNames;
	}

	public void setProductNames(List<String> productNames) {
		this.productNames = productNames;
	}

	public Map<Long, String> getFactProductQuantityFirstAgeCategory() {
		return factProductQuantityFirstAgeCategory;
	}

	public void setFactProductQuantityFirstAgeCategory(
			Map<Long, String> factProductQuantityFirstAgeCategory) {
		this.factProductQuantityFirstAgeCategory = factProductQuantityFirstAgeCategory;
	}

	public Map<Long, String> getFactProductQuantitySecondAgeCategory() {
		return factProductQuantitySecondAgeCategory;
	}

	public void setFactProductQuantitySecondAgeCategory(
			Map<Long, String> factProductQuantitySecondAgeCategory) {
		this.factProductQuantitySecondAgeCategory = factProductQuantitySecondAgeCategory;
	}

	public Map<Long, String> getFactProductQuantityThirdAgeCategory() {
		return factProductQuantityThirdAgeCategory;
	}

	public void setFactProductQuantityThirdAgeCategory(
			Map<Long, String> factProductQuantityThirdAgeCategory) {
		this.factProductQuantityThirdAgeCategory = factProductQuantityThirdAgeCategory;
	}

	public Map<Long, String> getFactProductQuantityFourthAgeCategory() {
		return factProductQuantityFourthAgeCategory;
	}

	public void setFactProductQuantityFourthAgeCategory(
			Map<Long, String> factProductQuantityFourthAgeCategory) {
		this.factProductQuantityFourthAgeCategory = factProductQuantityFourthAgeCategory;
	}
}
