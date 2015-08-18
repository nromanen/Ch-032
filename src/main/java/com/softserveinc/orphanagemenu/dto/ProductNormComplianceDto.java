package com.softserveinc.orphanagemenu.dto;

public class ProductNormComplianceDto {
	private String name;
	private AgeCategoryNormsAndFactDto categoryWithNormsAndFact;
	
	public ProductNormComplianceDto() {
		super();
		
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public AgeCategoryNormsAndFactDto getCategoryWithNormsAndFact() {
		return categoryWithNormsAndFact;
	}
	public void setCategoryWithNormsAndFact(
			AgeCategoryNormsAndFactDto categoryWithNormsAndFact) {
		this.categoryWithNormsAndFact = categoryWithNormsAndFact;
	}
	
	

}
