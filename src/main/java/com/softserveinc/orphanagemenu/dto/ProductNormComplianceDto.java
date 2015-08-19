package com.softserveinc.orphanagemenu.dto;

import java.util.ArrayList;
import java.util.List;


public class ProductNormComplianceDto {
	private String productName;
	private ArrayList<AgeCategoryNormsAndFactDto> categoryWithNormsAndFact = new ArrayList<AgeCategoryNormsAndFactDto>();
	
	public ProductNormComplianceDto() {
		super();
		
	}

	
	public String getProductName() {
		return productName;
	}


	public void setProductName(String name) {
		this.productName = name;
	}


	public List<AgeCategoryNormsAndFactDto> getCategoryWithNormsAndFact() {
		return categoryWithNormsAndFact;
	}
	public void addCategoryWithNormsAndFact(
			AgeCategoryNormsAndFactDto categoryWithNormsAndFact) {
		if(this.categoryWithNormsAndFact.contains(categoryWithNormsAndFact)){
			int indexID = this.categoryWithNormsAndFact.indexOf(categoryWithNormsAndFact);
			AgeCategoryNormsAndFactDto ageCat = this.categoryWithNormsAndFact.get(indexID);
			ageCat.setFactQuantity(ageCat.getFactQuantity()+categoryWithNormsAndFact.getFactQuantity());
			
		} else{
			this.categoryWithNormsAndFact.add(categoryWithNormsAndFact);
		}
		
		
		
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((productName == null) ? 0 : productName.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductNormComplianceDto other = (ProductNormComplianceDto) obj;
		if (productName == null) {
			if (other.productName != null)
				return false;
		} else if (!productName.equals(other.productName))
			return false;
		return true;
	}
	
	

}
