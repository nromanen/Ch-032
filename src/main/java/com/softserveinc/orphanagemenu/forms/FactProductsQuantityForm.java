package com.softserveinc.orphanagemenu.forms;

import java.util.Map;

public class FactProductsQuantityForm {
	
//	Map<String, Map<String, String>> productAgeCategoryStandartQuantity;
	String dishName;
	String submenuId;
	Map<String, String> ageCategoryIdSubmenuId; // ageCategoryId & submenuId????????????????????????????
	Map<Long, Double> factProductQuantityFirstAgeCategory; //key = id factProductQuantity, value = factProductQuantity;
	Map<Long, Double> factProductQuantitySecondAgeCategory;
	Map<Long, Double> factProductQuantityThirdAgeCategory;
	Map<Long, Double> factProductQuantityFourthAgeCategory;
	Map<String, String> productsName; // productId & productName
	
	
	public String getSubmenuId() {
		return submenuId;
	}
	public void setSubmenuId(String submenuId) {
		this.submenuId = submenuId;
	}
//	public Map<String, Map<String, String>> getProductAgeCategoryStandartQuantity() {
//		return productAgeCategoryStandartQuantity;
//	}
//	public void setProductAgeCategoryStandartQuantity(
//			Map<String, Map<String, String>> productAgeCategoryStandartQuantity) {
//		this.productAgeCategoryStandartQuantity = productAgeCategoryStandartQuantity;
//	}
	public String getDishName() {
		return dishName;
	}
	public void setDishName(String dishName) {
		this.dishName = dishName;
	}
	public Map<String, String> getAgeCategoryIdSubmenuId() {
		return ageCategoryIdSubmenuId;
	}
	public void setAgeCategoryIdSubmenuId(Map<String, String> ageCategoryIdSubmenuId) {
		this.ageCategoryIdSubmenuId = ageCategoryIdSubmenuId;
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
	public Map<String, String> getProductsName() {
		return productsName;
	}
	public void setProductsName(Map<String, String> productsName) {
		this.productsName = productsName;
	}
	
}
