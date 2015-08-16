package com.softserveinc.orphanagemenu.forms;

import java.util.List;
import java.util.Map;

import com.softserveinc.orphanagemenu.model.AgeCategory;
import com.softserveinc.orphanagemenu.model.Product;

public class FactProductsQuantityForm {
	
	String dishName;
	String submenuId;
	List<AgeCategory> ageCategory;
	List<Product> products; // productId & productName
	Map<Long, Double> factProductQuantityFirstAgeCategory; //key = id factProductQuantity, value = factProductQuantity;
	Map<Long, Double> factProductQuantitySecondAgeCategory;
	Map<Long, Double> factProductQuantityThirdAgeCategory;
	Map<Long, Double> factProductQuantityFourthAgeCategory;
	
	
	public String getDishName() {
		return dishName;
	}
	public void setDishName(String dishName) {
		this.dishName = dishName;
	}
	public String getSubmenuId() {
		return submenuId;
	}
	public void setSubmenuId(String submenuId) {
		this.submenuId = submenuId;
	}
	public List<AgeCategory> getAgeCategory() {
		return ageCategory;
	}
	public void setAgeCategory(List<AgeCategory> ageCategory) {
		this.ageCategory = ageCategory;
	}
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
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
