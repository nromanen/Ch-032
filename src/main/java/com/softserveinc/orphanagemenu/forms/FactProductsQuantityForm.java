package com.softserveinc.orphanagemenu.forms;

import java.util.List;
import java.util.Map;

public class FactProductsQuantityForm {

	String dailyMenuId;
	String dishName;
	List<String> ageCategoryNames;
	
	List<String> productNames;
	// key = id factProductQuantity, value = factProductQuantity;
	Map<String, String> factProductQuantityFirstAgeCategory;
	Map<String, String> factProductQuantitySecondAgeCategory;
	Map<String, String> factProductQuantityThirdAgeCategory;
	Map<String, String> factProductQuantityFourthAgeCategory;

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

	public Map<String, String> getFactProductQuantityFirstAgeCategory() {
		return factProductQuantityFirstAgeCategory;
	}

	public void setFactProductQuantityFirstAgeCategory(
			Map<String, String> factProductQuantityFirstAgeCategory) {
		this.factProductQuantityFirstAgeCategory = factProductQuantityFirstAgeCategory;
	}

	public Map<String, String> getFactProductQuantitySecondAgeCategory() {
		return factProductQuantitySecondAgeCategory;
	}

	public void setFactProductQuantitySecondAgeCategory(
			Map<String, String> factProductQuantitySecondAgeCategory) {
		this.factProductQuantitySecondAgeCategory = factProductQuantitySecondAgeCategory;
	}

	public Map<String, String> getFactProductQuantityThirdAgeCategory() {
		return factProductQuantityThirdAgeCategory;
	}

	public void setFactProductQuantityThirdAgeCategory(
			Map<String, String> factProductQuantityThirdAgeCategory) {
		this.factProductQuantityThirdAgeCategory = factProductQuantityThirdAgeCategory;
	}

	public Map<String, String> getFactProductQuantityFourthAgeCategory() {
		return factProductQuantityFourthAgeCategory;
	}

	public void setFactProductQuantityFourthAgeCategory(
			Map<String, String> factProductQuantityFourthAgeCategory) {
		this.factProductQuantityFourthAgeCategory = factProductQuantityFourthAgeCategory;
	}
}
