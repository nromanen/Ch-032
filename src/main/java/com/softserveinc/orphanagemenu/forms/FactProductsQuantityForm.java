package com.softserveinc.orphanagemenu.forms;

import java.util.List;
import java.util.Map;

public class FactProductsQuantityForm {

	String dishName;
	String dailyMenuId;
	List<String> ageCategoryNames;
	List<String> productNames;
	// key = id factProductQuantity, value = factProductQuantity;
	Map<Long, Double> factProductQuantityFirstAgeCategory;
	Map<Long, Double> factProductQuantitySecondAgeCategory;
	Map<Long, Double> factProductQuantityThirdAgeCategory;
	Map<Long, Double> factProductQuantityFourthAgeCategory;

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

	public Map<Long, Double> getFactProductQuantityFirstAgeCategory() {
		return factProductQuantityFirstAgeCategory;
	}

	public void setFactProductQuantityFirstAgeCategory(
			Map<Long, Double> factProductQuantityFirstAgeCategory) {
		this.factProductQuantityFirstAgeCategory = factProductQuantityFirstAgeCategory;
	}

	public Map<Long, Double> getFactProductQuantitySecondAgeCategory() {
		return factProductQuantitySecondAgeCategory;
	}

	public void setFactProductQuantitySecondAgeCategory(
			Map<Long, Double> factProductQuantitySecondAgeCategory) {
		this.factProductQuantitySecondAgeCategory = factProductQuantitySecondAgeCategory;
	}

	public Map<Long, Double> getFactProductQuantityThirdAgeCategory() {
		return factProductQuantityThirdAgeCategory;
	}

	public void setFactProductQuantityThirdAgeCategory(
			Map<Long, Double> factProductQuantityThirdAgeCategory) {
		this.factProductQuantityThirdAgeCategory = factProductQuantityThirdAgeCategory;
	}

	public Map<Long, Double> getFactProductQuantityFourthAgeCategory() {
		return factProductQuantityFourthAgeCategory;
	}

	public void setFactProductQuantityFourthAgeCategory(
			Map<Long, Double> factProductQuantityFourthAgeCategory) {
		this.factProductQuantityFourthAgeCategory = factProductQuantityFourthAgeCategory;
	}
}
