package com.softserveinc.orphanagemenu.dto;

import java.util.List;

import com.softserveinc.orphanagemenu.model.AgeCategory;
import com.softserveinc.orphanagemenu.model.Product;

public class ReportProductQuantitiesDto {

	List<AgeCategory> ageCategories;
	List<Product> products;
	List<ProductQuantitiesReportColumn> columns;
	
	public ReportProductQuantitiesDto() {
	}

	
	
	public List<AgeCategory> getAgeCategories() {
		return ageCategories;
	}



	public void setAgeCategories(List<AgeCategory> ageCategories) {
		this.ageCategories = ageCategories;
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
