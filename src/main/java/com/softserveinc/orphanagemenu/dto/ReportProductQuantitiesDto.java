package com.softserveinc.orphanagemenu.dto;

import java.util.List;
import java.util.Map;

import com.softserveinc.orphanagemenu.model.AgeCategory;
import com.softserveinc.orphanagemenu.model.ConsumptionType;
import com.softserveinc.orphanagemenu.model.Product;

public class ReportProductQuantitiesDto {
	
	private List<ConsumptionType> consumptionTypes;
	private Map<ConsumptionType, Integer> consumptionTypeDishQuantities;
	private List<AgeCategory> ageCategories;
	private List<Product> products;
	private List<ProductQuantitiesReportColumn> columns;
	
	public ReportProductQuantitiesDto() {
	}
	
	public List<AgeCategory> getAgeCategories() {
		return ageCategories;
	}

	public void setAgeCategories(List<AgeCategory> ageCategories) {
		this.ageCategories = ageCategories;
	}

	public List<ConsumptionType> getConsumptionTypes() {
		return consumptionTypes;
	}

	public void setConsumptionTypes(List<ConsumptionType> consumptionTypes) {
		this.consumptionTypes = consumptionTypes;
	}

	public Map<ConsumptionType, Integer> getConsumptionTypeDishQuantities() {
		return consumptionTypeDishQuantities;
	}

	public void setConsumptionTypeDishQuantities(
			Map<ConsumptionType, Integer> consumptionTypeDishQuantity) {
		this.consumptionTypeDishQuantities = consumptionTypeDishQuantity;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public List<ProductQuantitiesReportColumn> getColumns() {
		return columns;
	}

	public void setColumns(List<ProductQuantitiesReportColumn> columns) {
		this.columns = columns;
	}

	@Override
	public String toString() {
		return "ReportProductQuantitiesDto [products=" + products
				+ ", columns=" + columns + "]";
	}
	
	
	

}
