package com.softserveinc.orphanagemenu.forms;

import java.util.Map;

public class FactProductsQuantityForm {
	
//	Map<String, Map<String, String>> productAgeCategoryStandartQuantity;
	String dishName;
	String submenuId;
	Map<String, String> ageCategoryIdSubmenuId; // ageCategoryId & submenuId????????????????????????????
	Map<String, String> factProductQuantityFirstAgeCategory; //key = id factProductQuantity, value = factProductQuantity;
	Map<String, String> factProductQuantitySecondAgeCategory;
	Map<String, String> factProductQuantityThirdAgeCategory;
	Map<String, String> factProductQuantityFourthAgeCategory;
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
	public Map<String, String> getProductsName() {
		return productsName;
	}
	public void setProductsName(Map<String, String> productsName) {
		this.productsName = productsName;
	}
	
}
