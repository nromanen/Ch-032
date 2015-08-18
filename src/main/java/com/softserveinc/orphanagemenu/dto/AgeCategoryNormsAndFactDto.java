package com.softserveinc.orphanagemenu.dto;

import com.softserveinc.orphanagemenu.model.AgeCategory;

public class AgeCategoryNormsAndFactDto {
	private AgeCategory ageCategory;
	private Double norma;
	private Double factQuantity;
	
	public AgeCategoryNormsAndFactDto() {
		super();
		
	}
	public AgeCategory getAgeCategory() {
		return ageCategory;
	}
	public void setAgeCategory(AgeCategory ageCategory) {
		this.ageCategory = ageCategory;
	}
	public Double getNorma() {
		return norma;
	}
	public void setNorma(Double norma) {
		this.norma = norma;
	}
	public Double getFactQuantity() {
		return factQuantity;
	}
	public void setFactQuantity(Double factQuantity) {
		this.factQuantity = factQuantity;
	}
	
	
	

}
